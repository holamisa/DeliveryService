package bj.delivery.api.domain.token.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.token.controller.model.TokenResponse;
import bj.delivery.api.domain.token.converter.TokenConverter;
import bj.delivery.api.domain.token.service.TokenService;
import bj.delivery.db.BaseEntity;
import bj.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity user id 추출
     * 2. access, refresh token 생성
     * 3. converter -> token response return
     */
    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(BaseEntity::getId)
                .map(x -> {
                    var accessToken = tokenService.issueAccessToken(x);
                    var refreshToken = tokenService.issueRefreshToken(x);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
