
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CadastroProfissionaisTitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private CadastroProfissionais cadastroProfissionais;

    private String titulo;

    public CadastroProfissionaisTitulo(int id, CadastroProfissionais cadastroProfissionais, String titulo) {
        this.id = id;
        this.cadastroProfissionais = cadastroProfissionais;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CadastroProfissionais getCadastroProfissionais() {
        return cadastroProfissionais;
    }

    public void setCadastroProfissionais(CadastroProfissionais cadastroProfissionais) {
        this.cadastroProfissionais = cadastroProfissionais;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CadastroProfissionaisTitulo other = (CadastroProfissionaisTitulo) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "CadastroProfissionaisTitulo{" + "id=" + id + ", "
                + "cadastroProfissionais=" + cadastroProfissionais + ", "
                + "titulo=" + titulo + '}';
    }

}
