package fa.training.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * The controller groups together all methods related to login.
 * 
 * @author ThanhDT19
 *
 */
@Controller
public class LoginController {

  /**
   * This method to load login page.
   * 
   * @RequestMapping define url request to response login page
   * @param notify    status login
   * @param modelMap  ModelMap is also used to pass values to render a view
   * @param principal The principal is the currently logged in user
   * @return login page when not logged in, landing page when logged in
   */
  @RequestMapping(value = { "/login", "/" })
  public String loadLoginPage(
      @RequestParam(name = "notify", required = false) String notify,
      ModelMap modelMap, Principal principal) {
    if (notify != null) {
      if ("login-faile".equalsIgnoreCase(notify)) {
        modelMap.addAttribute("message", Message.MSG1);
      }
      if ("logout".equalsIgnoreCase(notify)) {
        modelMap.addAttribute("message", "Logout successfully!");
      }
    }
    if (principal != null) {
      User userLogin = (User) ((Authentication) principal).getPrincipal();
      Collection<GrantedAuthority> authorities = userLogin.getAuthorities();
      List<String> roles = authorities.stream()
          .map(author -> author.getAuthority()).collect(Collectors.toList());
      modelMap.addAttribute("roles", roles);
      return "landing-page";
    }
    return "login";
  }

  /**
   * This method to perform logout.
   * 
   * @RequestMapping define url request to perform logout
   * @param request  request information for HTTP servlets.
   * @param response to provide HTTP-specific functionality in sending a
   *                 response.
   * @return login page
   */
  @RequestMapping("/logout")
  public String actionLogout(HttpServletRequest request,
      HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response,
          authentication);
    }
    return "redirect:/login?notify=logout";
  }

  /**
   * This method to display not permission page.
   * 
   * @RequestMapping define url request to response 403 page
   * @param modelMap  ModelMap is also used to pass values to render a view
   * @param principal The principal is the currently logged in user
   * @return page-403
   */
  @RequestMapping("/page-403")
  public String loadPage403(ModelMap modelMap, Principal principal) {
    if (principal == null) {
      return "login";
    }
    User userLogin = (User) ((Authentication) principal).getPrincipal();
    Collection<GrantedAuthority> authorities = userLogin.getAuthorities();
    List<String> roles = authorities.stream()
        .map(author -> author.getAuthority()).collect(Collectors.toList());
    modelMap.addAttribute("roles", roles);
    Log4J.getLogger().info(userLogin);
    return "page-403";
  }

}
