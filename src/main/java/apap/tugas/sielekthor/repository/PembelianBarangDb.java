package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PembelianBarangDb extends JpaRepository<PembelianBarangModel, Long> {
    List<PembelianBarangModel> findAllByPembelian(PembelianModel pembelian);
}
