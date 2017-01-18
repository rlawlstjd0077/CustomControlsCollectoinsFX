package sample.sidemenu;

import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 */
public class ResizeWidthTranslation extends Transition {
  protected Region region;
  protected double startWidth;
  protected double newWidth;
  protected double widthDiff;

  public ResizeWidthTranslation(Duration duration, Region region, double newWidth) {
    setCycleDuration(duration);
    this.region = region;
    this.startWidth = region.getWidth();
    this.newWidth = newWidth;
    this.widthDiff = newWidth-startWidth;
  }

  @Override
  protected void interpolate(double frac) {
    region.setMinWidth( startWidth + ( widthDiff * frac ) );
    region.setPrefWidth( startWidth + ( widthDiff * frac ) );
    region.setMaxWidth( startWidth + ( widthDiff * frac ) );
  }
}
