package ui.common;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by GSD on 2017-02-06.
 */
public class ManeuverListTableRowViewModel {
  private DoubleProperty time;
  private StringProperty type;
  private StringProperty thrusterSetID;
  private DoubleProperty delVx;
  private DoubleProperty delVy;
  private DoubleProperty delVz;
  private FDSCheckBox checked = new FDSCheckBox();

  public interface MyOnChecked{
    public void onChecked();
  }

  private MyOnChecked myOnChecked;

  public void setMyOnChecked(MyOnChecked onChecked){
    myOnChecked = onChecked;
  }

  public ManeuverListTableRowViewModel(DoubleProperty time, StringProperty type, StringProperty thrusterSetID,
                                       DoubleProperty delVx, DoubleProperty delVy, DoubleProperty delVz){
     this.time = time;
     this.type = type;
     this.thrusterSetID = thrusterSetID;
     this.delVx = delVx;
     this.delVy = delVy;
     this.delVz = delVz;
     checked.selectedProperty().addListener(new ChangeListener<Boolean>() {
       @Override
       public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
         myOnChecked.onChecked();
       }
     });
  }

  public FDSCheckBox getChecked() {
    return checked;
  }
  public DoubleProperty timeProperty() {
    return time;
  }
  public StringProperty typeProperty() {
    return type;
  }
  public StringProperty thrusterSetIDProperty() {
    return thrusterSetID;
  }
  public DoubleProperty delVxProperty() {
    return delVx;
  }

  public DoubleProperty delVyProperty() {
    return delVy;
  }

  public DoubleProperty delVzProperty() {
    return delVz;
  }
}
