package ui.control.sidemenu;

import commons.ui.UiUtil;
import commons.ui.viewmodel.MenuViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

/**
 * 사이트 메뉴를 표현하기 위한 Controller.
 * Created by 신동찬 on 2016-12-26.
 * Modified By 동근방.
 */
public class SideMenuController extends AnchorPane {
  private final static int VISIBLE_WIDTH = 240;
  private final static int HIDE_WIDTH = 40;

  @FXML
  private TreeView<MenuViewModel> treeView;

  @FXML
  private ImageView showButton;

  @FXML
  private ImageView hideButton;

  private ObservableList<TreeItem> sideMenuTreeItem = FXCollections.observableArrayList();
  private Boolean isVisible = true;

  /**
   * Side Menu를 위한 컨트롤러.
   *
   * @throws IOException FXML 이 없을 때 발생하는 오류.
   */
  public SideMenuController() throws IOException {
    UiUtil.loadFxml(this);
    hideButton.setOnMouseClicked(event -> hide());
    showButton.setOnMouseClicked(event -> visible());

    treeView.setCellFactory(param -> {
      TreeCell<MenuViewModel> cell = new TreeCell<MenuViewModel>() {
        @Override
        public void updateItem(MenuViewModel item, boolean empty) {
          super.updateItem(item, empty);
          if (!empty) {
            final TreeItem<MenuViewModel> treeItem = getTreeItem();

            setText(item.getTitle());
            setGraphic(treeItem.getGraphic());
            setCursor(Cursor.HAND);
            if (item.isRunnable()) {
              setOnMouseClicked(event -> item.run());
            } else {
              setOnMouseClicked(event -> treeItem.setExpanded(!treeItem.isExpanded()));
            }
          } else {
            setText("");
            setGraphic(null);
            setOnMouseClicked(null);
          }
        }
      };
      return cell;
    });

    hide();
  }

  /**
   * MenuViewModel 과 바인딩 수행.
   *
   * @param menuViewModel Side menu 정보.
   */
  public void bind(MenuViewModel menuViewModel) {
    sideMenuTreeItem.clear();

    TreeItem<MenuViewModel> root = createTreeItem(menuViewModel);
    root.setExpanded(true);

    treeView.setRoot(root);
  }

  private TreeItem<MenuViewModel> createTreeItem(MenuViewModel menuViewModel) {
    final StackPane graphic = new StackPane();
    graphic.setPrefWidth(30);
    graphic.setPrefHeight(30);
    graphic.setPadding(new Insets(0, 4, 2, 0));

    Node icon = menuViewModel.getIcon();
    if (icon == null) {
      icon = UiUtil.createGlyph(FontAwesome.Glyph.INBOX);
    }
    graphic.getChildren().add(icon);
    icon.getStyleClass().add("sidemenu-icon");

    TreeItem<MenuViewModel> item = new TreeItem<>(menuViewModel, graphic);
    menuViewModel.getChildren().forEach(m -> item.getChildren().add(createTreeItem(m)));
    return item;
  }

  private void hide() {
    isVisible = false;

    Timeline timeline = new Timeline();
    timeline.getKeyFrames().addAll(
      new KeyFrame(new Duration(200),
        new KeyValue(this.prefWidthProperty(), HIDE_WIDTH),
        new KeyValue(hideButton.visibleProperty(), isVisible),
        new KeyValue(showButton.visibleProperty(), !isVisible)
      )
    );
    timeline.play();
  }

  private void visible() {
    isVisible = true;

    Timeline timeline = new Timeline();
    timeline.getKeyFrames().addAll(
      new KeyFrame(new Duration(200),
        new KeyValue(this.prefWidthProperty(), VISIBLE_WIDTH),
        new KeyValue(hideButton.visibleProperty(), isVisible),
        new KeyValue(showButton.visibleProperty(), !isVisible)
      )
    );
    timeline.play();
  }
}
