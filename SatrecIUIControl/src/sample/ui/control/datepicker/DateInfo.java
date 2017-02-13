package sample.ui.control.datepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GSD on 2017-01-20.
 */
public class DateInfo {
  private Date date;
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * 전달된 month 에 해당하는 마지막 일자를 return
   *
   * @param month
   * @return lastday
   */

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

  /**
   * date 객체를 리턴
   *
   * @return dateObject
   */
  public Date getDate() {
    return date;
  }
  /**
   * String 형식으로 date 리턴
   * @return StringDate
   */
  public String getDateString() {
    return dateFormat.format(this.date);
  }

  /**
   *  year, month, day, time을 각각 전달 받아 date setting
   * @param year
   * @param month
   * @param day
   */
  public void setDate(int year, int month, int day) {
    try {
      this.date = dateFormat.parse(year + "-" + month + "-" + day);
    } catch (ParseException e) {
    }
  }

  /**
   * Calendar 객체로 date setting
   * @param cal
   */
  public void setDate(Calendar cal) {
    try {
      this.date = dateFormat.parse(cal.get(Calendar.YEAR) + "-"
        + (cal.get(Calendar.MONTH) + 1) + "-"
        + cal.get(Calendar.DAY_OF_MONTH) + "    "
        + cal.get(Calendar.HOUR) + " : "
        + cal.get(Calendar.MINUTE) + " : "
        + cal.get(Calendar.SECOND));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * String 형식으로 date setting
   * @param date
   */
  public void setDate(String date) {
    try {
      this.date = dateFormat.parse(date);
    } catch (ParseException e) {
    }
  }
}
