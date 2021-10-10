package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipeDb extends JpaRepository<TipeModel, Long> {
    Optional<TipeModel> findByIdTipe(Long idTipe);
}
