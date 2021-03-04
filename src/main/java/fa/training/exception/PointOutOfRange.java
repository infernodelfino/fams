package fa.training.exception;

public class PointOutOfRange extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public PointOutOfRange(String msg) {
    super(msg);
  }
}
