package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDb extends JpaRepository<MemberModel, Long> {
    Optional<MemberModel> findByIdMember(Long idMember);
}
