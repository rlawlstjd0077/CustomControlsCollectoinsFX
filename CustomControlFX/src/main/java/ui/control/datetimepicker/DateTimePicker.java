package ui.control.datetimepicker;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GSD on 2016-12-19.
 */
public class DateTimePicker extends Control {
  private static final String DEFAULT_STYLE_CLASS = "date-time-picker-form";
  private DateTimeInfo dateTimeInfo = new DateTimeInfo();
  private Popup popup;
  private CalendarControl calendarControl;
  private TextField textField = new TextField();
  private SimpleIntegerProperty formWidth;
  private SimpleIntegerProperty formHeight;
  private SimpleIntegerProperty fontSize;

  public DateTimePicker(int width, int height, int fontSize){
    setFormWidth(width);
    setFormHeight(height);
    setFontSize(fontSize);
    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    popup = new Popup();
    calendarControl = new CalendarControl(this);
    calendarControl.getStylesheets().add("/commons/ui/control/datetimepicker/chooser.css");

    Calendar cal = Calendar.getInstance();
    dateTimeInfo.setDate(cal);
  }
  public DateTimePicker() {
    this(220, 36, 14);
  }

  @Override
  public String getUserAgentStylesheet() {
    return "commons/ui/control/datetimepicker/date_time_picker.css";
  }

  /**
   * popup창 제어 함수
   * Calendar Button 이 눌렸을 때 호출
   */
  public void handlePopup() {
    if (popup.isShowing()) {
      popup.hide();
    } else {
      //현재 DatePicker Control의 위치를 계산해 DatePicker의 오른쪽 하단에 기준으로  Calendar Control을 띄운다.
      final Window window = textField.getScene().getWindow();
      popup.setAutoHide(true);
      popup.setAutoFix(true);
      popup.setHideOnEscape(true);
      popup.setWidth(100);
      popup.setHeight(300);

      final double x = window.getX()
        + textField.localToScene(0, 0).getX()
        + textField.getScene().getX()
        - 44;
      final double y = window.getY()
        + textField.localToScene(0, 0).getY()
        + textField.getScene().getY()
        + textField.getHeight();

      popup.getContent().clear();
      popup.getContent().addAll(calendarControl);
      popup.show(this.getParent(), x, y);
      popup.setAutoHide(true);
      textField.setEditable(false);
    }
  }

  public SimpleIntegerProperty formWidthProperty() {
    if (formWidth == null) {
      formWidth = new SimpleIntegerProperty(this, "width", 220);
    }
    return formWidth;
  }

  public void setFormWidth(int width) {
    formWidthProperty().setValue(width);
  }

  public int getFormWidth() {
    return formWidth == null ? 220 : formWidthProperty().getValue();
  }

  public SimpleIntegerProperty formHeightProperty() {
    if (formHeight == null) {
      formHeight = new SimpleIntegerProperty(this, "height", 36);
    }
    return formHeight;
  }

  public void setFormHeight(int height) {
    formHeightProperty().setValue(height);
  }

  public int getFormHeight() {
    return formHeight == null ? 36 : formHeightProperty().getValue();
  }

  public SimpleIntegerProperty fontSizeProperty() {
    if (fontSize == null) {
      fontSize = new SimpleIntegerProperty(this, "font-size", 14) {
      };
    }
    return fontSize;
  }

  public void setFontSize(int size) {
    fontSizeProperty().setValue(size);
    setStyle("-fx-font-size:" + getFontSize());
  }

  public int getFontSize() {
    return fontSize == null ? 14 : fontSizeProperty().getValue();
  }

  public void setEditable() {
    textField.setEditable(true);
  }

  public void setComboBoxText(DateTimeInfo dateTimeInfo) {
    this.dateTimeInfo = dateTimeInfo;
    this.textField.setText(dateTimeInfo.getDateString());
  }

  public Date getDate() {
    this.dateTimeInfo.setDate(textField.getText());
    return this.dateTimeInfo.getDate();
  }

  public void hidePopup() {
    popup.hide();
  }

  public void initTextField(TextField textField) {
    this.textField = textField;
  }

  public void setTimeInterval(int minute) {
    this.calendarControl.setTimeIntervalMin(minute);
  }

  public void setDateTimeInfo(DateTimeInfo date) {
    this.dateTimeInfo = date;
  }

  public ZonedDateTime getDatetime() {
    final Date date = getDate();
    return ZonedDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
  }

  public void setDatetime(ZonedDateTime datetime) {
    String time = datetime.format(DateTimeFormatter.ofPattern("HH : mm : ss"));
    dateTimeInfo.setDate(datetime.getYear(), datetime.getMonthValue(), datetime.getDayOfMonth(), time);
    textField.setText(dateTimeInfo.getDateString());
  }

  public DateTimeInfo getDateTimeInfo() {
    return dateTimeInfo;
  }

  public boolean isValidDateType(){
    return new DateTimePickerSkin().isValidDate(textField.getText());
  }



}
