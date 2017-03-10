package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import java.io.IOException;

/**
 * UI에 공통적으로 사용되는 기능을 제공.
 */
public class UiUtil {
  private static GlyphFont fontAwesome;

  /**
   * 폰트 초기화 및 로드.
   */
  public static void initializeFont() {
    String[] fonts = new String[]{
      "Roboto-Black.ttf",
      "Roboto-BlackItalic.ttf",
      "Roboto-Bold.ttf",
      "Roboto-BoldItalic.ttf",
      "Roboto-Italic.ttf",
      "Roboto-Light.ttf",
      "Roboto-LightItalic.ttf",
      "Roboto-Medium.ttf",
      "Roboto-MediumItalic.ttf",
      "Roboto-Regular.ttf",
      "Roboto-Thin.ttf",
      "Roboto-ThinItalic.ttf",
      "RobotoCondensed-Bold.ttf",
      "RobotoCondensed-BoldItalic.ttf",
      "RobotoCondensed-Italic.ttf",
      "RobotoCondensed-Light.ttf",
      "RobotoCondensed-LightItalic.ttf",
      "RobotoCondensed-Regular.ttf",
    };
    for (String font : fonts) {
      Font.loadFont(UiUtil.class.getResourceAsStream("/commons/ui/fonts/" + font), 12);
    }

    fontAwesome = GlyphFontRegistry.font("FontAwesome");
  }

  /**
   * Fxml을 Controller 클래스와 동일한 위치에서 가져오기.
   * 예) LoginController 의 경우 Login.fxml 로 변환
   *
   * @param clazz Controller 이름.
   * @return FXML Loader.
   */
  public static FXMLLoader getFxmlLoader(Class<?> clazz) {
    FXMLLoader loader = new FXMLLoader();
    final String fxmlName = clazz.getSimpleName().replace("Controller", "") + ".fxml";
    loader.setLocation(clazz.getResource(fxmlName));
    return loader;
  }

  /**
   * FXML을 Controller 이름을 기반으로 추출하여 읽고 등록.
   *
   * @param controller Controller 인스턴스.
   * @throws IOException 파일이 없을 때 발생.
   */
  public static void loadFxml(Parent controller) throws IOException {
    final FXMLLoader loader = getFxmlLoader(controller.getClass());
    loader.setRoot(controller);
    loader.setController(controller);
    loader.load();
  }

  public static Node createGlyph(FontAwesome.Glyph glyph) {
    return fontAwesome.create(glyph);
  }


}
