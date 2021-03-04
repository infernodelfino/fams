package fa.training.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class to define log4j.
 * 
 * @author ThanhDT19
 *
 */
public final class Log4J {
  private static final Logger LOGGER = LogManager.getLogger(Log4J.class);

  private Log4J() {
    super();
  }

  /**
   * This static method return logger.
   * 
   * @return singleton instance of Logger
   */
  public static Logger getLogger() {
    return LOGGER;
  }
}