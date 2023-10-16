package bj.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Token의 경우 2000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs {

    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2000, "유효하지 않은 토큰."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "만료된 토큰."),
    EXCEPTION_TOKEN(HttpStatus.BAD_REQUEST.value(), 2002, "토큰 알 수 없는 에러."),
    NOT_FOUND_TOKEN(HttpStatus.BAD_REQUEST.value(), 2003, "인증 헤더 토큰 없음."),
    AUTHORIZATION_FAILURE(HttpStatus.BAD_REQUEST.value(), 2004, "인증 실패..");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
