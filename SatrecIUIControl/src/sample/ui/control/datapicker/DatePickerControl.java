package sample.ui.control.datapicker;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Date;

/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerControl extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    private Popup popup;
    private CalendarControl calendarControl;
    private TextField textField;
    private DateInfo dateInfo;

    public DatePickerControl(){
//        if (Platform.isFxApplicationThread()) {
//
//        } else {
//            // Intended for SceneBuilder
//            Platform.runLater(this::init);
//        }
        init();
    }

    public void init(){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        popup = new Popup();
        calendarControl = new CalendarControl(this);
       calendarControl.getStylesheets().add("/commons/ui/control/datepicker/chooser.css");
    }

    @Override
    public String getUserAgentStylesheet() {
        return "commons/ui/control/datepicker/date_picker.css";
    }
    public void handlePopup() {
        if (popup.isShowing()) {
            popup.hide();
        } else {

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
    public void setEditable(){
      textField.setEditable(true);
    }
    public void setComboBoxText(DateInfo dateInfo){
        this.dateInfo = dateInfo;
        this.textField.setText(dateInfo.getDateString());
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
        this.calendarControl.setTimeIntervalMin(minute);
    }
    public void setDateInfo(DateInfo date) {
        this.dateInfo = date;
    }
}
