package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PembelianDb extends JpaRepository<PembelianModel, Long> {
    Optional<PembelianModel> findByIdPembelian(Long idPembelian);
    List<PembelianModel> findAllByMemberAndIsCash(MemberModel member, Integer isCash);
    List<PembelianModel> findAllByMember(MemberModel member);
    List<PembelianModel> findAllByIsCash(Integer isCash);
}
