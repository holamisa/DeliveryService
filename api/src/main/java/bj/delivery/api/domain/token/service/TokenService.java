package bj.delivery.api.domain.token.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.token.helper.JwtTokenHelper;
import bj.delivery.api.domain.token.model.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenHelper tokenHelper;

    public TokenDTO issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelper.issueAccessToken(data);
    }

    public TokenDTO issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelper.issueRefreshToken(data);
    }

    public Long validationToken(String token){
        var map = tokenHelper.validationTokenWithThrow(token);

        var userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }
}
