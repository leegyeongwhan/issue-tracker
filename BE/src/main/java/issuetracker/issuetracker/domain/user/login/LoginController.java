package issuetracker.issuetracker.domain.user.login;

import issuetracker.issuetracker.domain.user.MemberService;
import issuetracker.issuetracker.domain.user.login.dto.GithubToken;
import issuetracker.issuetracker.domain.user.login.dto.JWTResponse;
import issuetracker.issuetracker.domain.user.login.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;
    private final MemberService service;

    @GetMapping("/githublogin")
    public ResponseEntity<JWTResponse> githubLogin(String code, HttpServletResponse response) {
        GithubToken githubToken = loginService.getAccessToken(code);
        response.setHeader("Authorization", "application/json");

        UserProfileResponse userProfile = loginService.getUserProfile(githubToken.getAccessToken());
        service.checkLoginMember(userProfile);

        String token = jwtUtil.createToken(userProfile);

        return ResponseEntity.ok(new JWTResponse("login success", token));
    }

}
