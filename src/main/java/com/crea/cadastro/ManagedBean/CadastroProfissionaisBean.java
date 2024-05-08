package com.crea.cadastro.ManagedBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crea.cadastro.Services.CadastroProfissionaisService;
import com.crea.cadastro.Models.CadastroProfissionais;
import com.crea.cadastro.Models.CadastroProfissionaisTitulo;

@ManagedBean
@ViewScoped
@Component
public class CadastroProfissionaisBean implements Serializable {

    @Autowired
    private CadastroProfissionaisService cadastroProfissionaisService;
    @Autowired
    private CadastroProfissionais novoProfissional;
    @Autowired
    private CadastroProfissionaisTitulo cadastroProfissionaisTitulo;

    public CadastroProfissionaisBean() {
        novoProfissional = new CadastroProfissionais();
        cadastroProfissionaisTitulo = new CadastroProfissionaisTitulo();
    }

    // Método cadastrar um novo profissional
    public void cadastrarProfissional() {
        try {
            cadastroProfissionaisService.postProfissional(novoProfissional);
            novoProfissional = new CadastroProfissionais(); // Limpa os campos após o cadastro
            System.out.println("Profissional cadastrado com sucesso: " + profissional);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
