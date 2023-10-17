package bj.delivery.api.domain.user.controller;

import bj.delivery.api.common.annotation.UserSession;
import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.user.business.UserBusiness;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import bj.delivery.api.domain.user.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "UserApi", description = "사용자 API Document")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    @Operation(summary = "내 정보", description = "내 정보 확인")
    public Api<UserResponse> me(
            @UserSession UserDTO user
    ){

        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
