package sample.tableview;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * Header가 있는 TableView로 BASIC, DARK style 두가지가 있음
 * Created by 신동찬
 */
public class Gk2TableViewGroupHeader extends AnchorPane {

  private TableView tableView = new TableView();
  private Label tableTitle;

  /**
   * Gk2TableViewGroupHeader 생성자로 title에 들어갈 문자열과
   * enum class인 HeaderTableStyle이 인자로 들어와야 한다.
   * 예) new Gk2TableViewGroupHeader(Gk2TableViewGroupHeader.HeaderTableStyle.BASIC);
   *
   * @param tableTitle header 부분에 들어갈 title
   * @param style      HeaderTableStyle
   */
  public Gk2TableViewGroupHeader(String tableTitle, HeaderTableStyle style) {
    this.getStyleClass().add("gk2tableview");
    this.setId("tableview_title");
    this.tableTitle = new Label(tableTitle);
    this.tableTitle.setId("tableview_title_text");

    AnchorPane.setLeftAnchor(this.tableTitle, 14.0);
    AnchorPane.setTopAnchor(this.tableTitle, 7.0);

    AnchorPane.setTopAnchor(tableView, 32.0);
    AnchorPane.setBottomAnchor(tableView, 0.0);
    AnchorPane.setLeftAnchor(tableView, 0.0);
    AnchorPane.setRightAnchor(tableView, 0.0);

    this.getChildren().addAll(tableView, this.tableTitle);
    tableView.setFocusTraversable(false);
    switch (style.value) {
      case 0:
        this.getStylesheets().add(getClass().getResource("Gk2TableViewGroupHeader.css").toExternalForm());
        break;
      case 1:
        this.getStylesheets().add(getClass().getResource("Gk2TableViewDark.css").toExternalForm());
        break;
      default:
        this.getStylesheets().add(getClass().getResource("Gk2TableViewGroupHeader.css").toExternalForm());
        break;
    }

    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Medium.ttf"),
      14
    );

  }

  /**
   * AnchorPane안에 TableView가 감싸져 있는 구조이기때문에
   * Getter를 통해 TableView에 접근할 수 있다.
   *
   * @return TableView
   */
  public TableView getTableView() {
    return tableView;
  }

  public void setTableView(TableView tableView) {
    this.tableView = tableView;
  }

  public Label getTableTitle() {
    return tableTitle;
  }

  public void setTableTitle(Label tableTitle) {
    this.tableTitle = tableTitle;
  }

  public enum HeaderTableStyle {
    BASIC(0), DARK(1);
    private int value;

    HeaderTableStyle(int value) {
      this.value = value;
    }
  }
}
