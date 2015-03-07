package dw;
import dw.input.InputKeyboard;
import dw.input.InputMouse;
import dw.module.Module;
import dw.module.ModuleApp;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
		moduleNew(new ModuleApp(), 0);
		moduleSetActive(0);
		moduleCount = 1;
	}
	
	public void moduleNew(Module newModule)
	{
		moduleNew(newModule, moduleCount);
	}
	
	public void moduleNew(Module newModule, int newPosition)
	{
		moduleArray[newPosition] = newModule;
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
		moduleGetActive().render(appGraphics, appWidth, appHeight);
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
				moduleGetActive().tick(appKeyboard, appMouse);
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

}