package bj.delivery.storeadmin.domain.user.service;

import bj.delivery.db.storeuser.StoreUserEntity;
import bj.delivery.db.storeuser.StoreUserRepository;
import bj.delivery.db.storeuser.enums.StoreUserStatus;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.error.UserErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public StoreUserEntity register(
            StoreUserEntity storeUserEntity
    ){

        return Optional.ofNullable(storeUserEntity)
                .map(x -> {
                    // 중복가입 확인
                    var duplicateUser = storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(x.getEmail(), StoreUserStatus.REGISTERED);
                    if(duplicateUser.isPresent()){
                        throw new ApiException(UserErrorCode.USER_DUPLICATE);
                    }

                    // 비밀번호 암호화하여 저장
                    x.setPassword(passwordEncoder.encode(x.getPassword()));
                    x.setStatus(StoreUserStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());
                    return storeUserRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity NULL"));
    }

    public StoreUserEntity getUserWithThrow(String email){

        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
