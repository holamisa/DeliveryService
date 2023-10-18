package bj.delivery.api.domain.userordermenu.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.db.userordermenu.UserOrderMenuEntity;
import bj.delivery.db.userordermenu.UserOrderMenuRepository;
import bj.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenu(Long userOrderId){

        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }

    public UserOrderMenuEntity registerUserOrderMenu(
            UserOrderMenuEntity userOrderMenuEntity
    ){

        return Optional.ofNullable(userOrderMenuEntity)
                .map(x -> {
                    x.setStatus(UserOrderMenuStatus.REGISTERED);

                    return userOrderMenuRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
