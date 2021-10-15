package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.PembelianBarangService;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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

    @Autowired
    private PembelianBarangService pembelianBarangService;

    @GetMapping("/pembelian/tambah")
    public String tambahPembelianFormPage(Model model) {

        // Dapatin tanggal hari ini.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        PembelianModel pembelian = new PembelianModel();
        pembelian.setListBarang(new ArrayList<>());
        pembelian.getListBarang().add(new PembelianBarangModel());

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "tambahBarang")
    private String tambahBarang(@ModelAttribute PembelianModel pembelian, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        pembelian.getListBarang().add(new PembelianBarangModel());

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "hapusBarang")
    private String hapusBarang(@ModelAttribute PembelianModel pembelian,
                               Model model, HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        Integer indexBarang = Integer.valueOf(request.getParameter("hapusBarang"));
        pembelian.getListBarang().remove(indexBarang.intValue());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "simpan")
    private String simpanPembelian(@ModelAttribute PembelianModel pembelian, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);
        pembelian.setTanggalPembelian(dateNow);
        pembelian.setNomorInvoice("INV1");

        int totalHarga = 0;
        for (PembelianBarangModel pembelianBarang : pembelian.getListBarang()) {
            BarangModel barang = pembelianBarang.getBarang();
            LocalDate tanggalGaransi = dateNow.plusDays(barang.getJumlahGaransi());
            pembelianBarang.setTanggalGaransi(tanggalGaransi);
            totalHarga += (barang.getHargaBarang() * pembelianBarang.getKuantitas());
            pembelianBarang.setPembelian(pembelian);
        }
        pembelian.setTotalPembelian(totalHarga);

        pembelianService.addPembelian(pembelian);
        model.addAttribute("pesan", String.format("Pembelian dengan nomor invoice %s berhasil ditambahkan!",
                pembelian.getNomorInvoice()));
        return "info";
    }

    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        List<PembelianModel> listPembelian = pembelianService.getListPembelian();
        List<Integer> listKuantitas = new ArrayList<>();
        for (PembelianModel pembelian : listPembelian) {
            listKuantitas.add(pembelianService.getJumlahBarangPembelian(pembelian));
        }
        model.addAttribute("listPembelian", listPembelian);
        model.addAttribute("listKuantitas", listKuantitas);
        return "viewall-pembelian";
    }

    @GetMapping("/pembelian/{idPembelian}")
    public String viewDetailPembelian(@PathVariable Long idPembelian, Model model) {
        PembelianModel pembelian = pembelianService.getPembelianByIdPembelian(idPembelian);
        int jumlahBarang = pembelianService.getJumlahBarangPembelian(pembelian);
        List<BarangModel> listBarangPembelian = pembelianService.getListBarangPembelian(pembelian);
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("listBarangPembelian", listBarangPembelian);
        return "detail-pembelian";
    }
}
