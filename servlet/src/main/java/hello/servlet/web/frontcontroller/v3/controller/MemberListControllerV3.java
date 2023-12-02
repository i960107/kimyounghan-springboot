package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

public class MemberListControllerV3 implements ControllerV3 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.addAttribute("members", members);

        return mv;
    }
}
