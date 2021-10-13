package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PembelianController {
    @Qualifier("pembelianServiceImpl")

    @Autowired
    private PembelianService pembelianService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/pembelian/tambah")
    public String tambahPembelianFormPage(Model model) {
        PembelianModel pembelian = new PembelianModel();
        List<MemberModel> listMember = memberService.getListMember();
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listMember", listMember);
        return "form-add-pembelian";
    }

    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        return "viewall-pembelian";
    }
}
