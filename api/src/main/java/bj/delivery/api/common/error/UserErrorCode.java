package bj.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User의 경우 1000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 1404, "존재하지 않는 사용자이거나 비밀번호가 일치하지 않습니다."),
    USER_DUPLICATE(HttpStatus.BAD_REQUEST.value(), 1405, "이미 가입된 계정이 존재합니다.");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
