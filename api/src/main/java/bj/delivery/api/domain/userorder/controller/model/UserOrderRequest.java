package bj.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

    // 주문 시 특정 사용자 -> 특정 가맹점 -> 메뉴 리스트 주문 과정
    // 특정 사용자 = 세션 정보

    @NotNull
    private Long storeId;

    @NotNull
    private List<Long> storeMenuIdList;
}
