package dw;
import dw.file.FileWrite;
import dw.input.InputKeyboard;
import dw.input.InputMouse;
import dw.module.Module;
import dw.module.ModuleApp;
import dw.module.ModuleProjectNew;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JPanel;

public class Editor extends JPanel implements Runnable
{
	// Application
	public static EditorDisplay appDisplay;
	public String appTitle;
	public int appWidth, appHeight;
	private Thread appThread;
	private boolean appThreadRunning = false;
	private int appThreadTick;
	private BufferStrategy appBufferStrategy;
	private Graphics appGraphics;
	public InputKeyboard appKeyboard;
	public InputMouse appMouse;
	public static boolean appMouseListenClick = true;
	
	// Module
	private int moduleActive;
	private Module[] moduleArray = new Module[10];
	private int moduleCount;
	
	public Editor(int width, int height)
	{
		this.appHeight = height;
		this.appWidth = width;
	}
	
	private void init()
	{
		appKeyboard = new InputKeyboard();
		appMouse = new InputMouse();
		addMouseListener (appMouse);
		appDisplay = new EditorDisplay("Daydream Workshop", appWidth, appHeight, appKeyboard, appMouse);
		moduleInit();
	}
	
	public Module moduleGetActive()
	{
		return moduleArray[moduleActive];
	}
	
	private void moduleInit()
	{
		moduleNew(new ModuleApp(appWidth, appHeight, appKeyboard, appMouse), true, 0);
		moduleSetActive(0);
		moduleCount = 1;
	}
	
	public void moduleNew(Module newModule, boolean newFocus)
	{
		moduleNew(newModule, newFocus, moduleCount);
	}
	
	public void moduleNew(Module newModule, boolean newFocus, int newPosition)
	{
		moduleArray[newPosition] = newModule;
		if(newFocus==true){moduleSetActive(newPosition);}
	}
	
	public void moduleSetActive(int active)
	{
		moduleActive = active;
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
		moduleGetActive().render(appGraphics, appWidth, appHeight, appKeyboard, appMouse);
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
				render();
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
		// Temp
		if(appKeyboard.getKeyPressed()=="Enter")
		{
			System.out.println("ENTER");
			appKeyboard.keyPressedDone();
			moduleNew(new ModuleProjectNew(appWidth, appHeight, appKeyboard, appMouse, 0, 0, appWidth, appHeight), true, 1);
			moduleSetActive(1);
			moduleCount = 2;
			
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
		
		// See if any menu options have been clicked on
		//moduleGetActive();
	
		moduleGetActive().tick(appKeyboard, appMouse);
	}

}