package bj.delivery.storeadmin.domain.userorder.service;

import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.db.userorder.UserOrderRepository;
import bj.delivery.db.userorder.enums.UserOrderStatus;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithThrow(Long userOrderId){

        return userOrderRepository.findById(userOrderId)
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
