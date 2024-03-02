package dyrvania.resources;

import java.awt.Font;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class GameFont {

	private static final Font titleFont;
	private static final Font defaultFont;

	static {
		Font auxTitleFont = null;
		Font auxDefaultFont = null;

		try {
			auxTitleFont = Font.createFont(Font.TRUETYPE_FONT, GameFont.class.getResourceAsStream("/fonts/default.ttf"));
			auxDefaultFont = Font.createFont(Font.TRUETYPE_FONT, GameFont.class.getResourceAsStream("/fonts/default.ttf"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FONTS.getValue());
		}

		titleFont = auxTitleFont.deriveFont(Font.BOLD, 25);
		defaultFont = auxDefaultFont.deriveFont(Font.BOLD, 15);
	}

	public static Font getTitle() {
		return GameFont.titleFont;
	}

	public static Font getDefault() {
		return GameFont.defaultFont;
	}

}
