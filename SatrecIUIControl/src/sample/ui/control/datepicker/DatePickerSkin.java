package sample.ui.control.datepicker;

import commons.ui.control.datetimepicker.DateTimeInfo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Calendar;

/**
 * Created by GSD on 2017-01-20.
 */
public class DatePickerSkin extends SkinBase<DatePickerControl>{
  private Button calendarButton;
  private TextField textField;
  private Pane pane;
  private DateInfo currentDateTimeInfo;
  private static final int VALID_DATE_SIZE = 10;
  boolean focusState;
  boolean errorState;

  private static final String INVALID_STANDARD_BUTTON_STYLE = "-fx-border-color:#E16B67;";
  private static final String INVALID_FOCUSED_BUTTON_STYLE = "-fx-border-color: #E16B67; -fx-focus-color:none; -fx-background-color:white";
  private static final String VALID_STANDARD_BUTTON_STYLE = "-fx-background-color:white; " +
    "-fx-background-insets: 0; -fx-border-color: #B3B9BF; -fx-border-radius: 2px;";
  private static final String VALID_FOCUSED_BUTTON_STYLE = "-fx-border-color:#2196F3;-fx-background-color:white;" +
    "-fx-effect: dropshadow(three-pass-box, rgba(65,137,221,0.8), 4, 0, 0, 0);";
  private static final String VALID_HOVER_BUTTON_STATE = "-fx-border-color:#2196F3; -fx-background-color:white";

  public DatePickerSkin() {
    this(new DatePickerControl());
  }


  /**
   * DatePickerControl 객체를 parameter 로 받아 기능 사용
   * @param datePickerControl
   */
  public DatePickerSkin(DatePickerControl datePickerControl) {
    super(datePickerControl);

    /* pane 안에 FDSTextField 와 Button 을 겹치게 배치 */
    pane = new Pane();
    textField = new TextField();
    calendarButton = new Button();

    int fieldWidth = datePickerControl.getFormWidth();
    int fieldHeight = datePickerControl.getFormHeight();

    textField.setPrefWidth(fieldWidth);
    textField.setPrefHeight(fieldHeight);
    textField.setMinHeight(fieldHeight);
    textField.getStyleClass().add("form-text");
    settingCurrentDate();
    textField.setText(currentDateTimeInfo.getDateString());
    datePickerControl.setDateInfo(currentDateTimeInfo);

    calendarButton.setPrefWidth(fieldHeight - 8);
    calendarButton.setPrefHeight(fieldHeight - 8);
    calendarButton.setLayoutX(fieldWidth - fieldHeight + 4);
    calendarButton.setLayoutY(4);
    calendarButton.getStyleClass().setAll("calendar-form-button");
    calendarButton.setGraphic(
      new ImageView(new Image(getClass().getResourceAsStream(fieldHeight > 32 ? "/commons/ui/control/datepicker/image/calendar_gray.png" : "/commons/ui/control/datepicker/image/calendar_gray_16px.png"))));


    //TextField의 입력 이벤트 감시
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (isValidDate(textField.getText())) {
        textField.styleProperty().unbind();
        backToOriginalUsingEvent(textField);
      } else {
        textField.styleProperty().unbind();
        changeBorderOnFocusUsingBinding(textField);
      }
    });
    calendarButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        datePickerControl.handlePopup();
      }
    });
    pane.getChildren().addAll(textField, calendarButton);
    pane.getStyleClass().addAll("form");
    getChildren().add(pane);
    datePickerControl.initTextField(this.textField);
  }

  private void settingCurrentDate() {
    Calendar cal = Calendar.getInstance();
    currentDateTimeInfo = new DateInfo();
    currentDateTimeInfo.setDate(cal);
  }

  private void changeBorderOnFocusUsingBinding(Node node) {
    errorState = false;
    node.styleProperty().bind(
      Bindings
        .when(node.focusedProperty())
        .then(
          new SimpleStringProperty(INVALID_FOCUSED_BUTTON_STYLE)
        )
        .otherwise(
          new SimpleStringProperty(INVALID_STANDARD_BUTTON_STYLE)
        )
    );
  }

  public void backToOriginalUsingEvent(Node node) {
    node.setStyle(VALID_FOCUSED_BUTTON_STYLE);
    errorState = true;
    node.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (errorState) {
          if (newValue) {
            focusState = true;
            node.setStyle(VALID_FOCUSED_BUTTON_STYLE);
          } else {
            focusState = false;
            node.setStyle(VALID_STANDARD_BUTTON_STYLE);
          }
        }
      }
    });
    node.hoverProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!focusState && errorState) {
          if (newValue) {
            node.setStyle(VALID_HOVER_BUTTON_STATE);
          } else {
            node.setStyle(VALID_STANDARD_BUTTON_STYLE);
          }
        }
      }
    });
  }

  /**
   * Date가 유효한지 검사
   * @param date
   * @return
   */
  public boolean isValidDate(String date) {
    if (date.length() == VALID_DATE_SIZE) {
      String[] arr = date.split("-");
      if ((arr.length == 3)) {
        try {
          if (!((Integer.parseInt(arr[1]) > 12 || Integer.parseInt(arr[1]) < 1) ||
            (Integer.parseInt(arr[2]) > DateTimeInfo.getLastDateFromMonth(arr[2]) || (Integer.parseInt(arr[2])) < 1))) {
            return true;
          }
        } catch (NumberFormatException e) {

        }
      }
    }
    return false;
  }
}
