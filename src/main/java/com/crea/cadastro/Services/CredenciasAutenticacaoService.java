
import javax.naming.AuthenticationException;


@Service
public class CredenciasAutenticacaoService implements AuthenticationProvider {

    @Autowired
    private CredenciasAutenticationsRepository credenciasAutenticationsRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws javax.security.sasl.AuthenticationException {
        String nomeUsuario = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<CredenciasAutenticacao> credencial = credenciasAutenticationsRepository.findByNomeUsuario(nomeUsuario);
        if (credencial.isPresent()) {
            CredenciasAutenticacao credenciasAutenticacao = credencial.get();
            if (passwordEncoder().matches(password, credenciasAutenticacao.getPassword())) {
                return new UsernamePasswordAuthenticationToken(nomeUsuario, password, credenciasAutenticacao.getAuthorities());
            } else {
                throw new BadCredentialsException("Senha inválida");
            }
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

    }

    @Override
    public boolean credencialSuporte(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Bean
    public PasswordEncoder codificarSenha() {
        return new BCryptPasswordEncoder();
    }

}
