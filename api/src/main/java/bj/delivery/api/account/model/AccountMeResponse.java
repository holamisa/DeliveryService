package bj.delivery.api.account.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // 컨피그에서 설정됨
public class AccountMeResponse {

    private String email;
    private String name;
    private LocalDateTime registeredAt;
}
