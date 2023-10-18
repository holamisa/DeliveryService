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

    // 상태 변경
    public UserOrderEntity setOrderStatus(
            UserOrderEntity userOrderEntity,
            UserOrderStatus status
    ){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setStatus(status);

                    return userOrderRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 주문 확인
    public UserOrderEntity acceptOrder(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setAcceptedAt(LocalDateTime.now());

                    return setOrderStatus(x, UserOrderStatus.ACCEPT);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 조리 시작
    public UserOrderEntity cookOrder(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setCookingStartedAt(LocalDateTime.now());

                    return setOrderStatus(x, UserOrderStatus.COOKING);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 배달 시작
    public UserOrderEntity deliverOrder(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setDeliveryStartedAt(LocalDateTime.now());

                    return setOrderStatus(x, UserOrderStatus.DELIVERING);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 배달 완료
    public UserOrderEntity receiveOrder(UserOrderEntity userOrderEntity){

        return Optional.ofNullable(userOrderEntity)
                .map(x -> {
                    x.setReceivedAt(LocalDateTime.now());

                    return setOrderStatus(x, UserOrderStatus.RECEIVE);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
