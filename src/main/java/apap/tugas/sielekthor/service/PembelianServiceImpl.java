package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.KuantitasPembelianModel;
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

    @Override
    public void addPembelian(PembelianModel pembelian) {
        pembelianDb.save(pembelian);
    }

    @Override
    public void setTotalHargaPembelian(PembelianModel pembelian) {
        int totalHarga = 0;
        List<KuantitasPembelianModel> listBarang = pembelian.getListBarang();
        for (KuantitasPembelianModel kuantitasPembelianModel : listBarang) {
            BarangModel barang = kuantitasPembelianModel.getBarang();
            int kuantitasBarang = kuantitasPembelianModel.getKuantitas();
            int hargaBarang = barang.getHargaBarang();
            totalHarga += (hargaBarang * kuantitasBarang);
        }
        pembelian.setTotalPembelian(totalHarga);
    }
}
