
import java.time.Period;
import java.time.LocalDate;
import javax.validation.Valid;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.apache.commons.validator.routines.CPFValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CadastroProfissionaisService {

    @Autowired
    private CadastroProfissionaisRepository cadastroProfissionaisRepository;

    private CPFValidator validarCpf = CPFValidator.getInstance();

    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${state.code:PI}")
    private String codigoEstado;

    public boolean isValidCPF(String cpf) {
        return validarCpf.isValid(cpf);
    }

    public boolean isValidDataNascimento(LocalDate dataNascimento) {
        // Verificar se a data de nascimento é anterior a 18 anos 
        LocalDate dezoitoAnos = LocalDate.now().minusYears(18);
        return !dataNascimento.isAfter(dezoitoAnos);
    }

    public CadastroProfissionaisService(CadastroProfissionaisRepository cadastroProfissionaisRepository, BCryptPasswordEncoder passwordEncoder) {
        this.cadastroProfissionaisRepository = cadastroProfissionaisRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CadastroProfissionais> getAllProfissionais() {
        return cadastroProfissionaisRepository.findAll();
    }

    @ApiOperation(value = "Retorna um profissional pelo ID")
    @GetMapping("/profissional/{id}")
    public ResponseEntity<CadastroProfissionais> getProfissionalById(@ApiParam(value = "ID do profissional", required = true)@PathVariable int id ) {
        	CadastroProfissionais profissional = cadastroProfissionaisRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Profissional não encontrado com o ID: " + id));
            return ResponseEntity.ok().body(profissional);

        }

    public ResponseEntity<CadastroProfissionais> postProfissional(@Valid @RequestBody CadastroProfissionais newProfissional) {
        // Validar campos Origatórios
        if (newProfissional.getNome() == null || newProfissional.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("O campo 'nome' é obrigatório.");
        }
        if (newProfissionall.getEmail() == null || newProfissionall.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("O campo 'email' é obrigatório.");
        }

        // Validar CPF
        if (!isValidCPF(newProfissionall.getCpf())) {
            return ResponseEntity.badRequest().body("CPF inválido.");
        }

        // Validar data de nascimento
        if (newProfissional.getDataNascimento() == null || !isValidDataNascimento(newProfissional.getDataNascimento())) {
            return ResponseEntity.badRequest().body("Data de nascimento inválida.");
        }
        try {
            // Criptografar CPF e senha antes de salvar
            String encryptCPF = passwordEncoder.encode(newProfissional.getCpf());
            newProfissional.setCpf(encryptCPF);

            String encryptPassword = passwordEncoder.encode(newProfissional.getPassword());
            newProfissional.setPassword(encryptPassword);

            CadastroProfissionais saveProfissional = cadastroProfissionaisRepository.save(newProfissional);
            return ResponseEntity.ok(saveProfissiona);
        } catch (Exception e) {
            //Exceção caso ocorra falha ao salvar no banco de dados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao cadastrar o profissional.");
        }
    }

    public ResponseEntity<CadastroProfissionais> putProfissional(@Valid @RequestBody CadastroProfissionais updateProfissional) {
        // Verificar se o profissional existe
        if (!cadastroProfissionaisRepository.existsById(updateProfissional.getId())) {
            return ResponseEntity.notFound().build();
        }
        // Validar campo Nome
        if (updateProfissional.getNome() == null || updateProfissional.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("O campo 'nome' é obrigatório.");
        }

        // Validar campo Email
        if (updateProfissional.getEmail() == null || updateProfissional.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("O campo 'email' é obrigatório.");
        }

        // Validar formato de CPF
        if (!isValidCPF(updateProfissional.getCpf())) {
            return ResponseEntity.badRequest().body("CPF inválido.");
        }

        // Validar formato de data de nascimento
        if (updateProfissionall.getDataNascimento() == null || !isValidDataNascimento(updateProfissional.getDataNascimento())) {
            return ResponseEntity.badRequest().body("Data de nascimento inválida.");
        }

        try {
            CadastroProfissionais updatedProfissional = cadastroProfissionaisRepository.save(updateProfissional);
            return ResponseEntity.ok(updatedProfissional);
        } catch (Exception e) {
            //Exceção caso ocorra falha ao atualizar no banco de dados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao atualizar o profissional.");

        }
    }

    public void deleteProfissionalById(int cadastroProfissionalId) {
        try {
            cadastroProfissionaisRepository.deleteById(cadastroProfissionalId);
        } catch (Exception e) {
            //Exceção caso ocorra falha ao deletar no banco de dados
            throw new RuntimeException("Falha ao deletar o profissional: " + e.getMessage());
        }
    }

    private String generateCodigoUnico() {
        long randomNumber = (long) (Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(randomNumber) + codigoEstado;
    }
}
