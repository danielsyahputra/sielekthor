package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class KuantitaPembelianKey implements Serializable {
    @Column(name = "id_member")
    private Long idMember;

    @Column(name = "id_pembelian")
    private Long idPembelian;
}
