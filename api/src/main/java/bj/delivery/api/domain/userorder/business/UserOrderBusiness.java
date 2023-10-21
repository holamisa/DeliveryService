package bj.delivery.api.domain.userorder.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.error.UserOrderErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.store.converter.StoreConverter;
import bj.delivery.api.domain.store.service.StoreService;
import bj.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import bj.delivery.api.domain.storemenu.service.StoreMenuService;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import bj.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import bj.delivery.api.domain.userorder.converter.UserOrderConverter;
import bj.delivery.api.domain.userorder.producer.UserOrderProducer;
import bj.delivery.api.domain.userorder.service.UserOrderService;
import bj.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import bj.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.db.userorder.UserOrderEntity;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderProducer userOrderProducer;

    /**
     * 1. 사용자, 메뉴ID
     * 2. userOrder 생성
     * 3. userOrderMenu 생성
     */
    public UserOrderResponse userOrderRegister(
            UserDTO user,
            UserOrderRequest userOrderRequest

    ){

        // 메뉴 리스트
        var storeId = userOrderRequest.getStoreId();
        var storeMenuEntityList = userOrderRequest.getStoreMenuIdList().stream()
                .map(storeMenuService::getStoreMenuWithThrow).collect(Collectors.toList());

        // 최소 주문 금액 확인
        var storeEntity = storeService.getStoreWithThrow(storeId);
        var minimumAmount = storeEntity.getMinimumAmount();
        var totalAmount = storeMenuEntityList.stream()
                .map(StoreMenuEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if(minimumAmount.compareTo(totalAmount) >= 0){
            throw new ApiException(UserOrderErrorCode.MINIMUM_AMOUNT_NOT_SATISFY);
        }

        // 주문
        var userOrderEntity = userOrderConverter.toEntity(user, storeId, storeMenuEntityList);
        var saveUserOrderEntity = userOrderService.registerOrder(userOrderEntity);

        // 주문 메뉴 맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(x -> userOrderMenuConverter.toEntity(saveUserOrderEntity, x))
                .collect(Collectors.toList());
        var saveUserOrderMenuEntityList = userOrderMenuEntityList.stream()
                .map(userOrderMenuService::registerUserOrderMenu)
                .collect(Collectors.toList());


        // 비동기 가맹점 주문 알림 (RabbitMq)
        userOrderProducer.sendOrder(saveUserOrderEntity);

        return userOrderConverter.toResponse(saveUserOrderEntity, saveUserOrderMenuEntityList);
    }

    public List<UserOrderDetailResponse> getCurrentOrder(UserDTO user) {

        var userOrderEntityList = userOrderService.getCurrentOrder(user.getId());

        return getUserOrderDetailResponses(userOrderEntityList);
    }

    public List<UserOrderDetailResponse> getPastOrder(UserDTO user) {

        var userOrderEntityList = userOrderService.getPastOrder(user.getId());

        return getUserOrderDetailResponses(userOrderEntityList);
    }

    public UserOrderDetailResponse getUserOrder(
            UserDTO user,
            Long userOrderId
    ){

        var userOrderEntity = userOrderService.getUserOrderWithThrow(userOrderId, user.getId());

        return getUserOrderDetailResponse(userOrderEntity);
    }

    private List<UserOrderDetailResponse> getUserOrderDetailResponses(List<UserOrderEntity> userOrderEntityList) {

        return userOrderEntityList.stream()
                .map(this::getUserOrderDetailResponse)
                .collect(Collectors.toList());
    }

    private UserOrderDetailResponse getUserOrderDetailResponse(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    // 주문건 별 가맹점
                    var storeId = x.getStoreId();
                    var storeEntity = storeService.getStoreWithThrow(storeId);

                    // 주문한 메뉴 리스트
                    var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(x.getId());

                    // 주문한 메뉴 별 이름 리스트
                    var storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(userOrderMenuEntity -> storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId()))
                            .collect(Collectors.toList());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(x, userOrderMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .storeMenuResponseList(storeMenuConverter.toReponse(storeMenuEntityList))
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
