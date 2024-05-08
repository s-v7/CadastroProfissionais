package com.crea.cadastro.Models;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CadastroProfissionais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipoCadastro;
    private String nome;
    private String cpf; // Armazenar cpf criptografado
    private String email;
    private String password; // Armazenar a senha criptografada
    private LocalDate dataNascimento;
    private String telefone;

    public CadastroProfissionais(int id, String tipoCadastro, String nome,
            String cpf, String email, String password, LocalDate dataNascimento,
            String telefone) {
        this.id = id;
        this.tipoCadastro = tipoCadastro;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
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
        final CadastroProfissionais other = (CadastroProfissionais) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "CadastroProfissionais{" + "id=" + id + ", "
                + "tipoCadastro=" + tipoCadastro + ", "
                + "nome=" + nome + ", "
                + "cpf=" + cpf + ", "
                + "email=" + email + ", "
                + "password=" + password + ", "
                + "dataNascimento=" + dataNascimento + ", "
                + "telefone=" + telefone + '}';
    }

}
