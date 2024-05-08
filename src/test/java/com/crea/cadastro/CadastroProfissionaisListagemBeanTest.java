
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import com.crea.cadastro.Models.CadastroProfissionais;
import com.crea.cadastro.ManagedBean.CadastroProfissionaisListagemBean;
import com.crea.cadastro.Services.CadastroProfissionaisService;

import static org.mockito.Mockito.*;

public class CadastroProfissionaisListagemBeanTest {

    @InjectMocks
    private CadastroProfissionaisListagemBean listagemBean;

    @Mock
    private CadastroProfissionaisService cadastroProfissionaisService;

    @Mock
    private FacesContext facesContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(facesContext.addMessage(any(), any(FacesMessage.class))).thenReturn(null);
    }

    @Test
    public void testSaveProfissional_Success() {
        // Arrange
        CadastroProfissionais profissional = new CadastroProfissionais();
        when(cadastroProfissionaisService.postProfissional(profissional)).thenReturn(profissional);

        // Act
        listagemBean.setSelectProfissional(profissional);
        listagemBean.saveProfissional();

        // Assert
        verify(cadastroProfissionaisService, times(1)).postProfissional(profissional);
        verify(facesContext, times(1)).addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Profissional salvo com sucesso."));
    }

    @Test
    public void testSaveProfissional_Failure() {
        // Arrange
        when(cadastroProfissionaisService.postProfissional(null)).thenThrow(new RuntimeException("Erro"));

        // Act
        listagemBean.saveProfissional();

        // Assert
        verify(cadastroProfissionaisService, never()).postProfissional(any());
        verify(facesContext, times(1)).addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não foi possível salvar o profissional. Profissional selecionado está nulo."));
    }

    @Test
    public void testDeleteProfissional_Success() {
        // Arrange
        CadastroProfissionais profissional = new CadastroProfissionais();
        profissional.setId(1);
        when(cadastroProfissionaisService.deleteProfissionalById(1)).thenReturn(null);

        // Act
        listagemBean.setSelectProfissional(profissional);
        listagemBean.deleteProfissional();

        // Assert
        verify(cadastroProfissionaisService, times(1)).deleteProfissionalById(1);
        verify(facesContext, times(1)).addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Profissional excluído com sucesso."));
    }

    @Test
    public void testDeleteProfissional_Failure() {
        // Arrange
        when(cadastroProfissionaisService.deleteProfissionalById(0)).thenThrow(new RuntimeException("Erro"));

        // Act
        listagemBean.deleteProfissional();

        // Assert
        verify(cadastroProfissionaisService, never()).deleteProfissionalById(0);
        verify(facesContext, times(1)).addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não foi possível excluir o profissional. Profissional selecionado está nulo."));
    }
}
