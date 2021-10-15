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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


}
