package dw;
import dw.application.AppWorkshop;
import dw.file.FileWrite;
import dw.input.InputKeyboard;
import dw.input.InputMouse;
import dw.module.Module;
import dw.module.ModuleApp;
import dw.module.ModuleManager;
import dw.module.ModuleProjectNew;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JPanel;

public class Editor extends JPanel implements Runnable
{
	// Application
	public String appTitle;
	public static EditorDisplay appDisplay;
	public int appWidth, appHeight;
	private BufferStrategy appBufferStrategy;
	private Graphics appGraphics;
	private String appModule;
	public static ModuleManager appModules;
	public static EditorProject appProject;
	private boolean appPause = false;
	
	// Input
	public InputKeyboard appKeyboard;
	public InputMouse appMouse;
	public static boolean appMouseListenClick = true;
	
	// Threads
	private Thread appThread;
	private boolean appThreadRunning = false;
	private int appThreadTick;
	
	public Editor(String module, int width, int height)
	{
		this.appModule = module;
		this.appHeight = height;
		this.appWidth = width;
	}
	
	public void editorPause(boolean pause)
	{
		appPause = pause;
	}
	
	public boolean editorPaused()
	{
		return appPause;
	}
	
	private void init()
	{
		appKeyboard = new InputKeyboard();
		appMouse = new InputMouse();
		addMouseListener (appMouse);
		appDisplay = new EditorDisplay("Daydream Workshop", appWidth, appHeight, appKeyboard, appMouse);
		appModules = new ModuleManager(appModule, appWidth, appHeight, appKeyboard, appMouse);
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
		appModules.getActive().render(appGraphics, appWidth, appHeight, appKeyboard, appMouse);
		appBufferStrategy.show();
		appGraphics.dispose();
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
		if(editorPaused())
		{
			if(appKeyboard.getKeyPressed()=="Space"){tickTest3();}
		}
		else
		{
			// Temp
			if(appKeyboard.getKeyPressed()=="Enter"){tickTest1();}
			if(appKeyboard.getKeyPressed()=="Escape"){tickTest2();}
			
			// See if any menu options have been clicked on
			//moduleGetActive();
		
			// Module Tick
			appModules.getActive().tick(appKeyboard, appMouse);
		}
	}
	
	private void tickTest1()
	{
		System.out.println("  ~ Editor Test 1 ~  ");
		appKeyboard.keyPressedDone();
		
		// Test 1
		FileWrite fw = new FileWrite("C:/Eclipse/Workspace/Daydream/data/editor/test1.dwec", false);
		try {
			fw.FileWriteLine("Hello world");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Test 2
		/*fw = new FileWrite("C:/Eclipse/Workspace/Daydream/data/editor/test2.txt", true);
		String[] myArray = new String[5];
		myArray[0] = "Hello";
		myArray[1] = "Hiya";
		myArray[2] = "";
		myArray[3] = "Hoho";
		fw.FileWriteArray(myArray);*/
	}
	
	private void tickTest2()
	{
		System.out.println("  ~ Editor Test 2 ~  ");
		appKeyboard.keyPressedDone();
		
		// Test
		String[] arguments = new String[3];
		arguments[0] = "ProjectNew";
		arguments[1] = "800";
		arguments[2] = "500";
		AppWorkshop.main(arguments);
		editorPause(true);
	}
	
	private void tickTest3()
	{
		System.out.println("  ~ Editor Test 3 ~  ");
		appKeyboard.keyPressedDone();
		
		// Temp
		editorPause(false);
	}

}