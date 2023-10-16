package bj.delivery.api.domain.token.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.token.controller.model.TokenResponse;
import bj.delivery.api.domain.token.model.TokenDTO;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDTO accessToken,
            TokenDTO refreshToken
    ){
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        Objects.requireNonNull(refreshToken, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
