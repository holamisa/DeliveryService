package bj.delivery.storeadmin.domain.userordermenu.service;

import bj.delivery.db.userordermenu.UserOrderMenuEntity;
import bj.delivery.db.userordermenu.UserOrderMenuRepository;
import bj.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenuList(Long userOrderId){

        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }
}
