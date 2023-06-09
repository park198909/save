package controllers;

import models.member.MemberDao;
import models.member.MemberJoinService;
import models.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private JoinValidator joinValidator;
    @Autowired
    private Optional<MemberJoinService> opt;

    @Autowired
    private MemberLoginService loginService;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm) {
        /*
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);
        */
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {
        joinValidator.validate(joinForm, errors);

        if (errors.hasErrors()) {
            return "member/join";   // 페이지 리셋
        }

        // 회원가입 완료 처리
        MemberJoinService joinService = opt.get();
        joinService.join(joinForm);

        return "redirect:/member/login";    // 가입 성공하면 로그인페이지로
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        @CookieValue(required=false, name="saveId") String sId) {
        System.out.println(sId);
        if (sId != null) {
            loginForm.setUserId(sId);
            loginForm.setSaveId(true);
        }
        return "member/login";
    }

    @PostMapping("/login")
    public String loginPs(@Valid LoginForm loginForm, Errors errors) {
        loginValidator.validate(loginForm, errors);

        if (errors.hasErrors()) {
            return "member/login";
        }

        // 로그인 완료 처리
        loginService.login(loginForm);
        
        return "redirect:/";    // 로그인 성공하면 메인페이지로
    }
}
