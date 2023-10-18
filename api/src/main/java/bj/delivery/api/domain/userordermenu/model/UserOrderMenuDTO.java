package bj.delivery.api.domain.userordermenu.model;

import bj.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderMenuDTO {

    private Long id;

    private Long userOrderId;

    private Long storeMenuId;

    private UserOrderMenuStatus status;
}
