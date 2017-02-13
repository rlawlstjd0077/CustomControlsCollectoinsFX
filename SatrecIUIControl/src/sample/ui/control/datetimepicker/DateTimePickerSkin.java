package sample.ui.control.datetimepicker;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by GSD on 2016-12-19.
 */
public class DateTimePickerSkin extends SkinBase<DateTimePicker> {
  private static final int VALID_DATE_SIZE = 26;
  private static final String INVALID_STANDARD_BUTTON_STYLE = "-fx-border-color:#E16B67;";
  private static final String INVALID_FOCUSED_BUTTON_STYLE = "-fx-border-color: #E16B67; -fx-focus-color:none; -fx-background-color:white";
  private static final String VALID_STANDARD_BUTTON_STYLE = "-fx-background-color:white; " +
    "-fx-background-insets: 0; -fx-border-color: #B3B9BF; -fx-border-radius: 2px;";
  private static final String VALID_FOCUSED_BUTTON_STYLE = "-fx-border-color:#2196F3;-fx-background-color:white;" +
    "-fx-effect: dropshadow(three-pass-box, rgba(65,137,221,0.8), 4, 0, 0, 0);";
  private static final String VALID_HOVER_BUTTON_STATE = "-fx-border-color:#2196F3; -fx-background-color:white";
  boolean focusState;
  boolean errorState;
  private Button calendarButton;
  private TextField textField;
  private Pane pane;
  private DateTimeInfo currentDateTimeInfo;

  public DateTimePickerSkin() {
    this(new DateTimePicker());
  }

  /**
   * DatePicker 객체를 parameter
   *
   * @param dateTimePicker
   */
  public DateTimePickerSkin(DateTimePicker dateTimePicker) {
    super(dateTimePicker);

    currentDateTimeInfo = dateTimePicker.getDateTimeInfo();

    pane = new Pane();

    textField = new TextField();

    int fieldWidth = dateTimePicker.getFormWidth();
    int fieldHeight = dateTimePicker.getFormHeight();

    textField.setPrefSize(fieldWidth, fieldHeight);
//    textField.setMinHeight(fieldHeight);

    textField.getStyleClass().add("form-text");
    textField.setText(currentDateTimeInfo.getDateString());
    dateTimePicker.setDateTimeInfo(currentDateTimeInfo);

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

    calendarButton = new Button();
    dateTimePicker.initTextField(this.textField);

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
        dateTimePicker.handlePopup();
      }
    });

    pane.getChildren().addAll(textField, calendarButton);
    pane.getStyleClass().addAll("form");
    getChildren().add(pane);
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
    node.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (errorState) {
        if (newValue) {
          focusState = true;
          node.setStyle(VALID_FOCUSED_BUTTON_STYLE);
        } else {
          focusState = false;
          node.setStyle(VALID_STANDARD_BUTTON_STYLE);
        }
      }
    });
    node.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (!focusState && errorState) {
        if (newValue) {
          node.setStyle(VALID_HOVER_BUTTON_STATE);
        } else {
          node.setStyle(VALID_STANDARD_BUTTON_STYLE);
        }
      }
    });
  }

  public boolean isValidDate(String date) {
    if (date.length() == VALID_DATE_SIZE) {
      String[] arr = date.split("    ");
      if ((arr.length == 2)) {
        String[] dateObj = arr[0].split("-");
        String[] timeObj = arr[1].split(" : ");
        try {
          if (!((Integer.parseInt(dateObj[1]) > 12 || Integer.parseInt(dateObj[1]) < 1) ||
            (Integer.parseInt(dateObj[2]) > DateTimeInfo.getLastDateFromMonth(dateObj[2]) || (Integer.parseInt(dateObj[2])) < 1) ||
            (Integer.parseInt(timeObj[0]) > 23 || Integer.parseInt(timeObj[0]) < 0) || (Integer.parseInt(timeObj[1]) > 59 || (Integer.parseInt(timeObj[1])) < 0) ||
            (Integer.parseInt(timeObj[2])) > 59 || (Integer.parseInt(timeObj[2])) < 0)) {
            return true;
          }
        } catch (NumberFormatException e) {

        }
      }
    }
    return false;
  }
}
