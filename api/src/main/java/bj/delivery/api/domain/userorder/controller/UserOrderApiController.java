package bj.delivery.api.domain.userorder.controller;

import bj.delivery.api.common.annotation.UserSession;
import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.userorder.business.UserOrderBusiness;
import bj.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
@Tag(name = "UserOrderApi", description = "주문 API Document")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("/order")
    @Operation(summary = "주문", description = "가맹점의 메뉴 주문")
    public Api<UserOrderResponse> userOrder(
            @Parameter(hidden = true)
            @UserSession UserDTO user,
            @Valid
            @RequestBody Api<UserOrderRequest> userOrderRequest

    ){
        var response = userOrderBusiness.userOrder(user, userOrderRequest.getBody());

        return Api.OK(response);
    }
}
