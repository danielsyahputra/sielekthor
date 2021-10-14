package apap.tugas.sielekthor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private BarangModel barang;

    @ManyToOne
    @MapsId("idPembelian")
    @JoinColumn(name = "id_pembelian")
    private PembelianModel pembelian;

    private Integer kuantitas;

    public KuantitasPembelianModel(BarangModel barang, PembelianModel pembelian, Integer kuantitas) {
        this.idKuantitasPembelian = new KuantitaPembelianKey(barang.getIdBarang(), pembelian.getIdPembelian());
        this.barang = barang;
        this.pembelian = pembelian;
        this.kuantitas = kuantitas;
    }
}
