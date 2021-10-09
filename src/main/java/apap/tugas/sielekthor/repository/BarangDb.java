package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarangDb extends JpaRepository<BarangModel, Long> {
    Optional<BarangModel> findByIdBarang(Long idBarang);
}
