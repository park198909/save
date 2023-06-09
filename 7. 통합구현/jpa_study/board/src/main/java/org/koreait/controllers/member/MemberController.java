package org.koreait.controllers.member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.commons.MemberUtils;
import org.koreait.models.member.JoinService;
import org.koreait.models.member.MemberInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller @Log
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;
    private final JoinService joinService;
    private final MemberUtils utils;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {
        model.addAttribute("addScript", new String[] { "member/join"} );
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        joinValidator.validate(joinForm, errors);

        if (errors.hasErrors()) {
            return "member/join";
        }

        joinService.join(joinForm);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpSession session, @CookieValue(required = false) String saveId) {
        if (saveId != null) {
            loginForm.setUserId(saveId);
            loginForm.setSaveId(true);
        }

        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            loginForm.setUserId(userId);
        }

        return "member/login";
    }

    @GetMapping("/ex")
    @ResponseBody
    public void ex() {
        MemberInfo memberInfo = utils.getMember();
        log.info(memberInfo.toString());
        /**
        MemberInfo memberInfo = (MemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(memberInfo.toString());
         */
    }
    /**
    public void ex(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info(memberInfo.toString());
    }*/
    /**
    public void ex(Principal principal) {
        String userId = principal.getName();
        log.info(userId);
    }*/
}
