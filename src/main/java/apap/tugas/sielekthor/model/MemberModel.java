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
@Table(name = "member")
public class MemberModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMember;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_member", nullable = false)
    private String namaMember;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date tanggalLahir;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date tanggalPendaftaran;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PembelianModel> listPembelian;
}