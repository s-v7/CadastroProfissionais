
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CadastroProfissionaisControllerTest {

    @InjectMocks
    private CadastroProfissionaisController profissionaisController;

    @Mock
    private CadastroProfissionaisService cadastroProfissionaisService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProfissionais() {
        // Arrange
        List<CadastroProfissionais> profissionais = new ArrayList<>();
        // Adicionar profissionais à lista (simulação)
        when(cadastroProfissionaisService.getAllProfissionais()).thenReturn(profissionais);

        // Act
        ResponseEntity<List<CadastroProfissionais>> responseEntity = profissionaisController.getAllProfissionais();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profissionais, responseEntity.getBody());
    }

    @Test
    public void testGetProfissionalById_Success() {
        // Arrange
        int id = 1;
        CadastroProfissionais profissional = new CadastroProfissionais();
        // Simular serviço retornando um profissional
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(profissional);

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.getProfissionalById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profissional, responseEntity.getBody());
    }

    @Test
    public void testGetProfissionalById_NotFound() {
        // Arrange
        int id = 1;
        // Simular serviço não encontrando o profissional
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(null);

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.getProfissionalById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateProfissional_Success() {
        // Arrange
        CadastroProfissionais newProfissional = new CadastroProfissionais();
        when(cadastroProfissionaisService.postProfissional(newProfissional)).thenReturn(newProfissional);

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.createProfissional(newProfissional);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newProfissional, responseEntity.getBody());
    }

    @Test
    public void testCreateProfissional_BadRequest() {
        // Arrange
        CadastroProfissionais newProfissional = null;

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.createProfissional(newProfissional);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateProfissional_Success() {
        // Arrange
        int id = 1;
        CadastroProfissionais updatedProfissional = new CadastroProfissionais();
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(updatedProfissional);
        when(cadastroProfissionaisService.putProfissional(updatedProfissional)).thenReturn(updatedProfissional);

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.updateProfissional(updatedProfissional, id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedProfissional, responseEntity.getBody());
    }

    @Test
    public void testUpdateProfissional_NotFound() {
        // Arrange
        int id = 1;
        CadastroProfissionais updatedProfissional = new CadastroProfissionais();
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(null);

        // Act
        ResponseEntity<CadastroProfissionais> responseEntity = profissionaisController.updateProfissional(updatedProfissional, id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCadastroProfissional_Success() {
        // Arrange
        int id = 1;
        CadastroProfissionais existeCadastro = new CadastroProfissionais();
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(existeCadastro);

        // Act
        ResponseEntity<?> responseEntity = profissionaisController.deleteCadastroProfissional(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCadastroProfissional_NotFound() {
        // Arrange
        int id = 1;
        when(cadastroProfissionaisService.getProfissionalById(id)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = profissionaisController.deleteCadastroProfissional(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
