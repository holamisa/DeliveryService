package bj.delivery.api.domain.user.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.token.controller.model.TokenResponse;
import bj.delivery.api.domain.user.business.UserBusiness;
import bj.delivery.api.domain.user.controller.model.UserLoginRequest;
import bj.delivery.api.domain.user.controller.model.UserRegisterRequest;
import bj.delivery.api.domain.user.controller.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
@Tag(name = "UserOpenApi", description = "사용자 Open API Document")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    //사용자 가입
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "사용자 회원가입")
    public Api<UserResponse> register(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    // 사용자 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ){
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
