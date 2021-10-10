package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tanggal_garansi_pembelian_barang")
public class TanggalGaransiPembelianModel implements Serializable {
    @EmbeddedId
    private TanggalGaransiPembelianKey idTanggalGaransiPembelian;

    @ManyToOne
    @MapsId("idBarang")
    @JoinColumn(name = "id_barang")
    private BarangModel barang;

    @ManyToOne
    @MapsId("idPembelian")
    @JoinColumn(name = "id_pembelian")
    private PembelianModel pembelian;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date tanggalGaransi;
}
