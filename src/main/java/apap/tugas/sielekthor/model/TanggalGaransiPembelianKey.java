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
public class TanggalGaransiPembelianKey implements Serializable {
    @Column(name = "id_barang")
    private Long idBarang;

    @Column(name = "id_pembelian")
    private Long idPembelian;
}
