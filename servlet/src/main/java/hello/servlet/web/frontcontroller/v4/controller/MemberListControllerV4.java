package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

public class MemberListControllerV4 implements ControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model)
            throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        model.put("members", members);

        return "members";
    }
}
