package bj.delivery.api.domain.user.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.user.controller.model.UserRegisterRequest;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import bj.delivery.api.domain.user.converter.UserConverter;
import bj.delivery.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save entity -> response
     * 4. return response
     */
    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;

        /*
        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest NULL"));
        */
    }
}
