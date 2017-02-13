package sample.ui.control.datetimepicker;

import javafx.scene.control.Control;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * DatePicker의 Calendar의 Control 부분
 * Created by GSD on 2016-12-15.
 */
public class CalendarControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "calendar-form";
  private Date currentDate;
  private DateTimeInfo dateTimeInfo;
  /**
   * 시간 선택 콤보 박스의 시간 간격 설정 변수
   */
  private int intervalMin;
  private DateTimePicker dateTimePicker;

  /**
   * System의 현재 시간으로 Calendar 초기화
   *
   * @param parent
   */
  public CalendarControl(Date parent) {
    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    this.currentDate = parent;
    this.intervalMin = 30;
    this.intervalMin = 30;
  }

  /**
   * DatePicker 객체를 parameter로 받아 기능 사용
   *
   * @param dateTimePicker
   */
  public CalendarControl(DateTimePicker dateTimePicker) {
    this(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
    this.dateTimePicker = dateTimePicker;
  }

  public CalendarControl() {
    this(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
  }

  @Override
  public String getUserAgentStylesheet() {
    return "/commons/ui/control/datetimepicker/calendar.css";
  }

  public Date getCurrentDate() {
    return currentDate;
  }

  public int getTimeIntervalMin() {
    return this.intervalMin;
  }

  public void setTimeIntervalMin(int minute) {
    this.intervalMin = minute;
  }

  /**
   * Date가 Selected 됐을 때
   *
   * @param dateTimeInfo
   */
  public void onSelectDate(DateTimeInfo dateTimeInfo) {
    this.dateTimeInfo = dateTimeInfo;
    dateTimePicker.setComboBoxText(dateTimeInfo);
    closeCalendar();
  }

  public void closeCalendar() {
    this.dateTimePicker.setEditable();
    this.dateTimePicker.hidePopup();
  }

  public Date getDateFromField() {
    return this.dateTimePicker.getDate();
  }
}
