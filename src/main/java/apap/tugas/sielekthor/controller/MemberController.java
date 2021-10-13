package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    @Qualifier("memberServiceImpl")

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/tambah")
    public String tambahMemberFormPage(Model model) {
        MemberModel member = new MemberModel();
        model.addAttribute("member", member);
        return "form-add-member";
    }

    @PostMapping("/member/tambah")
    public String tambahMemberSubmitForm(@ModelAttribute MemberModel member, Model model) {
        memberService.addMember(member);
        model.addAttribute("pesan", String.format("Member dengan nama %s berhasil ditambahkan!", member.getNamaMember()));
        return "info";
    }

    @GetMapping("/member")
    public String viewAllMember(Model model) {
        List<MemberModel> listMember = memberService.getListMember();
        model.addAttribute("listMember", listMember);
        return "viewall-member";
    }

    @GetMapping("/member/ubah/{idMember}")
    public String ubahMemberFormPage(@PathVariable Long idMember, Model model) {
        MemberModel member = memberService.getMemberByIdMember(idMember);
        model.addAttribute("member", member);
        return "form-ubah-member";
    }

    @PostMapping("/member/ubah")
    public String ubahMemberSubmitPage(@ModelAttribute MemberModel member, Model model) {
        MemberModel updatedMember = memberService.ubahMember(member);
        model.addAttribute("pesan", String.format("Member dengan id %d berhasil diubah", updatedMember.getIdMember()));
        return "info";
    }
}
