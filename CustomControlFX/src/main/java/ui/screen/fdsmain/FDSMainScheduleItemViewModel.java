package ui.screen.fdsmain;

import commons.data.ScheduleDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-03-07.
 */
public class FDSMainScheduleItemViewModel {
  private StringProperty title;
  private ObjectProperty<FDSMainScheduleItemController.ItemType> itemType;
  private ObjectProperty<ScheduleDateTime> schedule;

  public FDSMainScheduleItemViewModel(StringProperty title, ObjectProperty<FDSMainScheduleItemController.ItemType> itemType, ObjectProperty<ScheduleDateTime> schedule) {
    this.title = title;
    this.itemType = itemType;
    this.schedule = schedule;
  }

  public StringProperty titleProperty() {
    return title;
  }

  public ObjectProperty<FDSMainScheduleItemController.ItemType> itemTypeProperty() {
    return itemType;
  }

  public ObjectProperty<ScheduleDateTime> scheduleProperty() {
    return schedule;
  }
}
