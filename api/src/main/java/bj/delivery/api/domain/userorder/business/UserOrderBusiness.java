package bj.delivery.api.domain.userorder.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.common.error.UserOrderErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.store.service.StoreService;
import bj.delivery.api.domain.storemenu.service.StoreMenuService;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import bj.delivery.api.domain.userorder.converter.UserOrderConverter;
import bj.delivery.api.domain.userorder.service.UserOrderService;
import bj.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import bj.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import bj.delivery.db.storemenu.StoreMenuEntity;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreService storeService;

    /**
     * 1. 사용자, 메뉴ID
     * 2. userOrder 생성
     * 3. userOrderMenu 생성
     */
    public UserOrderResponse userOrder(
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
        if(minimumAmount.compareTo(totalAmount) < 0){
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

        return userOrderConverter.toResponse(saveUserOrderEntity, saveUserOrderMenuEntityList);
    }
}
