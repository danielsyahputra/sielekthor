package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PembelianBarangServiceImpl implements PembelianBarangService{
    @Autowired
    PembelianBarangDb pembelianBarangDb;

    @Override
    public List<PembelianBarangModel> findPembelianBarangByPembelian(PembelianModel pembelian) {
        return pembelianBarangDb.findAllByPembelian(pembelian);
    }

    @Override
    public PembelianBarangModel getPembelianBarangByBarang(BarangModel barang, PembelianModel pembelian) {
        Optional<PembelianBarangModel> pembelianBarang = pembelianBarangDb.findByBarangAndPembelian(barang, pembelian);
        if (pembelianBarang.isPresent()) return pembelianBarang.get();
        return null;
    }
}
