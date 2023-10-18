package bj.delivery.api.domain.userorder.controller.model;

import bj.delivery.api.domain.userordermenu.model.UserOrderMenuDTO;
import bj.delivery.db.userorder.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderResponse {

    private Long id;

    private Long userId; // user table 1:N

    private Long storeId; // store table 1:N

    private UserOrderStatus status;

    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

    public List<UserOrderMenuDTO> userOrderMenuList = List.of();
}
