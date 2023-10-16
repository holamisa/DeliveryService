package bj.delivery.api.domain.user.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.user.controller.model.UserRegisterRequest;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){
        return Optional.ofNullable(request)
                .map(x -> UserEntity.builder()
                        .name(x.getName())
                        .email(x.getEmail())
                        .address(x.getAddress())
                        .password(x.getPassword())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest NULL"));
    }

    public UserResponse toResponse(UserEntity user) {
        return Optional.ofNullable(user)
                .map(x -> UserResponse.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .email(x.getEmail())
                        .status(x.getStatus())
                        .address(x.getAddress())
                        .registeredAt(x.getRegisteredAt())
                        .unregisteredAt(x.getUnregisteredAt())
                        .lastLoginAt(x.getLastLoginAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity NULL"));
    }

    public UserResponse toResponse(UserDTO user) {
        return Optional.ofNullable(user)
                .map(x -> UserResponse.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .email(x.getEmail())
                        .status(x.getStatus())
                        .address(x.getAddress())
                        .registeredAt(x.getRegisteredAt())
                        .unregisteredAt(x.getUnregisteredAt())
                        .lastLoginAt(x.getLastLoginAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity NULL"));
    }
}
