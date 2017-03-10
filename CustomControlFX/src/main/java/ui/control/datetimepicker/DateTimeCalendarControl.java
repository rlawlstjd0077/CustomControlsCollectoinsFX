package ui.control.datetimepicker;

import javafx.scene.control.Control;

import java.util.Date;

/**
 * DateTimePicker의 Calendar의 Control 부분
 * Created by GSD on 2016-12-15.
 */
public class DateTimeCalendarControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "calendar-form";
  private Date currentDate;
  private DateTimeInfo dateTimeInfo;
  /**
   * 시간 선택 콤보 박스의 시간 간격 설정 변수
   */
  private int intervalMin;
  private DateTimePickerControl dateTimePickerControl;

  /**
   * System의 현재 시간으로 Calendar 초기화
   *
   * @param parent
   */
  public DateTimeCalendarControl(Date parent) {
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
  public DateTimeCalendarControl(Control datePickerControl) {
    this(new Date(System.currentTimeMillis()));
    this.dateTimePickerControl = (DateTimePickerControl) datePickerControl;
  }

  public DateTimeCalendarControl() {
    this(new Date(System.currentTimeMillis()));
  }

  @Override
  public String getUserAgentStylesheet() {
    return "/commons/ui/control/datetimepicker/calendar.css";
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
   * @param dateTimeInfo
   */
  public void onSelectDate(DateTimeInfo dateTimeInfo) {
    this.dateTimeInfo = dateTimeInfo;
    onClicked();
    dateTimePickerControl.setComboBoxText(dateTimeInfo);
    closeCalendar();
  }

  public void closeCalendar() {
    this.dateTimePickerControl.setEditable();
    this.dateTimePickerControl.hidePopup();
  }

  public Date getDateFromField() {
    return this.dateTimePickerControl.getDate();
  }
  public interface OnMyItemClicked{
    public void onItemClicked();
  }
  private OnMyItemClicked mOnMyItemClicked;
  public void setmOnMyItemClicked(OnMyItemClicked mOnMyItemClicked){
    DateTimeCalendarControl.OnMyItemClicked onMyItemClicked = mOnMyItemClicked;
  }
  public void onClicked(){
    this.mOnMyItemClicked.onItemClicked();
  }
}
