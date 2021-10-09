package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Override
    public void addBarang(BarangModel barang) {
        barangDb.save(barang);
    }
}
