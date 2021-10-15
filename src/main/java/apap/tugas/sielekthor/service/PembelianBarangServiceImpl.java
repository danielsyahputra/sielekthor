package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PembelianBarangServiceImpl implements PembelianBarangService{
    @Autowired
    PembelianBarangDb pembelianBarangDb;

    @Override
    public List<PembelianBarangModel> findPembelianBarangByPembelian(PembelianModel pembelian) {
        return pembelianBarangDb.findAllByPembelian(pembelian);
    }
}
