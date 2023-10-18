package bj.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * UserOrder의 경우 3000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum UserOrderErrorCode implements ErrorCodeIfs {

    MINIMUM_AMOUNT_NOT_SATISFY(HttpStatus.BAD_REQUEST.value(), 3000, "최소 주문 금액을 충족하지 않습니다.");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
