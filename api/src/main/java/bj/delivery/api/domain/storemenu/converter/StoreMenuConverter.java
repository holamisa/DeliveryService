package bj.delivery.api.domain.storemenu.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import bj.delivery.db.storemenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){

        return Optional.ofNullable(request)
                .map(x -> StoreMenuEntity.builder()
                        .storeId(x.getStoreId())
                        .name(x.getName())
                        .amount(x.getAmount())
                        .thumbnailUrl(x.getThumbnailUrl())
                        .star(0)
                        .sequence(0)
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenuRegisterRequest NULL"));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity entity){

        return Optional.ofNullable(entity)
                .map(x -> {
                    return StoreMenuResponse.builder()
                            .id(x.getId())
                            .storeId(x.getStoreId())
                            .name(x.getName())
                            .amount(x.getAmount())
                            .status(x.getStatus())
                            .thumbnailUrl(x.getThumbnailUrl())
                            .star(x.getStar())
                            .sequence(x.getSequence())
                            .registeredAt(x.getRegisteredAt())
                            .unregisteredAt(x.getUnregisteredAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenuEntity NULL"));

    }
}
