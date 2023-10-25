package bj.delivery.storeadmin.domain.authorization;

import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.store.service.StoreService;
import bj.delivery.storeadmin.domain.storeuser.service.StoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreService storeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var storeUserEntity = storeUserService.getUserWithThrow(username);
        var storeEntity = storeService.getStoreWithThrow(storeUserEntity.getStoreId());

        // 정상적인 로그인 데이터가 있기에 로그인 시간 업데이트
        storeUserService.updateLogin(storeUserEntity);

        return UserSession.builder()
                .userId(storeUserEntity.getId())
                .email(storeUserEntity.getEmail())
                .password(storeUserEntity.getPassword())
                .userStatus(storeUserEntity.getStatus())
                .role(storeUserEntity.getRole())
                .registeredAt(storeUserEntity.getRegisteredAt())
                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                .lastLoginAt(storeUserEntity.getLastLoginAt())
                .storeId(storeUserEntity.getStoreId())
                .storeName(storeEntity.getName())
                .storeStatus(storeEntity.getStatus())
                .storeCategory(storeEntity.getCategory())
                .build();
    }
}
