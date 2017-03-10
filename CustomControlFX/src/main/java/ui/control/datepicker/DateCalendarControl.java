package ui.control.datepicker;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.util.Date;

/**
 * DatePicker의 Calendar의 Control 부분
 * Created by GSD on 2017-01-20.
 */
public class DateCalendarControl extends Control{
  private static final String DEFAULT_STYLE_CLASS = "calendar-form";
  private Date currentDate;
  private DateInfo dateInfo;

  /**
   * 시간 선택 콤보 박스의 시간 간격 설정 변수
   */
  private int intervalMin;
  private DatePickerControl datePickerControl;
  private TextField formText;

  /**
   * DatePicker 객체를 parameter로 받아 기능 사용
   *
   * @param datePickerControl
   */
  public DateCalendarControl(DatePickerControl datePickerControl, String currentDate, int intervalMin) {
    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    this.datePickerControl = datePickerControl;
    formText = datePickerControl.getFormText();
    this.currentDate = DateInfo.getDateFromString(currentDate);
    this.intervalMin = 30;
  }

  @Override
  public String getUserAgentStylesheet() {
    return "/commons/ui/control/datepicker/calendar.css";
  }

  public Date getCurrentDate() {
    return currentDate;
  }


  public int getTimeIntervalMin() {
    return this.intervalMin;
  }

  public String getFormText(){
    return formText.getText();
  }

  /** Date가 Selected 됐을 때
   * @param dateInfo
   */
  public void onSelectDate(DateInfo dateInfo) {
    this.dateInfo = dateInfo;
    datePickerControl.setFormText(dateInfo);
    closeCalendar();
  }

  public void closeCalendar() {
    this.datePickerControl.setEditable(true);
    this.datePickerControl.hidePopup();
  }

  public Date getDateFromField() {
    return this.datePickerControl.getDate();
  }


}
