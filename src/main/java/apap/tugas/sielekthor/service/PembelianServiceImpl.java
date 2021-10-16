package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.BarangDb;
import apap.tugas.sielekthor.repository.PembelianDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService{
    @Autowired
    PembelianDb pembelianDb;

    @Autowired
    PembelianBarangService pembelianBarangService;

    @Override
    public void addPembelian(PembelianModel pembelian) {
        pembelianDb.save(pembelian);
    }

    @Override
    public List<PembelianModel> getListPembelian() {
        return pembelianDb.findAll();
    }

    @Override
    public PembelianModel getPembelianByIdPembelian(Long idPembelian) {
        Optional<PembelianModel> pembelian = pembelianDb.findByIdPembelian(idPembelian);
        if (pembelian.isPresent()) return pembelian.get();
        return null;
    }

    @Override
    public Integer getJumlahBarangPembelian(PembelianModel pembelian) {
        int jumlahBarang = 0;
        for (PembelianBarangModel pembelianBarang:
                pembelianBarangService.findPembelianBarangByPembelian(pembelian)) {
            jumlahBarang += pembelianBarang.getKuantitas();
        }
        return jumlahBarang;
    }

    @Override
    public List<BarangModel> getListBarangPembelian(PembelianModel pembelian) {
        List<BarangModel> listBarangPembelian = new ArrayList<>();
        for (PembelianBarangModel pembelianBarang :
                pembelianBarangService.findPembelianBarangByPembelian(pembelian)) {
            listBarangPembelian.add(pembelianBarang.getBarang());
        }
        return listBarangPembelian;
    }

    @Override
    public void hapusPembelian(PembelianModel pembelian) {
        pembelianDb.delete(pembelian);
    }

    @Override
    public List<PembelianModel> cariPembelianBerdasarkanMemberDanPembayaran(MemberModel member, Integer isCash) {
        return pembelianDb.findAllByMemberAndIsCash(member, isCash);
    }

    @Override
    public List<PembelianModel> cariPembelianBerdasarkanMember(MemberModel member) {
        return pembelianDb.findAllByMember(member);
    }

    @Override
    public List<PembelianModel> cariPembelianBerdasarkanIsCash(Integer isCash) {
        return pembelianDb.findAllByIsCash(isCash);
    }

    @Override
    public void generateNomorInvoicePembelian(PembelianModel pembelian) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        Random rnd = new Random();

        String nomorInvoice = "";
        String namaAdmin = pembelian.getNamaAdmin().toUpperCase();
        LocalDate tanggalPembelian = pembelian.getTanggalPembelian();
        int asciiFirstChar = ((int) namaAdmin.charAt(0)) - 64;
        char firstCharAdmin = String.valueOf(asciiFirstChar).charAt(0);
        char lastCharAdmin = namaAdmin.charAt(namaAdmin.length() - 1);
        String stringTanggalPembelian = formatter.format(tanggalPembelian);
        String stringPembayaran = (pembelian.getIsCash().equals(1)) ? "02" : "01";
        int tanggal = Integer.parseInt(stringTanggalPembelian.substring(0,2));
        int bulan = Integer.parseInt(stringTanggalPembelian.substring(2));
        String tanggalKaliBulan = String.format("%03d", (tanggal + bulan) * 5);
        char randomChar1 = (char) ('A' + rnd.nextInt(26));
        char randomChar2 = (char) ('A' + rnd.nextInt(26));

        // Assign nomor invoice
        nomorInvoice = String.format("%c%c%s%s%s%c%c", firstCharAdmin,
                lastCharAdmin, stringTanggalPembelian, stringPembayaran,
                tanggalKaliBulan, randomChar1, randomChar2);
        pembelian.setNomorInvoice(nomorInvoice);
    }

}
