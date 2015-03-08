package dw;
import dw.application.AppWorkshop;
import dw.audio.AudioManager;
import dw.file.FileRead;
import dw.file.FileWrite;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;
import dw.module.Module;
import dw.module.ModuleApp;
import dw.module.ModuleManager;
import dw.module.ModuleProjectNew;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class Editor extends JPanel implements Runnable
{
	// Application
	public String appTitle;
	public static EditorDisplay appDisplay;
	public static int appWidth;
	public static int appHeight;
	private BufferStrategy appBufferStrategy;
	private static Graphics appGraphics;
	private GraphicsStyle appStyle;
	public static EditorProject appProject;
	private boolean appPause = false;
	private static boolean appTerminate = false;
	private static int appTerminateTick;
	
	// Audio
	public static AudioManager appAudio;
	
	// Input
	public InputKeyboard appKeyboard;
	public InputMouse appMouse;
	public static boolean appMouseListenClick = true;
	
	// Modules
	private String appModule;
	public static ModuleManager appModules;
	
	// Settings
	private static String settingTheme;
	
	// Threads
	private Thread appThread;
	private boolean appThreadRunning = false;
	private int appThreadTick;
	
	public Editor(String module, int width, int height)
	{
		this.appHeight = height;
		this.appWidth = width;
		this.appModule = module;
	}
	
	public void editorPause(boolean pause)
	{
		appPause = pause;
	}
	
	public boolean editorPaused()
	{
		return appPause;
	}
	
	public static void editorTerminate()
	{
		appTerminate = true;
		appTerminateTick = 180;
		appAudio.playMusic("jasGoodbye");
	}
	
	private void init()
	{
		settingsLoad();
		appAudio = new AudioManager();
		appAudio.playMusic("jasWelcome");
		appKeyboard = new InputKeyboard();
		appMouse = new InputMouse();
		addMouseListener (appMouse);
		appDisplay = new EditorDisplay("Daydream Workshop", appWidth, appHeight, appKeyboard, appMouse);
		appModules = new ModuleManager(appWidth, appHeight, appKeyboard, appMouse);
		appModules.setActive(appModule);
	}

	private void render()
	{
		appBufferStrategy = appDisplay.getCanvas().getBufferStrategy();
		if(appBufferStrategy == null)
		{
			appDisplay.getCanvas().createBufferStrategy(3);
			return;
		}
		appGraphics = appBufferStrategy.getDrawGraphics();
		if(appTerminate){renderTerminate(appGraphics);}
		else{appModules.getActive().render(appGraphics, appWidth, appHeight, appKeyboard, appMouse);}
		appBufferStrategy.show();
		appGraphics.dispose();
	}
	
	private void renderTerminate(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, appWidth, appHeight);
		g.drawImage(GraphicsDrawing.getImage("ui/logo.png"), (appWidth/2)-150, (appHeight/2)-200+20, null);
	}
	
	public void run()
	{
		// Render speed
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		// Create window
		init();
		
		// Main game loop
		while(appThreadRunning)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1)
			{
				appThreadTick+= 1;
				tick();
				if(!editorPaused()){render();}
				ticks++;
				delta--;
			}
			if(timer >= 1000000000)
			{
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public static String settingsGetTheme()
	{
		return settingTheme;
	}
	
	private void settingsLoad()
	{
		String[] settings = settingsLoadData();
		settingsSetTheme(settingsLoadValue(settings[0]));
	}
	
	private String[] settingsLoadData()
	{
		String[] fileData = new String[5];
		FileRead fileRead = new FileRead("editor/settings.dwc");
		try{fileData = fileRead.FileReadData();}
		catch (IOException e){e.printStackTrace();}
		return fileData;
	}
	
	private String settingsLoadValue(String data)
	{
		return data.substring(data.indexOf("[")+1, data.indexOf("]"));
	}
	
	private void settingsSave()
	{
		// Create an array of settings
		String[] settings = new String[5];
		settings[0] = settingsGetTheme();
		
		// Overwrite the settings file
		FileWrite fileSave = new FileWrite("editor/settings.dwc", false);
		fileSave.FileWriteArray(settings);
	}
	
	private void settingsSetTheme(String theme)
	{
		settingTheme = theme;
	}
	
	public synchronized void start()
	{
		start(false);
	}
	
	public synchronized void start(boolean dev)
	{
		if(appThreadRunning==false)
		{
			appThreadRunning = true;
			appThread = new Thread(this);
			appThread.start();
		}
	}
	
	public synchronized void stop()
	{
		if(appThreadRunning==true)
		{
			try
			{
				appThread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public int threadGetTick()
	{
		return appThreadTick;
	}
	
	public void tick()
	{
		if(appTerminate)
		{
			appTerminateTick -= 1;
			if(appTerminateTick<1){System.exit(0);}
		}
		else
		{
			appModules.getActive().tick(appKeyboard, appMouse);
		}
	}

}