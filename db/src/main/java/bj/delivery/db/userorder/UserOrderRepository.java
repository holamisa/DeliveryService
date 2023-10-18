package bj.delivery.db.userorder;

import bj.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    // select * from user_order where user_id = ? and status = ? order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    // select * from user_order where user_id = ? and status in (?,? ..) order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    // select * from user_order where id = ? and user_id = ? and status = ? limit 1
    Optional<UserOrderEntity> findFirstByIdAndUserIdAndStatus(Long userOrderId, Long userId, UserOrderStatus status);

    // select * from user_order where id = ? and user_id = ? limit 1
    Optional<UserOrderEntity> findFirstByIdAndUserId(Long userOrderId, Long userId);
}
