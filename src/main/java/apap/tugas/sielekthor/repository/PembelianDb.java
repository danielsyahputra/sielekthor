package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PembelianDb extends JpaRepository<PembelianModel, Long> {
    Optional<PembelianModel> findByIdPembelian(Long idPembelian);
}
