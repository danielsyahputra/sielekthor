package apap.tugas.sielekthor.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pembelian")
public class PembelianModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembelian;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_admin", nullable = false)
    private String namaAdmin;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date tanggalPembelian;

    @NotNull
    @Column(name = "total_pembelian", nullable = false)
    private Integer totalPembelian;

    @NotNull
    @Column(name = "is_cash", nullable = false)
    private Integer isCash;

    @OneToMany(mappedBy = "pembelian")
    List<PembelianModel> kuantitas;
}
