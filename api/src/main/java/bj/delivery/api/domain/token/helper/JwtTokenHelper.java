package bj.delivery.api.domain.token.helper;

import bj.delivery.api.common.error.TokenErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.api.domain.token.ifs.TokenHelperIfs;
import bj.delivery.api.domain.token.model.TokenDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    // application.yaml 파일에 설정 값 읽기
    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDTO issueAccessToken(Map<String, Object> data) {
        return createJwtToken(data, accessTokenPlusHour);
    }

    @Override
    public TokenDTO issueRefreshToken(Map<String, Object> data) {
        return createJwtToken(data, refreshTokenPlusHour);
    }

    private TokenDTO createJwtToken(Map<String, Object> data, Long plusHour){

        var expiredLocalDateTime = LocalDateTime.now().plusHours(plusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDTO.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) throws ApiException {

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try{
            var result = parser.parseClaimsJws(token);
            return new HashMap<>(result.getBody());
        }
        catch (SignatureException e){
            // 토큰이 유효하지 않을 때
            throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
        }
        catch (ExpiredJwtException e){
            // 토큰이 만료
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
        }
        catch (Exception e){
            throw new ApiException(TokenErrorCode.EXCEPTION_TOKEN, e);
        }
    }
}
