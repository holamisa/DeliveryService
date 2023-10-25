package bj.delivery.storeadmin.domain.storemenu.converter;

import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.storeadmin.common.annotation.Converter;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import bj.delivery.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity){

        return Optional.ofNullable(storeMenuEntity)
                .map(x -> StoreMenuResponse.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .amount(x.getAmount())
                        .status(x.getStatus())
                        .thumbnailUrl(x.getThumbnailUrl())
                        .star(x.getStar())
                        .sequence(x.getSequence())
                        .registeredAt(x.getRegisteredAt())
                        .unregisteredAt(x.getUnregisteredAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenuEntity NULL"));
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> storeMenuEntityList){

        return storeMenuEntityList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
