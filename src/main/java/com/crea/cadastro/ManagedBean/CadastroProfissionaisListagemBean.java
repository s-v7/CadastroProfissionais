
import javax.inject.Named;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.crea.cadastro.Services.CadastroProfissionaisService;
import com.crea.cadastro.Models.CadastroProfissionais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
@Component
public class CadastroProfissionaisListagemBean implements Serializable {

    @Autowired
    private CadastroProfissionaisService cadastroProfissionaisService;

    private List<CadastroProfissionais> profissionais;

    private CadastroProfissionais selectProfissional;

    public CadastroProfissionaisListagemBean() {
        profissionais = cadastroProfissionaisService.getAllProfissionais();
    }

    // Getters e Setters
    public List<CadastroProfissionais> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<CadastroProfissionais> profissionais) {
        this.profissionais = profissionais;
    }

    public CadastroProfissionais getSelectProfissional() {
        return selectProfissional;
    }

    public void setSelecProfissional(CadastroProfissionais selectProfissional) {
        this.selectProfissional = selectProfissional;
    }

    public void saveProfissional() {
        if (selectProfissional != null) {
            cadastroProfissionaisService.postProfissional(selectProfissional);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Profissional salvo com sucesso."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não foi possível salvar o profissional. Profissional selecionado está nulo."));
        }
    }

    public void deleteProfissional() {
        if (selectProfissional != null) {
            cadastroProfissionaisService.deleteProfissionalById(selectProfissional.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Profissional excluído com sucesso."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não foi possível excluir o profissional. Profissional selecionado está nulo."));
        }
    }
}
