package com.crea.cadastro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import com.crea.cadastro.Models.CadastroProfissionais;
import com.crea.cadastro.Services.CadastroProfissionaisService;
import com.crea.cadastro.ManagedBean.CadastroProfissionaisBean;


public class CadastroProfissionaisBeanTest {

    @InjectMocks
    private CadastroProfissionaisBean profissionaisBean;

    @Mock
    private CadastroProfissionaisService cadastroProfissionaisService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastrarProfissional_Success() {
        // Arrange
        CadastroProfissionais novoProfissional = new CadastroProfissionais();
        doNothing().when(cadastroProfissionaisService).postProfissional(novoProfissional);

        // Act
        profissionaisBean.setNovoProfissional(novoProfissional);
        profissionaisBean.cadastrarProfissional();

        // Assert
        verify(cadastroProfissionaisService, times(1)).postProfissional(novoProfissional);
        assertNull(profissionaisBean.getNovoProfissional());
    }

    @Test
    public void testCadastrarProfissional_Failure() {
        // Arrange
        CadastroProfissionais novoProfissional = new CadastroProfissionais();
        doThrow(new RuntimeException("Erro ao cadastrar profissional")).when(cadastroProfissionaisService).postProfissional(novoProfissional);

        // Act & Assert
        profissionaisBean.setNovoProfissional(novoProfissional);
        profissionaisBean.cadastrarProfissional();
        assertNotNull(profissionaisBean.getNovoProfissional());
    }
}
