package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BarangController {
    @Qualifier("barangServiceImpl")

    @Autowired
    private BarangService barangService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/barang/add")
    public String addBarangFormPage(Model model) {
        BarangModel barang = new BarangModel();
        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("barang", barang);
        model.addAttribute("listTipe", listTipe);
        return "form-add-barang";
    }

    @PostMapping("/barang/add")
    public String addAgensiSubmitPage(@ModelAttribute BarangModel barang, Model model) {
        barangService.addBarang(barang);
        model.addAttribute("pesan", "Barang dengan kode barang " + barang.getKodeBarang() + " berhasil ditambahkan.");
        return "info";
    }

    @GetMapping("/barang/viewall")
    public String viewAllBarang(Model model) {
        List<BarangModel> listBarang = barangService.getListBarang();
        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }
}
