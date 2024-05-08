package com.crea.cadastro.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "AutenticacaoUsuarioCrea")
public class CredenciasAutentications {

    @Id
    @GeneratedValue(strategy = GeneratenType.IDENTITY)
    private int id;
    private String nomeUsuario;
    private String password;

    public CredenciasAutentications(int id, String nomeUsuario, String password) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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
        final CredenciasAutentications other = (CredenciasAutentications) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "CredenciasAutentications{" + "id=" + id + ", "
                + "nomeUsuario=" + nomeUsuario + ", "
                + "password=" + password + '}';
    }

}
