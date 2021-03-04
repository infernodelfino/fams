package fa.training.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import fa.training.controller.DashboardController;
import fa.training.entity.vo.DataVo;
import fa.training.repository.ClassRepository;
import fa.training.service.impl.AccountDetailsServiceImpl;
import fa.training.service.impl.ClassesServiceImpl;
import fa.training.utils.Log4J;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestPropertySource("/application.properties")
public class DashboardControllerTest {
  private MockMvc mockMvc;

  @InjectMocks
  private DashboardController dashboardController;

  @Mock
  private ClassesServiceImpl classesService;

  @Mock
  private ClassRepository classRepository;

  @Mock
  private AccountDetailsServiceImpl userService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();
    MockitoAnnotations.initMocks(this);
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/pages/views/");
    resolver.setSuffix(".jsp");
    mockMvc = MockMvcBuilders.standaloneSetup(dashboardController)
        .setViewResolvers(resolver).build();
  }

  /**
   * This method to test loadLandingPage when anyone logged in.
   */
  @Test
  public void testLoadLandingPage() throws Exception {
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

    DataVo dataVo1 = new DataVo("inprogress", "C#", 2);
    DataVo dataVo2 = new DataVo("inprogress", "JAVA", 1);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    String[] arrayLocation = { "HaNoi,DaNang,HCM" };
    List<String> locations = Arrays.asList(arrayLocation);
    when(classesService.countClassesGroupBySkillByStatusAndLocation("danang",
        "inprogress", "table")).thenReturn(classes);
    when(classesService.getLocations()).thenReturn(locations);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/landing-page").principal(authen))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("classesVo"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("statuses"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("locations"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("roles"))
        .andExpect(MockMvcResultMatchers.view().name("landing-page"));
  }

  /**
   * This method to test loadLandingPage when nobody logged in.
   */
  @Test
  public void testLoadLandingPageNotLoggedIn() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/landing-page"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("login"));
  }

  /**
   * This method to test loadLandingPageAjax display default data filter as
   * table.
   */
  @Test
  public void testLoadLandingPageAjax() throws Exception {
    DataVo dataVo1 = new DataVo("inprogress", "C#", 2);
    DataVo dataVo2 = new DataVo("inprogress", "JAVA", 3);
    DataVo dataVo3 = new DataVo("planning", "JAVA", 5);
    DataVo dataVo4 = new DataVo("planned", "PHP", 3);
    DataVo dataVo5 = new DataVo("planning", "ANDROID", 2);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    classes.add(dataVo3);
    classes.add(dataVo4);
    classes.add(dataVo5);
    String dashboardFor = "class";
    String location = "all";
    String status = "all";
    String type = "table";
    when(classesService.countClassesGroupBySkill(type)).thenReturn(classes);
    List<DataVo> dataVos = dashboardController.getData(dashboardFor, location,
        status, type);
    Log4J.getLogger().info("landing-page: " + dataVos);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/landing-page/ajax"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("classesVo"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("statuses"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("locations"))
        .andExpect(MockMvcResultMatchers.view().name("landing-page-ajax"));
  }

  /**
   * This method to test loadLandingPageTableContent display data filter as
   * table.
   */
  @Test
  public void loadLandingPageTableContent() throws Exception {
    DataVo dataVo1 = new DataVo("inprogress", "JAVA", 1);
    DataVo dataVo2 = new DataVo("inprogress", "C#", 3);
    DataVo dataVo3 = new DataVo("inprogress", "PHP", 2);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    classes.add(dataVo3);
    String dashboardFor = "class";
    String location = "danang";
    String status = "inprogress";
    String type = "table";
    when(classesService.countClassesGroupBySkillByStatusAndLocation(status,
        location, type)).thenReturn(classes);
    List<DataVo> dataVos = dashboardController.getData(dashboardFor, location,
        status, type);
    Log4J.getLogger().info("content as table " + dataVos);
    this.mockMvc.perform(MockMvcRequestBuilders.get(
        "/landing-page/content-table/class?location=danang&status=inprogress"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("statuses"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("locations"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("dataVo"))
        .andExpect(
            MockMvcResultMatchers.view().name("dashboard-content-table"));
  }

  /**
   * This method to test loadLandingPageTableContent when bad request.
   */
  @Test
  public void loadLandingPageTableContentBadRequest() throws Exception {
    DataVo dataVo1 = new DataVo("inprogress", "C#", 2);
    DataVo dataVo2 = new DataVo("inprogress", "JAVA", 1);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    String dashboardFor = "class";
    String location = "danang";
    String status = "inprogress";
    String type = "table";
    when(classesService.countClassesGroupBySkillByStatusAndLocation(status,
        location, type)).thenReturn(classes);
    List<DataVo> dataVos = dashboardController.getData(dashboardFor, location,
        status, type);
    Log4J.getLogger().info("content as table " + dataVos);
    this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/landing-page/content-table/class?status=inprogress"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  /**
   * This method to test return data restful loadLandingPageChartContent.
   */
  @Test
  public void loadLandingPageChartContentBadRequest() throws Exception {
    DataVo dataVo1 = new DataVo("inprogress", "C#", 2, 66.66);
    DataVo dataVo2 = new DataVo("inprogress", "JAVA", 1, 33.37);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    String dashboardFor = "class";
    String location = "danang";
    String status = "inprogress";
    String type = "chart";
    when(classesService.countClassesGroupBySkillByStatusAndLocation(status,
        location, type)).thenReturn(classes);
    List<DataVo> dataVos = dashboardController.getData(dashboardFor, location,
        status, type);
    Log4J.getLogger().info("content as chart " + dataVos);
    assertNotNull(dataVos);
  }
  
}
