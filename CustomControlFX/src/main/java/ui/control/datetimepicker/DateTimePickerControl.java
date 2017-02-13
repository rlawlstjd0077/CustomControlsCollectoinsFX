package ui.control.datetimepicker;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Date;

/**
 * Created by JinSeong on 2016-12-19.
 */
public class DateTimePickerControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "date-time-picker-form";
  private Popup popup;
  private DateTimeCalendarControl dateTimeCalendarControl;
  private TextField textField;
  private DateTimeInfo dateTimeInfo;
  /**
   * formWidth : DatePicker의 사이즈 조절 프로퍼티
   */
  private SimpleIntegerProperty formWidth;
  private SimpleIntegerProperty formHeight;
  private SimpleIntegerProperty fontSize;

  public DateTimePickerControl() {
    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    popup = new Popup();
    dateTimeCalendarControl = new DateTimeCalendarControl(this);

    dateTimeCalendarControl.getStylesheets().add("/commons/ui/control/datetimepicker/chooser.css");
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
      popup.getContent().addAll(dateTimeCalendarControl);
      popup.show(this.getParent(), x, y);
      popup.setAutoHide(true);
      textField.setEditable(false);
    }
  }

  private SimpleIntegerProperty formWidthProperty() {
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
    this.dateTimeCalendarControl.setTimeIntervalMin(minute);
  }

  public void setDateTimeInfo(DateTimeInfo date) {
    this.dateTimeInfo = date;
  }

  public TextField getTextFelid() {
    return this.textField;
  }

  DateTimeCalendarControl.OnMyItemClicked mOnMyItemClicked;
  public void setmOnMyItemClicked(DateTimeCalendarControl.OnMyItemClicked onMyItemClicked){
    this.mOnMyItemClicked = onMyItemClicked;
    dateTimeCalendarControl.setmOnMyItemClicked(mOnMyItemClicked);
  }

}
