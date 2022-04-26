package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// 스프링 컨테이너가 컨트롤러 에노테이션이 있으면 객체 생성해서 스프링이 관리함 (스르링 빈이 관리됨)
public class MemberController {
    private final MemberService memberService;
    
    @Autowired
    // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return  "redirect:/";
    }

    @GetMapping("/members")
    public String list (Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
