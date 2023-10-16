package bj.delivery.api.domain.user.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.db.user.UserEntity;
import bj.delivery.db.user.UserRepository;
import bj.delivery.db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(x -> {
                    x.setStatus(UserStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity NULL"));
    }
}
