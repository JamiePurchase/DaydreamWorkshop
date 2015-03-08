package dw.audio;
import dw.audio.AudioPlayer;

public class AudioManager
{
	private static boolean active = true;
	private static boolean musicActive = false;
	private static String musicFile;
	private static boolean soundActive = false;
	private static String soundFile;
	private static int soundTick;
	private static int volume = 100;

	public AudioManager()
	{
		AudioPlayer.init();
		initJas();
		initMusic();
		initSounds();
	}
	
	public void changeMusic(String music)
	{
		if(musicActive){stopMusic();}
		playMusic(music);
	}

	public boolean getMusicActive()
	{
		return musicActive;
	}
	
	public boolean getSoundActive()
	{
		return soundActive;
	}
	
	public void initJas()
	{
		AudioPlayer.load("jas/welcome.wav", "jasWelcome");
		AudioPlayer.load("jas/goodbye.wav", "jasGoodbye");
	}
	
	public void initMusic()
	{
		/*AudioPlayer.load("music/theme1.wav", "music1");
		AudioPlayer.load("music/battle1.wav", "battle1");
		AudioPlayer.load("music/battle2.wav", "battle2");
		AudioPlayer.load("music/battle3.wav", "battle3");
		AudioPlayer.load("music/battle4.wav", "battle4");
		AudioPlayer.load("music/battle5.wav", "battle5");
		AudioPlayer.load("music/battle6.wav", "battle6");*/
	}
	
	public void initSounds()
	{
		// Note: Do we need these?
		/*AudioPlayer.load("/sounds/collectGarnet.wav", "Garnet");
		AudioPlayer.load("/sounds/collectMushroom.wav", "Mushroom");
		AudioPlayer.load("/sounds/collectTreasure.wav", "Treasure");*/
	}
	
	public void playMusic(String music)
	{
		AudioPlayer.play(music);
		musicActive = true;
		musicFile = music;
	}
	
	public void playSound(String sound)
	{
		AudioPlayer.play(sound);
		soundActive = true;
		soundFile = sound;
		soundTick = 0;
	}
	
	public void setVolume(int newVolume)
	{
		volume = newVolume;
		if(newVolume<1){active = false;}
		else{active = true;}
	}
	
	public void stopMusic()
	{
		AudioPlayer.stop(musicFile);
		musicActive = false;
		musicFile = "";
	}
	
	public void stopSound()
	{
		//AudioPlayer.stop(soundFile);
		soundActive = false;
		soundFile = "";
	}
	
	public void tick()
	{
		if(getSoundActive())
		{
			soundTick += 1;
			//if(soundTick>1){stopSound();}
		}
	}
}