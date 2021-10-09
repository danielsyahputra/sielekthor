package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class KuantitasPembelianModel implements Serializable {
    @EmbeddedId
    private KuantitaPembelianKey idKuantitasPembelian;

    @ManyToOne
    @MapsId("idMember")
    @JoinColumn(name = "id_member")
    private MemberModel member;

    @ManyToOne
    @MapsId("idPembelian")
    @JoinColumn(name = "id_pembelian")
    private PembelianModel pembelian;
}
