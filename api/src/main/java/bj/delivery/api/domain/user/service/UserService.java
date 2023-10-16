package bj.delivery.api.domain.user.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.error.UserErrorCode;
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
                    // 중복가입 확인
                    var duplicateUser = userRepository.findFirstByEmailAndStatusOrderByIdDesc(userEntity.getEmail(), UserStatus.REGISTERED);
                    if(duplicateUser.isPresent()){
                        throw new ApiException(UserErrorCode.USER_DUPLICATE);
                    }

                    x.setStatus(UserStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity NULL"));
    }

    public UserEntity login(
            String email,
            String password
    ){
        return getUserWithThrow(email, password);
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
