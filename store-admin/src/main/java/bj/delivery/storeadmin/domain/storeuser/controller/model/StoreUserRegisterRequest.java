package bj.delivery.storeadmin.domain.storeuser.controller.model;

import bj.delivery.db.storeuser.enums.StoreUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserRegisterRequest {

    @NotNull
    private Long storeId;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private StoreUserRole role;
}
