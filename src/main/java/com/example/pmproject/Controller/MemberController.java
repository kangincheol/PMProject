package com.example.pmproject.Controller;

import com.example.pmproject.DTO.MemberDTO;
import com.example.pmproject.DTO.MemberUpdateDTO;
import com.example.pmproject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/info")
    public String memberInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberDTO member = memberService.listOne(userDetails.getUsername());

        model.addAttribute("member", member);

        return "/user/info";
    }

    @GetMapping("/user/update")
    public String updateForm(MemberUpdateDTO memberUpdateDTO, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.listOne(userDetails.getUsername());
        model.addAttribute("memberDTO", memberDTO);

        return "/user/update";
    }

    @PostMapping("/user/update")
    public String memberUpdate(@Valid MemberUpdateDTO memberUpdateDTO, BindingResult bindingResult, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(bindingResult.hasErrors()) {
            return "user/update";
        }


        try {
            String result = memberService.update(memberUpdateDTO, userDetails.getUsername());
            if(result==null) {
                model.addAttribute("wrongPwd", "기존 비밀번호가 맞지 않습니다.");
                return "user/update";
            }
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "user/update";
        }

        return "redirect:/user/info";
    }

    @GetMapping("/user/withdrawal")
    public String withdrawalForm() {
        return "user/withdrawal";
    }

    @PostMapping("/user/withdrawal")
    public String memberWithdrawal(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.withdrawal(userDetails.getUsername(), password);

        if(result) {
            return "redirect:/logout";
        }else {
            model.addAttribute("wrongPwd", "비밀번호가 맞지 않습니다.");
            return "user/withdrawal";
        }
    }

}
