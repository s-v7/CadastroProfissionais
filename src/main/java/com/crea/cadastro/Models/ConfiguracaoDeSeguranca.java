
@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredenciasAutenticacaoService credenciasAutenticacaoService;

    @Override
    protected void configuracao(AuthenticationManagerBuilder autenticao) throws Exception {
        autenticao.authenticationProvider(credenciasAutenticacaoService);
    }

    @Override
    protected void configurar(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
