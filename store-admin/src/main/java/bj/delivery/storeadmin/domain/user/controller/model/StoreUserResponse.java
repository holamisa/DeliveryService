package bj.delivery.storeadmin.domain.user.controller.model;

import bj.delivery.db.store.enums.StoreCategory;
import bj.delivery.db.store.enums.StoreStatus;
import bj.delivery.db.storeuser.enums.StoreUserRole;
import bj.delivery.db.storeuser.enums.StoreUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse {

    private UserResponse userResponse;

    private StoreResponse storeResponse;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {

        private Long id;

        private Long storeId;

        private String email;

        private StoreUserStatus status;

        private StoreUserRole role;

        private LocalDateTime registeredAt;

        private LocalDateTime unregisteredAt;

        private LocalDateTime lastLoginAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreResponse {

        private Long id;

        private String name;

        private StoreStatus status;

        private StoreCategory category;
    }
}
