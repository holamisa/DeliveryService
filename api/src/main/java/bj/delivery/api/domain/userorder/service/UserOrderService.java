package bj.delivery.api.domain.userorder.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.db.userorder.UserOrderRepository;
import bj.delivery.db.userorder.enums.UserOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithThrow(
            Long userOrderId,
            Long userId
    ){

        return userOrderRepository.findFirstByIdAndUserId(userOrderId, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserOrderEntity> searchAll(Long userId){

        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> searchAll(
            Long userId,
            List<UserOrderStatus> statusList
    ){

        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
    }

    // 현재 진행중인 내역
    public List<UserOrderEntity> getCurrentOrder(Long userId){

        return searchAll(userId, List.of(
                UserOrderStatus.ORDER,
                UserOrderStatus.ACCEPT,
                UserOrderStatus.COOKING,
                UserOrderStatus.DELIVERING
        ));
    }

    // 과거 주문한 내역
    public List<UserOrderEntity> getPastOrder(Long userId){

        return searchAll(userId, List.of(
                UserOrderStatus.RECEIVE
        ));
    }

    // 주문
    public UserOrderEntity registerOrder(
            UserOrderEntity userOrderEntity
    ){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setStatus(UserOrderStatus.ORDER);
                    x.setOrderedAt(LocalDateTime.now());

                    return userOrderRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
