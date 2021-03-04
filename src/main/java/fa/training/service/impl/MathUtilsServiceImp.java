package fa.training.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MathUtilsServiceImp {
  public List<Integer> getDaysByMonth(String date) throws ParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat("yyyy-M-d").parse(date));
    LocalDate temp = LocalDate.parse(date, formatter).withDayOfMonth(1);
    int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    List<Integer> days = new ArrayList<Integer>();
    for (int i = 1; i <= totalDate; i++) {
      if (!temp.getDayOfWeek().equals(DayOfWeek.SATURDAY)
          && !temp.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
        days.add(temp.getDayOfMonth());
      }
      temp = temp.plusDays(1);
    }
    return days;
  }

  public List<Integer> getDays(String startDate, String endDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
    LocalDate date1 = LocalDate.parse(startDate, formatter);
    LocalDate date2 = LocalDate.parse(endDate, formatter);
    LocalDate temp = date1;
    long totalDate = ChronoUnit.DAYS.between(date1, date2);
    List<Integer> days = new ArrayList<Integer>();
    for (int i = 1; i <= totalDate + 1; i++) {
      if (!temp.getDayOfWeek().equals(DayOfWeek.SATURDAY)
          && !temp.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
        days.add(temp.getDayOfMonth());
      }
      temp = temp.plusDays(1);
    }
    return days;
  }

}
