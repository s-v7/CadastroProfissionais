
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginBeanTest {

    @InjectMocks
    private LoginBean loginBean;

    @Mock
    private FacesContext facesContext;

    @Mock
    private ExternalContext externalContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(facesContext.getExternalContext()).thenReturn(externalContext);
    }

    @Test
    public void testLoginSuccessful() {
        // Arrange
        loginBean.setUsername("admin");
        loginBean.setPassword("admin");

        // Act
        String outcome = loginBean.login();

        // Assert
        assertEquals("welcome?faces-redirect=true", outcome);
        verify(externalContext, never()).addMessage(any(), any(FacesMessage.class));
    }

    @Test
    public void testLoginFailed() {
        // Arrange
        loginBean.setUsername("user");
        loginBean.setPassword("wrongpassword");

        // Act
        String outcome = loginBean.login();

        // Assert
        assertEquals(null, outcome);
        verify(externalContext).addMessage(eq(null), any(FacesMessage.class));
    }
}
