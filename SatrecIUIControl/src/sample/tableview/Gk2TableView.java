package sample.tableview;

import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * 기본 형태의 TableView로 BASIC, DARK, TOPCOLOR style 세가지가 있음
 * Created by 신동찬
 */
public class Gk2TableView extends TableView {

  /**
   * 기본 생성시 TableStyle.BASIC 으로 초기화
   */
  public Gk2TableView(){
    this(TableStyle.BASIC);
  }

  /**
   * Gk2TableView 생성자로 enum class인 TableStyle이 인자로 들어와야 한다.
   * 예) new Gk2TableView(Gk2TableView.TableStyle.BASIC);
   *
   * @param style TableStyle
   */

  public Gk2TableView(TableStyle style) {
    this.setFocusTraversable(false);
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Medium.ttf"),
      14
    );
    switch (style.value) {
      case 0:
        this.getStylesheets().add(getClass().getResource("Gk2TableView.css").toExternalForm());
        break;
      case 1:
        this.getStylesheets().add(getClass().getResource("Gk2TableViewDark.css").toExternalForm());
        break;
      case 2:
        this.getStylesheets().add(getClass().getResource("Gk2TableViewTopColor.css").toExternalForm());
        break;
      default:
        this.getStylesheets().add(getClass().getResource("Gk2TableView.css").toExternalForm());
        break;
    }
    this.getStyleClass().add("gk2tableview");
  }

  public enum TableStyle {
    BASIC(0), DARK(1), TOPCOLOR(2);
    private int value;

    TableStyle(int value) {
      this.value = value;
    }
  }
}
