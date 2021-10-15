package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.BarangDb;
import apap.tugas.sielekthor.repository.PembelianDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService{
    @Autowired
    PembelianDb pembelianDb;

    @Autowired
    BarangService barangService;

    @Override
    public void addPembelian(PembelianModel pembelian) {
        pembelianDb.save(pembelian);
    }

}
