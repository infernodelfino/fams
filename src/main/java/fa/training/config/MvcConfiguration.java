package fa.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configure MVC model.
 */

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfiguration implements WebMvcConfigurer {

  /**
   * The method will register InternalResourceViewResolver with a default view
   * name prefix of "/pages/views/" and a default suffix of ".jsp"
   */
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/pages/views/", ".jsp");
  }

  /**
   * Configure to load these static files.
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
  }

  /**
   * This ViewResolver allows us to set properties such as prefix or suffix to
   * the view name to generate the final view page URL.
   * 
   * @return
   */
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = 
        new InternalResourceViewResolver();
    viewResolver.setPrefix("/pages/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setViewClass(JstlView.class);
    return viewResolver;
  }

}
