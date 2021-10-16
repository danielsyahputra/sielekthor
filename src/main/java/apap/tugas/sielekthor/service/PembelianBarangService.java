package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;

import java.util.List;

public interface PembelianBarangService {
    List<PembelianBarangModel> findPembelianBarangByPembelian(PembelianModel pembelian);
    PembelianBarangModel getPembelianBarangByBarang(BarangModel barang, PembelianModel pembelian);
}
