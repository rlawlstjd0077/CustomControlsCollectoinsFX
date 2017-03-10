package ui.control.datepicker;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Date;

/**
 * Created by GSD on 2017-01-20.
 */
public class DatePickerControl extends Control {
  private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
  private Popup popup;
  private TextField textField;
  private DateInfo dateInfo;
  private StringProperty cssPath;
  private int formWidth;
  private int formHeight;
  private int fontSize;
  private String formTextStyleClass;
  private String formButtonStyleClass;
  private boolean textFieldCheckState;
  private int timeInterval = 30;


  public DatePickerControl(int width, int height, int fontSize, String formTextStyleClass, String formButtonStyleClass, boolean textFieldCheckState){
    this.formWidth = width;
    this.formHeight = height;
    this.formTextStyleClass = formTextStyleClass;
    this.formButtonStyleClass = formButtonStyleClass;
    this.textFieldCheckState = textFieldCheckState;
    setFontSize(fontSize);

    getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    popup = new Popup();
  }

  public DatePickerControl(int width, int height, int fontSize, String formTextStyleClass, String formButtonStyleClass){
    this(width, height, fontSize, formTextStyleClass, formButtonStyleClass, true);
  }

  public DatePickerControl(int width, int height, int fontSize) {
    this(width, height, fontSize, "form-text", "calendar-form-button", true);

  }

  public DatePickerControl(){
    this(220, 36, 14, "form-text", "calendar-form-button",true);
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
      DateCalendarControl control = new DateCalendarControl(this,  textField.getText(), timeInterval);
      control.getStylesheets().add("/commons/ui/control/datepicker/chooser.css");
      popup.getContent().addAll(control);
      popup.show(this.getParent(), x, y);
      popup.setAutoHide(true);
      textField.setEditable(false);
    }
  }


  public void setFormWidth(int width) {
    this.formWidth = width;
  }

  public int getFormWidth() {
    return formWidth;
  }


  public void setFormHeight(int height){
    this.formHeight = height;
  }

  public int getFormHeight() {
    return formHeight;
  }


  public void setFontSize(int size) {
    this.fontSize = size;
    setStyle("-fx-font-size:" + size);
  }


  public String getFormTextStyleClass() {
    return formTextStyleClass;
  }

  public String getFormButtonStyleClass() {
    return formButtonStyleClass;
  }

  public void setEditable(boolean state){
    if(!textFieldCheckState) {
      textField.setEditable(state);
    }
  }

  public void setFormText(DateInfo dateinfo){
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
    timeInterval = minute;
  }
  public void setDateInfo(DateInfo date) {
    this.dateInfo = date;
  }


  public boolean isTextFieldCheckState() {
    return textFieldCheckState;
  }

  public void addMonth(){
    this.dateInfo.addMonth();
    this.textField.setText(dateInfo.getDateString());
  }

  public void minusMonth(){
    this.dateInfo.minusMonth();
    this.textField.setText(dateInfo.getDateString());
  }

  public TextField getFormText() {
    return this.textField;
  }
}
