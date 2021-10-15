package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;

import java.util.List;

public interface BarangService {
    void addBarang(BarangModel barang);
    List<BarangModel> getListBarang();
    BarangModel getBarangByIdBarang(Long idBarang);
    BarangModel ubahBarang(BarangModel barang);
    List<BarangModel> getListBarangByTipeAndAvailable(TipeModel tipe, Integer isAvailable);
}
