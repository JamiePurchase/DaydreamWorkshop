package dw.control;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsFont;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ControlToolbar
{
	// Toolbar
	private Color toolbarBkg;
	private int toolbarPosX;
	private int toolbarPosY;
	private String toolbarRef;
	private int toolbarSizeX;
	private int toolbarSizeY;
	
	// Options
	private int optCount = 0;
	private String[] optText = new String[10];
	private int[] optTextX = new int[10];
	private String[] optType = new String[10];
	private int[] optPosX = new int[10];
	private int[] optSizeX = new int[10];
	private String[] optRefNexus = new String[10];
	private boolean[] optSelect = new boolean[10];

	public ControlToolbar(String newRef, int newPosX, int newPosY, int newSizeX, int newSizeY, Color newBkg)
	{
		toolbarRef = newRef;
		toolbarPosX = newPosX;
		toolbarPosY = newPosY; 
		toolbarSizeX = newSizeX;
		toolbarSizeY = newSizeY;
		toolbarBkg = newBkg;
	}
	
	public void optionAdd(String newText, String newType, int newTextX, int newSizeX, InputMouse mouse)
	{
		optText[optCount] = newText;
		optType[optCount] = newType;
		optTextX[optCount] = newTextX;
		optSizeX[optCount] = newSizeX;
		if(optCount>0){optPosX[optCount] = optPosX[optCount-1]+optSizeX[optCount-1];}
		else{optPosX[optCount] = toolbarPosX+1;}
		String newNexus = toolbarRef + "-opt" + optCount;
		mouse.nexusAdd(newNexus, optPosX[optCount], toolbarPosY+1, optSizeX[optCount], toolbarSizeY-1);
		optRefNexus[optCount] = newNexus;
		optSelect[optCount] = false;
		optCount += 1;
	}
	
	public void render(Graphics g, InputMouse mouse)
	{
		// Background
		g.setColor(toolbarBkg);
		g.fillRect(toolbarPosX, toolbarPosY, toolbarSizeX, toolbarSizeY);
		
		// Border
		g.setColor(Color.BLACK);
		g.drawRect(toolbarPosX, toolbarPosY, toolbarSizeX, toolbarSizeY);
		
		// Options
		g.setFont(GraphicsFont.getFont("ModuleFrameMenu"));
		for(int opt=0;opt<optCount;opt+=1)
		{
			if(mouse.nexusCheckRef()==optRefNexus[opt])
			{
				g.setColor(GraphicsDrawing.getColorRGB(75,200,75));
				g.fillRect(optPosX[opt], toolbarPosY+1, optSizeX[opt], toolbarSizeY-1);
			}
			g.setColor(Color.BLACK);
			g.drawString(optText[opt], optTextX[opt], toolbarPosY+24);
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		if(keyboard.getKeyPressed()=="Space" || keyboard.getKeyPressed()=="Enter")
		{
			System.out.println("Keyboard pressed...");
		}
	}
	
}