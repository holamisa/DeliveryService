package bj.delivery.api.domain.user.controller;

import bj.delivery.api.common.annotation.UserSession;
import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.user.business.UserBusiness;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import bj.delivery.api.domain.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession UserDTO user
    ){

        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
