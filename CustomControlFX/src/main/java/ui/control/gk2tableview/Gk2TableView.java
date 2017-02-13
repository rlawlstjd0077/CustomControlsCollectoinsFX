package ui.control.gk2tableview;

import javafx.scene.control.TableView;

/**
 * 기본 형태의 TableView로 BASIC, DARK, TOPCOLOR style 세가지가 있음
 * Created by 신동찬
 */
public class Gk2TableView<T> extends TableView<T> {

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

  public void changeTableStyle(TableStyle style){
    this.getStylesheets().remove(0);
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
  }

  public enum TableStyle {
    BASIC(0), DARK(1), TOPCOLOR(2);
    private int value;

    TableStyle(int value) {
      this.value = value;
    }
  }

  public static void changeTableStyle(TableView view, TableStyle style){
    switch (style) {
      case BASIC:
        view.getStylesheets().add(Gk2TableView.class.getResource("Gk2TableView.css").toExternalForm());
        break;
      case DARK:
        view.getStylesheets().add(Gk2TableView.class.getResource("Gk2TableViewDark.css").toExternalForm());
        break;
      case TOPCOLOR:
        view.getStylesheets().add(Gk2TableView.class.getResource("Gk2TableViewTopColor.css").toExternalForm());
        break;
      default:
        view.getStylesheets().add(Gk2TableView.class.getResource("Gk2TableView.css").toExternalForm());
        break;
    }
  }
}
