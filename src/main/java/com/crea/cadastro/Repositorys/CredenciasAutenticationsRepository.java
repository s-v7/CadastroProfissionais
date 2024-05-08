package com.crea.cadastro.Repositorys;
package com.crea.cadastro.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenciasAutenticationsRepository extends JpaRepository<CredenciasAutentications, int> {

    CredenciasAutentications findByNomeUsuario(String nomeUsuario);
}
