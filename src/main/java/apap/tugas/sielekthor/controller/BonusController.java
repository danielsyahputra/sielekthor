package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BonusController {

    @Autowired
    MemberService memberService;

    @GetMapping("/bonus/cari/member/paling-banyak")
    public String cariMemberPalingBanyakBeli(Model model) {
        List<MemberModel> listMember = memberService.getMemberPalingBanyakBeli();
        model.addAttribute("listMember", listMember);
        return "cari-member-paling-banyak-beli";
    }
}
