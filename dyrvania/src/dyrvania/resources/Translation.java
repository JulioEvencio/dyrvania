package dyrvania.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import dyrvania.Main;
import dyrvania.strings.GameString;
import dyrvania.strings.StringError;
import dyrvania.strings.StringScreen;

public class Translation {

	private static String language = "english";

	public enum Language {
		ENGLISH, PORTUGUESE, SPANISH;
	}

	public static String getLanguage() {
		return Translation.language;
	}

	public static void changeTheLanguage(Language language) {
		if (language == Translation.Language.PORTUGUESE) {
			Translation.language = "portuguese";
		} else if (language == Translation.Language.SPANISH) {
			Translation.language = "spanish";
		} else {
			Translation.language = "english";
		}

		try {
			Translation.toTranslation("screen", new GameString[] {
					StringScreen.TUTORIAL_FULL_SCREEN,
					StringScreen.NEW_GAME,
					StringScreen.LOAD_GAME,
					StringScreen.CREDITS,
					StringScreen.SETTINGS,
					StringScreen.EXIT
			});

			Translation.toTranslation("level", new GameString[] {
					// Code
			});
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}
	}

	private static void toTranslation(String file, GameString[] messageString) throws IOException {
		String fileName = String.format("/languages/%s/%s.txt", Translation.language, file);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Translation.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			for (int i = 0; i < messageString.length; i++) {
				messageString[i].setValue(Translation.readLine(reader));
			}
		}
	}

	private static String readLine(BufferedReader reader) throws IOException {
		String content = reader.readLine();

		if (content == null) {
			throw new IOException();
		}

		return content;
	}

}