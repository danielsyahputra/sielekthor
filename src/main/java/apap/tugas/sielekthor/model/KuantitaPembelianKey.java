package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KuantitaPembelianKey implements Serializable {

    @Column(name = "id_barang")
    private Long idBarang;

    @Column(name = "id_pembelian")
    private Long idPembelian;

    public Long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public Long getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(Long idPembelian) {
        this.idPembelian = idPembelian;
    }

    @Override
    public  boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KuantitaPembelianKey that = (KuantitaPembelianKey) o;
        return idBarang.equals(that.idBarang) && idPembelian.equals(that.idPembelian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBarang, idPembelian);
    }
}
