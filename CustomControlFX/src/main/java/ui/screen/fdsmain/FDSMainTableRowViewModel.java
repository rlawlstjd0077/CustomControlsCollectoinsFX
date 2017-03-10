package ui.screen.fdsmain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-03-07.
 */
public class FDSMainTableRowViewModel {
  private StringProperty name;
  private StringProperty firstIcon;
  private StringProperty secondIcon;
  private BooleanProperty visible;

  public FDSMainTableRowViewModel(StringProperty name, StringProperty firstIcon, StringProperty secondIcon, BooleanProperty visible) {
    this.name = name;
    this.firstIcon = firstIcon;
    this.secondIcon = secondIcon;
    this.visible = visible;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty firstIconProperty() {
    return firstIcon;
  }

  public StringProperty secondIconProperty() {
    return secondIcon;
  }

  public BooleanProperty visibleProperty() {
    return visible;
  }
}
