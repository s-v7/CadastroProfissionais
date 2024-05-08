
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class LoginBean {

    private String username;
    private String password;

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 79 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoginBean other = (LoginBean) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return (this.password == null) ? (other.password == null) : this.password.equals(other.password);
    }

    @Override
    public String toString() {
        return "LoginBean{" + "username=" + username + ", password=" + password + '}';
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        if ("admin".equals(username) && "admin".equals(password)) {
            // Lógica de autenticação bem-sucedida
            return "listagem?faces-redirect=true";
        } else {
            // Exibir mensagem de erro
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid username or password"));
            return null;
        }
    }
}
