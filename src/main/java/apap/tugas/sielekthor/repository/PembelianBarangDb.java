package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PembelianBarangDb extends JpaRepository<PembelianBarangModel, Long> {
    List<PembelianBarangModel> findAllByPembelian(PembelianModel pembelian);
    Optional<PembelianBarangModel> findByBarangAndPembelian(BarangModel barang, PembelianModel pembelian);
}
