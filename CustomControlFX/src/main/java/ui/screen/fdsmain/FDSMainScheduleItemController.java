package ui.screen.fdsmain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.UiUtil;

import java.io.IOException;

/**
 * Created by GSD on 2017-03-07.
 */
public class FDSMainScheduleItemController extends VBox {
  @FXML
  private Label titleLabel;

  private ItemType item;

  public FDSMainScheduleItemController() throws IOException {
    UiUtil.loadFxml(this);
  }

  public void set(String title) {
    titleLabel.setText(title);
  }

  public void setMode(ItemType itemType) {
    this.item = itemType;
    updateClassName();
  }

  private void updateClassName() {
    String className = "mainScheduleItem";
    switch (item) {
      case SUN_ECLIPSE:
        className = "mainScheduleItemSunEclipse";
        break;
      case MOON_ECLIPSE:
        className = "mainScheduleItemMoonEclipse";
        break;
      case SENSOR_INTUSLON:
        className = "mainScheduleItemSensorIntuslon";
        break;
      case ORBIT_CROSSING:
        className = "mainScheduleItemOrbitCrossing";
        break;
      case SUN_INTERFACE:
        className = "mainScheduleItemSunInterface";
        break;
      case SK_NS:
        className = "mainScheduleItemSK_NS";
        break;
      case SK_EW:
        className = "mainScheduleItemSK_EW";
        break;
      case WOL:
        className = "mainScheduleItemSK_WOL";
        break;
    }
    this.getStyleClass().setAll(className);
  }

  public enum ItemType {
    SUN_ECLIPSE,
    MOON_ECLIPSE,
    SENSOR_INTUSLON,
    ORBIT_CROSSING,
    SUN_INTERFACE,
    SK_NS,
    SK_EW,
    WOL
  }
}
