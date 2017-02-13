package ui.control.datepicker;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Date;

/**
 * Created by JinSeong on 2017-01-20.
 */
public class DatePickerControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
  private Popup popup;
  private DateCalendarControl dateCalendarControl;
  private TextField textField;
  private DateInfo dateInfo;
  private StringProperty cssPath;
  private SimpleIntegerProperty formWidth;
  private SimpleIntegerProperty formHeight;
  private SimpleIntegerProperty fontSize;

  public DatePickerControl(int width, int height, int fontSize){
    setFormWidth(width);
    setFormHeight(height);
    setFontSize(fontSize);
    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    popup = new Popup();
    dateCalendarControl = new DateCalendarControl(this);
    dateCalendarControl.getStylesheets().add("/commons/ui/control/datepicker/chooser.css");
  }

  public DatePickerControl(){
    this(220, 36, 14);
  }

  @Override
  public String getUserAgentStylesheet() {
    return "commons/ui/control/datepicker/date_picker.css";
  }

  /**
   *	popup창 제어 함수
   *	Calendar Button 이 눌렸을 때 호출
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
      popup.getContent().addAll(dateCalendarControl);
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

  public void setEditable(){
    textField.setEditable(true);
  }
  public void setComboBoxText(DateInfo dateinfo){
    this.dateInfo = dateinfo;
    this.textField.setText(dateinfo.getDateString());
  }

  public Date getDate(){
    this.dateInfo.setDate(textField.getText());
    return this.dateInfo.getDate();
  }
  public void hidePopup(){
    popup.hide();
  }
  public void initTextField(TextField textField){
    this.textField = textField;
  }

  public void setTimeInterval(int minute){
    this.dateCalendarControl.setTimeIntervalMin(minute);
  }
  public void setDateInfo(DateInfo date) {
    this.dateInfo = date;
  }

}
