package bj.delivery.db.userorder;

import bj.delivery.db.BaseEntity;
import bj.delivery.db.userorder.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder // 부모 상속 받았기에 해야함
@EqualsAndHashCode(callSuper = true) // 부모 상속 받았기에 해야함
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId; // user table 1:N

    @Column(nullable = false)
    private Long storeId; // store table 1:N

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

}
