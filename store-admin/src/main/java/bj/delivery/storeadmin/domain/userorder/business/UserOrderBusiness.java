package bj.delivery.storeadmin.domain.userorder.business;

import bj.delivery.common.message.model.UserOrderMessage;
import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.storeadmin.common.annotation.Business;
import bj.delivery.storeadmin.common.error.TokenErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import bj.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import bj.delivery.storeadmin.domain.storemenu.service.StoreMenuService;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import bj.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import bj.delivery.storeadmin.domain.userorder.service.UserOrderService;
import bj.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final SseConnectionPool sseConnectionPool;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    /**
     * 주문
     * 주문 내역 찾기
     * 스토어 찾기
     * 연결된 세션 찾기
     * push
     */
    public void pushUserOrder(UserOrderMessage userOrderMessage){

        var userOrderEntity = userOrderService.getUserOrderWithThrow(userOrderMessage.getUserOrderId());

        var userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        var storeMenuResponseList = userOrderMenuList.stream()
                .map(x -> storeMenuService.getStoreMenuWithThrow(x.getStoreMenuId()))
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());

        var userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        var pushData = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponseList(storeMenuResponseList)
                .build();

        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());
        userConnection.sendMessage(pushData);
    }

    public List<UserOrderResponse> acceptOrder(
            UserSession userSession,
            UserOrderRequest userOrderRequest
    ){
        var userOrderEntityList = getUserOrderEntity(userSession, userOrderRequest);
        var acceptOrderEntityList = userOrderEntityList.stream()
                .map(userOrderService::acceptOrder)
                .collect(Collectors.toList());

        return userOrderConverter.toResponse(acceptOrderEntityList);
    }

    public List<UserOrderResponse> cookOrder(
            UserSession userSession,
            UserOrderRequest userOrderRequest
    ){
        var userOrderEntityList = getUserOrderEntity(userSession, userOrderRequest);
        var cookOrderEntityList = userOrderEntityList.stream()
                .map(userOrderService::cookOrder)
                .collect(Collectors.toList());

        return userOrderConverter.toResponse(cookOrderEntityList);
    }

    public List<UserOrderResponse> deliverOrder(
            UserSession userSession,
            UserOrderRequest userOrderRequest
    ){
        var userOrderEntityList = getUserOrderEntity(userSession, userOrderRequest);
        var deliverOrderEntityList = userOrderEntityList.stream()
                .map(userOrderService::deliverOrder)
                .collect(Collectors.toList());

        return userOrderConverter.toResponse(deliverOrderEntityList);
    }

    public List<UserOrderResponse> receiveOrder(
            UserSession userSession,
            UserOrderRequest userOrderRequest
    ){
        var userOrderEntityList = getUserOrderEntity(userSession, userOrderRequest);
        var receiveOrderEntityList = userOrderEntityList.stream()
                .map(userOrderService::receiveOrder)
                .collect(Collectors.toList());

        return userOrderConverter.toResponse(receiveOrderEntityList);
    }

    private List<UserOrderEntity> getUserOrderEntity(
            UserSession userSession,
            UserOrderRequest userOrderRequest
    ){

        return userOrderRequest.getUserOrderIdList().stream()
                .map(x -> {
                    var entity = userOrderService.getUserOrderWithThrow(x);
                    if(!userSession.getStoreId().equals(entity.getStoreId())){
                        throw new ApiException(TokenErrorCode.INVALID_TOKEN);
                    }

                    return entity;
                })
                .collect(Collectors.toList());
    }
}
