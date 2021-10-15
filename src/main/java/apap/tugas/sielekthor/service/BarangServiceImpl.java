package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    public BarangModel ubahBarang(BarangModel barang) {
        barangDb.save(barang);
        return barang;
    }

    @Override
    public List<BarangModel> getListBarangByTipeAndAvailable(TipeModel tipe, Integer isAvailable) {
        List<BarangModel> listBarang = new ArrayList<>();
        for (BarangModel barang : barangDb.findAllByTipe(tipe)) {
            if (isAvailable.equals(1) && barang.getStokBarang() > 0) listBarang.add(barang);
            if (isAvailable.equals(0) && barang.getStokBarang() <= 0) listBarang.add(barang);
        }
        return listBarang;
    }

}
