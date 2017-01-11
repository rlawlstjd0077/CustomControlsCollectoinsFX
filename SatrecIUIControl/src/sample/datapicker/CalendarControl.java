package sample.datapicker;

import javafx.scene.control.Control;

import java.util.Date;

/**
 * DatePicker의 Calendar의 Control 부분
 * Created by GSD on 2016-12-15.
 */
public class CalendarControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "calendar-form";
  private Date currentDate;
  private DateInfo dateInfo;
  /**
   * 시간 선택 콤보 박스의 시간 간격 설정 변수
   */
  private int intervalMin;
  private DatePickerControl datePickerControl;

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
   * @param datePickerControl
   */
  public CalendarControl(DatePickerControl datePickerControl) {
    this(new Date(System.currentTimeMillis()));
    this.datePickerControl = datePickerControl;
  }

  public CalendarControl() {
    this(new Date(System.currentTimeMillis()));
  }

  @Override
  public String getUserAgentStylesheet() {
    return "/commons/ui/control/datepicker/calendar.css";
  }

  public Date getCurrentDate() {
    return currentDate;
  }

  public void setTimeIntervalMin(int minute) {
    this.intervalMin = minute;
  }

  public int getTimeIntervalMin() {
    return this.intervalMin;
  }

  /** Date가 Selected 됐을 때
   * @param dateInfo
   */
  public void onSelectDate(DateInfo dateInfo) {
    this.dateInfo = dateInfo;
    datePickerControl.setComboBoxText(dateInfo);
    closeCalendar();
  }

  public void closeCalendar() {
    this.datePickerControl.setEditable();
    this.datePickerControl.hidePopup();
  }

  public Date getDateFromField() {
    return this.datePickerControl.getDate();
  }
}
