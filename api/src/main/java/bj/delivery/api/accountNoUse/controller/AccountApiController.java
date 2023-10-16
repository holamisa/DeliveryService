package bj.delivery.api.accountNoUse.controller;

import bj.delivery.api.accountNoUse.model.AccountMeResponse;
import bj.delivery.api.common.annotation.Timer;
import bj.delivery.api.common.api.Api;
import bj.delivery.db.account.AccountEntity;
import bj.delivery.db.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("")
    public void save(){
        var account = AccountEntity.builder().build();
        accountRepository.save(account);
    }

    @Timer
    @GetMapping("/me")
    public Api<AccountMeResponse> me(){

        var data = AccountMeResponse.builder()
                .name("홍길동")
                .email("hong@hong.com")
                .registeredAt(LocalDateTime.now())
                .build();

//        var str = "awefawef";
//        var age = Integer.parseInt(str);

        return Api.OK(data);
    }
}
