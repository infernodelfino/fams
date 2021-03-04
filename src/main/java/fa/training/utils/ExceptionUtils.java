package fa.training.utils;

import java.io.FileNotFoundException;
import javax.transaction.SystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import fa.training.exception.DeleteLearnDetailException;
import fa.training.exception.InvalidDateException;
import fa.training.exception.LearnPathException;
import fa.training.exception.MatchException;
import fa.training.exception.PointOutOfRange;

/**
 * This class to handle exception.
 * 
 * @author ThanhDT19
 *
 */
@ControllerAdvice
public class ExceptionUtils extends ResponseEntityExceptionHandler {

  /**
   * This method to handle Exception.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(Exception.class)
  public String handleAllException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle NullPointerException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(NullPointerException.class)
  public String handleNullPointerException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle LearnPathException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(LearnPathException.class)
  public String handleLearnPathException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle MatchException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(MatchException.class)
  public String handleMatchException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle InvalidDateException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(InvalidDateException.class)
  public String handleInvalidDateException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle PointOutOfRange.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(PointOutOfRange.class)
  public String handlePointOutOfRange(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle DeleteLearnDetailException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(DeleteLearnDetailException.class)
  public String handleDeleteLearnDetailException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle NotFound.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(NotFound.class)
  private String handleNotFound(Model model, NotFound ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle NumberFormatException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(NumberFormatException.class)
  public String handleNumberFormatException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle BadRequest.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(BadRequest.class)
  private String handleBadRequest(Model model, BadRequest ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle MethodNotAllowed.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(MethodNotAllowed.class)
  private String handleMethodNotAllowed405(Model model, MethodNotAllowed ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle FileNotFoundException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(value = FileNotFoundException.class)
  private String handleFileNotFound(Model model, FileNotFoundException ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle RuntimeException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(value = RuntimeException.class)
  private String handleRuntime(Model model, RuntimeException ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle NoSuchMethodException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(value = NoSuchMethodException.class)
  private String handleNoSuchMethod(Model model, NoSuchMethodException ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle IllegalArgumentException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public String handleIllegalArgumentException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle SystemException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(SystemException.class)
  public String handleSystemException(Model model, SystemException ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle IllegalStateException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(IllegalStateException.class)
  public String handleIllegalStateException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle ClassCastException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(ClassCastException.class)
  public String handleClassCastException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle ClassNotFoundException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(ClassNotFoundException.class)
  public String handleClassNotFoundException(Model model, Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

  /**
   * This method to handle ArrayIndexOutOfBoundsException.
   * 
   * @param model model is also used to pass values to render a view
   * @param ex    an exception
   * @return error page
   */
  @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
  public String handleArrayIndexOutOfBoundsException(Model model,
      Exception ex) {
    model.addAttribute("error", ex.getMessage());
    Log4J.getLogger().error("Exception: " + ex.getMessage());
    return "/error";
  }

}
