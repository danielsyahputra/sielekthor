package apap.tugas.sielekthor.model;

<<<<<<< HEAD
public class TipeModel {
=======
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tipe")
public class TipeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipe;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_tipe", nullable = false)
    private String namaTipe;

    @NotNull
    @Size(max = 100)
    @Column(name = "deskripsi_tipe", nullable = false)
    private String deskripsiTipe;

    @OneToMany(mappedBy = "tipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BarangModel> listBarang;
>>>>>>> aea12075ce9706571f6bdfdf81cccacc844dbe38
}
