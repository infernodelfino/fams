package fa.training.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import fa.training.controller.LoginController;
import fa.training.service.impl.AccountDetailsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestPropertySource("/application.properties")
public class LoginPageTest {
  private MockMvc mockMvc;

  @InjectMocks
  private LoginController loginController;

  @Mock
  private AccountDetailsServiceImpl accountDetailsService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    MockitoAnnotations.initMocks(this);
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/pages/views/");
    resolver.setSuffix(".jsp");
    mockMvc = MockMvcBuilders.standaloneSetup(loginController)
        .setViewResolvers(resolver).build();
  }

  @Test
  @WithMockUser(roles = "REC")
  public void givenManagerUser_whenGetFooSalute_thenOk() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders
            .get("/trainee-management/view/trainee-profile-ajax"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
            MockMvcResultMatchers.view().name("trainee-profile-view-ajax"));
  }

  @Test
  public void loadLoginPageLoggedin() throws Exception {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("MANAGER"));
    User user = new User("Quyen123", "123456", grantedAuthorities);

    Authentication authen = new Authentication() {
      private static final long serialVersionUID = 1L;

      @Override
      public String getName() {
        return user.getUsername();
      }

      @Override
      public void setAuthenticated(boolean isAuthenticated)
          throws IllegalArgumentException {
      }

      @Override
      public boolean isAuthenticated() {
        return true;
      }

      @Override
      public Object getPrincipal() {
        return user;
      }

      @Override
      public Object getDetails() {
        return null;
      }

      @Override
      public Object getCredentials() {
        return null;
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
      }
    };
    mockMvc.perform(MockMvcRequestBuilders.get("/login").principal(authen))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("landing-page"));
  }

  @Test
  public void loadLoginPage() throws Exception {
    
    mockMvc.perform(MockMvcRequestBuilders.get("/login"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("login"));
  }

  @Test
  public void actionLogout() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/login?notify=logout"));

  }

  @Test
  public void loadPage403() throws Exception {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("MANAGER"));
    User user = new User("Quyen123", "123456", grantedAuthorities);

    Authentication authen = new Authentication() {
      private static final long serialVersionUID = 1L;

      @Override
      public String getName() {
        return user.getUsername();
      }

      @Override
      public void setAuthenticated(boolean isAuthenticated)
          throws IllegalArgumentException {
      }

      @Override
      public boolean isAuthenticated() {
        return true;
      }

      @Override
      public Object getPrincipal() {
        return user;
      }

      @Override
      public Object getDetails() {
        return null;
      }

      @Override
      public Object getCredentials() {
        return null;
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
      }
    };

    mockMvc.perform(MockMvcRequestBuilders.get("/page-403").principal(authen))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("page-403"));

  }

}
