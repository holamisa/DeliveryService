package bj.delivery.storeadmin.domain.user.converter;

import bj.delivery.db.store.StoreEntity;
import bj.delivery.db.storeuser.StoreUserEntity;
import bj.delivery.storeadmin.common.annotation.Converter;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import bj.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;

import java.util.Optional;

@Converter
public class StoreUserConverter {

    public StoreUserEntity toEntity(StoreUserRegisterRequest request){

        return Optional.ofNullable(request)
                .map(x -> StoreUserEntity.builder()
                        .storeId(x.getStoreId())
                        .email(x.getEmail())
                        .password(x.getPassword())
                        .role(x.getRole())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreUserRegisterRequest NULL"));
    }

    public StoreUserResponse toResponse(
            StoreUserEntity storeUserEntity,
            StoreEntity storeEntity
    ){
        if(storeUserEntity == null){
            throw new ApiException(ErrorCode.NULL_POINT, "StoreUserEntity NULL");
        }
        if(storeEntity == null){
            throw new ApiException(ErrorCode.NULL_POINT, "StoreEntity NULL");
        }

        return StoreUserResponse.builder()
                .userResponse(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUserEntity.getId())
                                .storeId(storeUserEntity.getStoreId())
                                .email(storeUserEntity.getEmail())
                                .status(storeUserEntity.getStatus())
                                .role(storeUserEntity.getRole())
                                .registeredAt(storeUserEntity.getRegisteredAt())
                                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                                .lastLoginAt(storeUserEntity.getLastLoginAt())
                                .build())
                .storeResponse(StoreUserResponse.StoreResponse.builder()
                        .id(storeEntity.getId())
                        .name(storeEntity.getName())
                        .status(storeEntity.getStatus())
                        .category(storeEntity.getCategory())
                        .build())
                .build();
    }

    public StoreUserResponse toResponse(
            UserSession userSession
    ){

        return Optional.ofNullable(userSession)
                .map(x -> StoreUserResponse.builder()
                        .userResponse(
                                StoreUserResponse.UserResponse.builder()
                                        .id(x.getUserId())
                                        .storeId(x.getStoreId())
                                        .email(x.getEmail())
                                        .status(x.getUserStatus())
                                        .role(x.getRole())
                                        .registeredAt(x.getRegisteredAt())
                                        .unregisteredAt(x.getUnregisteredAt())
                                        .lastLoginAt(x.getLastLoginAt())
                                        .build())
                        .storeResponse(StoreUserResponse.StoreResponse.builder()
                                .id(x.getStoreId())
                                .name(x.getStoreName())
                                .status(x.getStoreStatus())
                                .category(x.getStoreCategory())
                                .build())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserSession NULL"));
    }
}
