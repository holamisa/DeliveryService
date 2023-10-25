package bj.delivery.storeadmin.domain.userorder.service;

import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.db.userorder.UserOrderRepository;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrder(Long userOrderId){

        return userOrderRepository.findById(userOrderId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
