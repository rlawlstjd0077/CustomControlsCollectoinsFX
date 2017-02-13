package ui.control.treeview;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * text, images 정보를 가지고 있는 CustomTreeItem 클래스
 * Created by JinSeong on 2017-01-09.
 */
public class Gk2TreeItem extends TreeItem<String> {
  private Image normalIcon;
  private Image focusedIcon;

  public Gk2TreeItem(String text, String normalIconPath, String focusedIconPath) {
    super(text);
    this.normalIcon = new Image(getClass().getResourceAsStream(normalIconPath));
    this.focusedIcon = new Image(getClass().getResourceAsStream(focusedIconPath));
    setGraphic(new ImageView(this.normalIcon));
  }

  public Image getNormalIcon() {
    return normalIcon;
  }

  public Image getFocusedIcon() {
    return focusedIcon;
  }
}
