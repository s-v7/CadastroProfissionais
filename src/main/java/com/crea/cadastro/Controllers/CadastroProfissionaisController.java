
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.crea.cadastro.Services.CadastroProfissionaisService;
import com.crea.cadastro.Models.CadastroProfissionais;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroProfissionaisController {

    @Autowired
    private CadastroProfissionaisService cadastroProfissionaisService;

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @ApiOperation(value = "Buscar todos os Profissionais Cadastrados", notes = "Retorna uma lista de todos os profissionais cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<CadastroProfissionais>> getAllProfissionais() {
        List<CadastroProfissionais> profissionaisAll = cadastroProfissionaisService.getAllProfissionais();
        return ResponseEntity.ok().body(profissionaisAll);
    }

    @ApiOperation(value = "Buscar um Profissional Cadastrado", notes = "Retorna um profissional com base no ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<CadastroProfissionais> getProfissionalById(@ApiParam(value = "ID do Profissional", required = true) @PathVariable int id) {
        CadastroProfissionais profissional = cadastroProfissionaisService.getProfissionalById(id);
        if (profissional == null) {
		return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(profissional);
    }
   
    @ApiOperation(value = "Cadastrar Novo Profissional", notes = "Cadastra um novo profissional no sistema.")
    @PostMapping
    public ResponseEntity<CadastroProfissionais> createProfissional(@ApiParam(value = "Novo Profissional", required = true) @RequestBody CadastroProfissionais newProfissional) 
        if (newProfissional == null) {
		return ResponseEntity.badRequest().build();
        }
        CadastroProfissionais createdProfissional = cadastroProfissionaisService.postProfissional(newProfissional);
        return ResponseEntity.status (HttpStatus.CREATED).body(createdProfissional);
    }

    @ApiOperation(value = "Atualizar um Profissional Cadastrado", notes = "Atualiza as informações de um profissional existente.")@PutMapping("/{id}")
    public ResponseEntity<CadastroProfissionais> updateProfissional(@ApiParam(value = "ID do Profissional", required = true)@PathVariable int id,
                                                                @ApiParam(value = "Profissional Atualizado", required = true)
                                                                @RequestBody CadastroProfissionais updatedProfissional) {
        CadastroProfissionais existeCadastro = cadastroProfissionaisService.getProfissionalById(id);
        if(existeCadastro == null) {
        	return ResponseEntity.notFound().build();
        }
	// Verifica se há mudanças no CPF e senha antes de criptografar novamente
	if(!existeCadastro.getCpf().equals(updatedProfissional.getCpf()) {
		updatedProfissional.setCpf(passwordEncoder.encode(updatedProfissional.getCpf()));
	}
	if (!existeCadastro.getPassword().equals(updatedProfissional.getPassword())) {
		updatedProfissional.setPassword(passwordEncoder.encode(updatedProfissional.getPassword()));
	}
	/* Atualizar campos necessário*/
	existeCadastro.setTipoCadastro(updatedProfissional.getTipoCadastro());
	existeCadastro.setNome(updatedProfissional.getNome());
        existeCadastro.setEmail(updatedProfissional.getEmail());
        existeCadastro.setDataNascimento(updatedProfissional.getDataNascimento());
        existeCadastro.setTelefone(updatedProfissional.getTelefone());
        existeCadastro.setTitulos(updatedProfissional.getTitulos());	
        CadastroProfissionais updatedProfissional = cadastroProfissionaisService.putProfissional(existeCadastro);
        return ResponseEntity.ok().body(updatedProfissional);	
    }

	
     @ApiOperation(value = "Deletar um Profissional Cadastrado", notes = "Exclui um profissional do sistema com base no ID fornecido.")
     @DeleteMapping("{id}")
     public ResponseEntity<?> deleteCadastroProfissional(@ApiParam(value = "ID do Profissional", required = true)@PathVariable int id){
	CadastroProfissionais existeCadastro = cadastroProfissionaisService.getProfissionalById(id);		
        if(existeCadastro == null) {
		return ResponseEntity.notFound().build();
        }
        cadastroProfissionaisService.deleteProfissional(id);
        return ResponseEntity.ok().build();
      }
}
