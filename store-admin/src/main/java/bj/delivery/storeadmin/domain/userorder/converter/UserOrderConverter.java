package bj.delivery.storeadmin.domain.userorder.converter;

import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.storeadmin.common.annotation.Converter;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class UserOrderConverter {

    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(this::responseBuilder)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserOrderEntity NULL"));
    }

    public List<UserOrderResponse> toResponse(List<UserOrderEntity> userOrderEntityList){

        return userOrderEntityList.stream()
                .map(this::responseBuilder)
                .collect(Collectors.toList());
    }

    private UserOrderResponse responseBuilder(UserOrderEntity x) {
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
                .build();
    }
}
