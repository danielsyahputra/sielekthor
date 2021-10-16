package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.PembelianService;
import apap.tugas.sielekthor.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BarangController {
    @Qualifier("barangServiceImpl")

    @Autowired
    private BarangService barangService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/barang/tambah")
    public String addBarangFormPage(Model model) {
        BarangModel barang = new BarangModel();
        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("barang", barang);
        model.addAttribute("listTipe", listTipe);
        return "form-add-barang";
    }

    @PostMapping("/barang/tambah")
    public String addAgensiSubmitPage(@ModelAttribute BarangModel barang, Model model) {
        barangService.addBarang(barang);
        model.addAttribute("pesan", "Barang dengan kode barang " + barang.getKodeBarang() + " berhasil ditambahkan.");
        model.addAttribute("link", "barang");
        return "info";
    }

    @GetMapping("/barang")
    public String viewAllBarang(Model model) {
        List<BarangModel> listBarang = barangService.getListBarang();
        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String viewDetailBarang(@PathVariable Long idBarang, Model model) {
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        model.addAttribute("barang", barang);
        model.addAttribute("link", "barang");
        return "detail-barang";
    }

    @GetMapping("/barang/ubah/{idBarang}")
    public String ubahBarangByIdBarangFormPage(@PathVariable Long idBarang, Model model) {
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        model.addAttribute("barang", barang);
        model.addAttribute("link", "barang");
        return "form-ubah-barang";
    }

    @PostMapping("/barang/ubah")
    public String ubahBarangSubmit(@ModelAttribute BarangModel barang, Model model) {
        BarangModel updatedBarang = barangService.ubahBarang(barang);
        String kodeBarang = updatedBarang.getKodeBarang();
        model.addAttribute("pesan", String.format("Barang dengan kode %s berhasil diubah!", kodeBarang));
        model.addAttribute("link", String.format("barang/%d", updatedBarang.getIdBarang()));
        return "info";
    }

    @GetMapping("/barang/cari")
    public String cariBarang(Model model) {
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<BarangModel> listBarang = new ArrayList<>();
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listTipe", listTipe);
        return "cari-barang";
    }

    @GetMapping("/barang/")
    public String cariBarangBerdasarkanTipeDanStok(
            @RequestParam(value = "namaTipe", required = false) String namaTipe,
            @RequestParam(value = "isAvailable", required = false) Integer isAvailable,
            Model model
    ) {
        if (namaTipe.equals("") || isAvailable == null) {
            model.addAttribute("pesan", "Anda belum memasukkan pilihan yang anda inginkan!");
            model.addAttribute("link", "barang/cari");
            return "info";
        }
        TipeModel tipe = tipeService.getTipeByNamaTipe(namaTipe);
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<BarangModel> listBarang = barangService.getListBarangByTipeAndAvailable(tipe, isAvailable);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listTipe", listTipe);
        return "cari-barang";
    }
}
