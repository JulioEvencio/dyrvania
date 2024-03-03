package dyrvania.resources;

import java.awt.Font;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class GameFont {

	private static final Font titleFont;
	private static final Font titleLarge;
	private static final Font smallFont;
	private static final Font defaultFont;

	static {
		Font auxFont = null;

		try {
			auxFont = Font.createFont(Font.TRUETYPE_FONT, GameFont.class.getResourceAsStream("/fonts/default.ttf"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FONTS.getValue());
		}

		titleFont = auxFont.deriveFont(Font.BOLD, 30);
		titleLarge = auxFont.deriveFont(Font.BOLD, 50);
		smallFont = auxFont.deriveFont(Font.BOLD, 15);
		defaultFont = auxFont.deriveFont(Font.BOLD, 20);
	}

	public static Font getTitle() {
		return GameFont.titleFont;
	}

	public static Font getTitleLarge() {
		return GameFont.titleLarge;
	}

	public static Font getSmall() {
		return GameFont.smallFont;
	}

	public static Font getDefault() {
		return GameFont.defaultFont;
	}

}
