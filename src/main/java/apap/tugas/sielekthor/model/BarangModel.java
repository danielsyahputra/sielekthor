package apap.tugas.sielekthor.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "barang")
public class BarangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarang;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @NotNull
    @Size(max = 100)
    @Column(name = "deskripsi_barang", nullable = false)
    private String deskripsiBarang;

    @NotNull
    @Size(max = 30)
    @Column(name = "kode_barang", nullable = false)
    private String kodeBarang;

    @NotNull
    @Size(max = 30)
    @Column(name = "merk_barang", nullable = false)
    private String merkBarang;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private Integer hargaBarang;

    @NotNull
    @Column(name = "stok_barang", nullable = false)
    private Integer stokBarang;

    @NotNull
    @Column(name = "jumlah_garansi", nullable = false)
    private Integer jumlahGaransi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "idTipe", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipe;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<KuantitasPembelianModel> listKuantitas;
}
