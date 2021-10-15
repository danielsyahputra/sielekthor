package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.PembelianModel;

import java.util.List;

public interface PembelianService {
    void addPembelian(PembelianModel pembelian);
    List<PembelianModel> getListPembelian();
}
