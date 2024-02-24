import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class FilePlayer {


	public void play(URL filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(filePath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch (Exception e) {
			System.out.println("Error with playing sound.");
			e.printStackTrace();
		}

	}
}
