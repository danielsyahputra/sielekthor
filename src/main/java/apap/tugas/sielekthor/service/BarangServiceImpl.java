package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Override
    public void addBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public List<BarangModel> getListBarang() {
        return barangDb.findAll();
    }

    @Override
    public BarangModel getBarangByIdBarang(Long idBarang) {
        Optional<BarangModel> barang = barangDb.findByIdBarang(idBarang);
        if (barang.isPresent()) return barang.get();
        return null;
    }
}
