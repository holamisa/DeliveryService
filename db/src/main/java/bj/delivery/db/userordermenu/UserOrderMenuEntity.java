package bj.delivery.db.userordermenu;

import bj.delivery.db.BaseEntity;
import bj.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder // 부모 상속 받았기에 해야함
@EqualsAndHashCode(callSuper = true) // 부모 상속 받았기에 해야함
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_order_menu")
public class UserOrderMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userOrderId; // user_order table 1:N

    @Column(nullable = false)
    private Long storeMenuId; // store_menu table 1:N

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;
}
