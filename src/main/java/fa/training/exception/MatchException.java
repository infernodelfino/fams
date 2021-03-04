package fa.training.exception;

public class MatchException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MatchException() {
  }

  public MatchException(String message) {
    super(message);
  }
}
