
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.crea.cadastro.Models.CadastroProfissionais;
import com.crea.cadastro.Repositorys.CadastroProfissionaisRepository;
import com.crea.cadastro.Services.CadastroProfissionaisService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CadastroProfissionaisServiceTest {

    private CadastroProfissionaisRepository cadastroProfissionaisRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private CadastroProfissionaisService cadastroProfissionaisService;

    @BeforeEach
    public void setUp() {
        cadastroProfissionaisRepository = mock(CadastroProfissionaisRepository.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        cadastroProfissionaisService = new CadastroProfissionaisService(cadastroProfissionaisRepository, passwordEncoder);
    }

    @Test
    public void testGetProfissionalById() {
        int profissionalId = 1;
        CadastroProfissionais profissionalMock = new CadastroProfissionais();
        profissionalMock.setId(profissionalId);
        when(cadastroProfissionaisRepository.findById(profissionalId)).thenReturn(java.util.Optional.of(profissionalMock));

        ResponseEntity<CadastroProfissionais> response = cadastroProfissionaisService.getProfissionalById(profissionalId);

        assertEquals(profissionalId, response.getBody().getId());
    }

    @Test
    public void testPostProfissional() {
        //Dados
        CadastroProfissionais newProfissional = new CadastroProfissionais();
        newProfissional.setNome("José Oquendo");
        newProfissional.setEmail("jaoquendof@gmail.com");
        newProfissional.setCpf("12345678901");
        newProfissional.setDataNascimento(LocalDate.of(1990, 1, 1));
        newProfissional.setPassword("senha");

        when(passwordEncoder.encode(newProfissional.getCpf())).thenReturn("encryptCPF");
        when(passwordEncoder.encode(newProfissional.getPassword())).thenReturn("encryptPassword");
        when(cadastroProfissionaisRepository.save(any(CadastroProfissionais.class))).thenReturn(newProfissional);

        ResponseEntity<CadastroProfissionais> response = cadastroProfissionaisService.postProfissional(newProfissional);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newProfissional, response.getBody());
    }

    @Test
    public void testPutProfissional() {
        CadastroProfissionais updateProfissional = new CadastroProfissionais();
        updateProfissional.setId(1);
        updateProfissional.setNome("Silas Vasconcelos");
        updateProfissional.setEmail("svasconceloscruz7@example.com");
        updateProfissional.setCpf("12345678901");
        updateProfissional.setDataNascimento(LocalDate.of(1990, 1, 1));

        when(cadastroProfissionaisRepository.existsById(updateProfissional.getId())).thenReturn(true);
        when(passwordEncoder.encode(updateProfissional.getCpf())).thenReturn("encryptCPFF");
        when(cadastroProfissionaisRepository.save(any(CadastroProfissionais.class))).thenReturn(updateProfissional);

        ResponseEntity<CadastroProfissionais> response = cadastroProfissionaisService.putProfissional(updateProfissional);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateProfissional, response.getBody());
    }
}
