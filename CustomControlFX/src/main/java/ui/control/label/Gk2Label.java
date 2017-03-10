package ui.control.label;

import commons.log.LogLevel;
import javafx.scene.control.Label;

/**
 * Error, Warning, Info, OFF 에 쓰이는 Label
 * Created by 신동찬.
 */
public class Gk2Label extends Label {
  /**
   * Gk2Label의 생성자로 enum class인 LabelUse가 인자로 들어와야 한다.
   * 예) new Gk2Lable(Gk2Label.LabelUse.ERROR);
   *
   * @param use LabelUse
   */
  public Gk2Label(LabelUse use) {
    getStylesheets().add("/commons/ui/control/gk2label/Gk2Label.css");
    if (use == null) {
      this.setId("info_label");
    } else {
      switch (use.value) {
        case 0:
          this.setId("error_label");
          this.setText("Error");
          break;
        case 1:
          this.setId("warning_label");
          this.setText("Warning");
          break;
        case 2:
          this.setId("info_label");
          this.setText("Info");
          break;
        case 3:
          this.setId("off_label");
          this.setText("OFF");
          break;
        default:
          this.setId("error_label");
          break;
      }
    }
  }

  public Gk2Label(LogLevel level) {
    getStylesheets().add("/commons/ui/control/gk2label/Gk2Label.css");
    setText(level.toString());

    switch (level) {
      case ERR:
        setId("error_label");
        break;
      case WAR:
        setId("warning_label");
        break;
      case INF:
      case EXT:
      case INT:
      case PRC:
        setId("info_label");
        break;
      default:
        setId("off_label");
        break;
    }
  }

  public enum LabelUse {
    ERROR(0), WARNING(1), INFO(2), OFF(3);
    private int value;

    LabelUse(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}
