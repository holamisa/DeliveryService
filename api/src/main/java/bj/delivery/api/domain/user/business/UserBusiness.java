package bj.delivery.api.domain.user.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.token.business.TokenBusiness;
import bj.delivery.api.domain.token.controller.model.TokenResponse;
import bj.delivery.api.domain.user.controller.model.UserLoginRequest;
import bj.delivery.api.domain.user.controller.model.UserRegisterRequest;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import bj.delivery.api.domain.user.converter.UserConverter;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

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

        return userConverter.toResponse(newEntity);

        /*
        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest NULL"));
        */
    }

    /**
     * 1. email, password 체크
     * 2. user entity 확인
     * 3. token 생성
     * 4. return token response
     */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        // 토큰 정상 발급 후 마지막 로그인 시간 업데이트
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        if(tokenResponse != null){
            userService.updateLoginAt(userEntity);
        }

        return tokenResponse;
    }

    public UserResponse me(UserDTO userDTO) {
        return userConverter.toResponse(userDTO);
    }
}
