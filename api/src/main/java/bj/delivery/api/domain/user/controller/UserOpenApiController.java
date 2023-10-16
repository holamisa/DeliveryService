package bj.delivery.api.domain.user.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.token.controller.model.TokenResponse;
import bj.delivery.api.domain.user.business.UserBusiness;
import bj.delivery.api.domain.user.controller.model.UserLoginRequest;
import bj.delivery.api.domain.user.controller.model.UserRegisterRequest;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    //사용자 가입
    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    // 사용자 로그인
    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ){
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
