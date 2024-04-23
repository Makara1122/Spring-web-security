package co.istad.mobilebankingcstad.features.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    @PostMapping("/login")
    public String login() {
        return "login successfully";
    }
    @PostMapping("/logout")
    public String refreshToken(){
        return "refresh token successfully";
    }
    @PostMapping("/register")
    public String signup(){
        return "logout successfully";
    }
}
