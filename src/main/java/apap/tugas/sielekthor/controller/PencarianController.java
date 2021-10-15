package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.PembelianBarangService;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PencarianController {

    @Autowired
    PembelianService pembelianService;

    @Autowired
    MemberService memberService;

    @Autowired
    PembelianBarangService pembelianBarangService;

    @GetMapping("/cari/pembelian")
    public String cariPembelianBerdasarkanMemberDanPembayaran(
            @RequestParam(value = "idMember", required = false) Long idMember,
            @RequestParam(value = "tipePembayaran", required = false) Integer tipePembayaran,
            Model model
    ) {
        Long defaultValue = (long) 0;
        List<MemberModel> listMember = memberService.getListMember();
        MemberModel member = memberService.getMemberByIdMember(idMember);
        List<PembelianModel> listPembelian = new ArrayList<>();
        if (idMember == null || idMember.equals(defaultValue)) {
            if (tipePembayaran != null) {
                listPembelian = pembelianService.cariPembelianBerdasarkanIsCash(tipePembayaran);
            }
        } else {
            if (tipePembayaran != null) {
                listPembelian = pembelianService.cariPembelianBerdasarkanMemberDanPembayaran(member, tipePembayaran);
            } else {
                listPembelian = pembelianService.cariPembelianBerdasarkanMember(member);
            }
        }
        List<Integer> listKuantitas = new ArrayList<>();
        for (PembelianModel pembelian : listPembelian) {
            listKuantitas.add(pembelianService.getJumlahBarangPembelian(pembelian));
        }
        model.addAttribute("listPembelian", listPembelian);
        model.addAttribute("listKuantitas", listKuantitas);
        model.addAttribute("listMember", listMember);
        model.addAttribute("defaultValue", defaultValue);
        return "cari-pembelian";
    }
    
}