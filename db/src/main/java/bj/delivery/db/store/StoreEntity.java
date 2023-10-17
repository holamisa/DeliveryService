package bj.delivery.db.store;

import bj.delivery.db.BaseEntity;
import bj.delivery.db.store.enums.StoreCategory;
import bj.delivery.db.store.enums.StoreStatus;
import lombok.*;
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
@Table(name = "store")
public class StoreEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star; // DB에 디폴트 값이 0, 만약 null 들어가야하면 Double로 수정 필요

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
