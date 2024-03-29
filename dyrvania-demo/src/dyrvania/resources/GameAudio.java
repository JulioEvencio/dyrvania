package dyrvania.resources;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class GameAudio {

	private Clip clip;

	public GameAudio(String fileName) {
		this(fileName, 0.0f);
	}

	public GameAudio(String fileName, float volume) {
		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameAudio.class.getResource(fileName))) {
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			this.clip = AudioSystem.getClip();

			if (AudioSystem.isLineSupported(info)) {
				this.clip.open(audioInputStream);
			} else {
				AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);

				try (AudioInputStream newAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream)) {
					this.clip.open(newAudioInputStream);
				}
			}

			FloatControl gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_AUDIOS.getValue());
		}
	}

	public void play() {
		this.stop();

		this.clip.setFramePosition(0);
		this.clip.start();
	}

	public void loop() {
		if (!this.clip.isRunning()) {
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}

	public void stop() {
		if (this.clip.isRunning()) {
			this.clip.stop();
		}
	}

}
