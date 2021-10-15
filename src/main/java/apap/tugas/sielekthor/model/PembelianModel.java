package apap.tugas.sielekthor.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate tanggalPembelian;

    @NotNull
    @Column(name = "total_pembelian", nullable = false)
    private Integer totalPembelian = 0;

    @NotNull
    @Column(name = "is_cash", nullable = false)
    private Integer isCash;

    @NotNull
    @Size(max = 13)
    @Column(name = "nomor_invoice", nullable = false)
    private String nomorInvoice = String.format("INV%d", this.idPembelian);

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_member", referencedColumnName = "idMember", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberModel member;

    @OneToMany(mappedBy = "pembelian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<KuantitasPembelianModel> listBarang;
}
