package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.KuantitasPembelianModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PembelianController {
    @Qualifier("pembelianServiceImpl")

    @Autowired
    private PembelianService pembelianService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/pembelian/tambah")
    public String tambahPembelianFormPage(Model model) {

        // Dapatin tanggal hari ini.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateNow = formatter.format(now);

        PembelianModel pembelian = new PembelianModel();
        pembelian.setListBarang(new ArrayList<>());
        pembelian.getListBarang().add(new KuantitasPembelianModel());

        List<BarangModel> listBarang = barangService.getListBarang();
        List<MemberModel> listMember = memberService.getListMember();

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listMember", listMember);
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "tambahBarang")
    private String tambahBarang(@ModelAttribute PembelianModel pembelian, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateNow = formatter.format(now);

        pembelian.getListBarang().add(new KuantitasPembelianModel());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        return "viewall-pembelian";
    }
}
