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
@Table(name = "kuantitas_pembelian_barang")
public class KuantitasPembelianModel implements Serializable {
    @EmbeddedId
    private KuantitaPembelianKey idKuantitasPembelian;

    @ManyToOne
    @MapsId("idBarang")
    @JoinColumn(name = "id_barang")
    private MemberModel barang;

    @ManyToOne
    @MapsId("idPembelian")
    @JoinColumn(name = "id_pembelian")
    private PembelianModel pembelian;
}
