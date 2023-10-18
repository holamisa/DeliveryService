package bj.delivery.api.domain.userorder.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import bj.delivery.api.domain.userordermenu.model.UserOrderMenuDTO;
import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.db.userordermenu.UserOrderMenuEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class UserOrderConverter {

    public UserOrderEntity toEntity(
            UserDTO user,
            Long storeId,
            List<StoreMenuEntity> storeMenuEntityList
    ){

        var totalAmount = storeMenuEntityList.stream()
                .map(StoreMenuEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return UserOrderEntity.builder()
                .userId(user.getId())
                .storeId(storeId)
                .amount(totalAmount)
                .build();
    }

    public UserOrderResponse toResponse(
            UserOrderEntity userOrderEntity,
            List<UserOrderMenuEntity> userOrderMenuEntityList
    ){

        var userOrderMenuList = userOrderMenuEntityList.stream()
                .map(x -> UserOrderMenuDTO.builder()
                        .id(x.getId())
                        .userOrderId(x.getUserOrderId())
                        .storeMenuId(x.getStoreMenuId())
                        .status(x.getStatus())
                        .build())
                .collect(Collectors.toList());

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    return UserOrderResponse.builder()
                            .id(x.getId())
                            .userId(x.getUserId())
                            .storeId(x.getStoreId())
                            .status(x.getStatus())
                            .amount(x.getAmount())
                            .orderedAt(x.getOrderedAt())
                            .acceptedAt(x.getAcceptedAt())
                            .cookingStartedAt(x.getCookingStartedAt())
                            .deliveryStartedAt(x.getDeliveryStartedAt())
                            .receivedAt(x.getReceivedAt())
                            .userOrderMenuList(userOrderMenuList)
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserOrderEntity NULL"));
    }
}
