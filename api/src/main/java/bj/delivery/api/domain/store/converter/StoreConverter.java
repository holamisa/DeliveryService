package bj.delivery.api.domain.store.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import bj.delivery.api.domain.store.controller.model.StoreResponse;
import bj.delivery.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity(StoreRegisterRequest request){

        return Optional.ofNullable(request)
                .map(x -> StoreEntity.builder()
                        .name(x.getName())
                        .address(x.getAddress())
                        .category(x.getCategory())
                        .star(0)
                        .thumbnailUrl(x.getThumbnailUrl())
                        .minimumAmount(x.getMinimumAmount())
                        .minimumDeliveryAmount(x.getMinimumDeliveryAmount())
                        .phoneNumber(x.getPhoneNumber())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreRegisterRequest NULL"));
    }

    public StoreResponse toResponse(StoreEntity entity){

        return Optional.ofNullable(entity)
                .map(x -> StoreResponse.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .address(x.getAddress())
                        .status(x.getStatus())
                        .category(x.getCategory())
                        .star(x.getStar())
                        .thumbnailUrl(x.getThumbnailUrl())
                        .minimumAmount(x.getMinimumAmount())
                        .minimumDeliveryAmount(x.getMinimumDeliveryAmount())
                        .phoneNumber(x.getPhoneNumber())
                        .registeredAt(x.getRegisteredAt())
                        .unregisteredAt(x.getUnregisteredAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreEntity NULL"));
    }
}
