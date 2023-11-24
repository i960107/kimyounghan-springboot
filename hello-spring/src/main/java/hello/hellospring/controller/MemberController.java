package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/new")
    public String createForm() {
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(MemberForm memberForm) {
        memberService.join(memberForm.toEntity());
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute(memberService.findMembers());
        return "/members/memberList";
    }
}
