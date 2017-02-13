package sample.ui.control.datetimepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by GSD on 2016-12-27.
 */
public class DateTimeInfo {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd    HH : mm : ss");
  private Date date;

  public DateTimeInfo() {
    dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
  }

  public static int getLastDateFromMonth(String month) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
    Date date;
    Calendar calendar = null;
    try {
      date = dateFormat.parse(month);
      calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    } catch (ParseException e) {
    }
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public Date getDate() {
    return date;
  }

  public void setDate(String date) {
    try {
      this.date = dateFormat.parse(date);
    } catch (ParseException e) {
    }
  }

  public void setDate(int year, int month, int day, String time) {
    try {
      this.date = dateFormat.parse(year + "-" + month + "-" + day + "    " + time);
    } catch (ParseException e) {
    }
  }

  public void setDate(Calendar cal) {
    try {
      this.date = dateFormat.parse(cal.get(Calendar.YEAR) + "-"
        + (cal.get(Calendar.MONTH) + 1) + "-"
        + cal.get(Calendar.DAY_OF_MONTH) + "    "
        + cal.get(Calendar.HOUR) + " : "
        + cal.get(Calendar.MINUTE) + " : "
        + cal.get(Calendar.SECOND));
    } catch (ParseException e) {
    }
  }

  public String getDateString() {
    return dateFormat.format(this.date);
  }

  public static Date getformattedDate(String date){
    try {
      Date formattedDate = dateFormat.parse(date);
      return formattedDate;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
