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
	private ControlMenu[] optChildMenu = new ControlMenu[10];

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
		int newOption = optCount;
		optText[optCount] = newText;
		optType[optCount] = "";
		optTextX[optCount] = newTextX;
		optSizeX[optCount] = newSizeX;
		if(optCount>0){optPosX[optCount] = optPosX[optCount-1]+optSizeX[optCount-1];}
		else{optPosX[optCount] = toolbarPosX+1;}
		String newNexus = toolbarRef + "-opt" + optCount;
		mouse.nexusAdd(newNexus, optPosX[optCount], toolbarPosY+1, optSizeX[optCount], toolbarSizeY-1);
		optRefNexus[optCount] = newNexus;
		optSelect[optCount] = false;
		optCount += 1;
		
		// Debug
		System.out.println("Added nexus for the new toolbar option ("+newText+")");
		int nH = toolbarSizeY-1;
		System.out.println(newNexus + "x " + optPosX[optCount] + ", y " + toolbarPosY+1 + ", w " + optSizeX[optCount] + ", h " + nH);
	}
	
	public void optionAttachMenu(int opt, ControlMenu newMenu)
	{
		optType[opt] = "MENU";
		optChildMenu[opt] = newMenu;
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
			
			// Temp
			if(optType[opt]=="MENU"){optChildMenu[opt].render(g, mouse);}
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		//tickKeyboard(keyboard);
		if(mouse.mouseActionPressedL==true){tickNexus(mouse);}
	}
	
	/*public tickKeyboard(InputKeyboard keyboard)
	{
		if(keyboard.getKeyPressed()=="Space" || keyboard.getKeyPressed()=="Enter")
		{
			System.out.println("Keyboard pressed...");
		}
	}*/
	
	public void tickNexus(InputMouse mouse)
	{
		// Debug
		System.out.println("Toolbar tickNexus (nexus = " + mouse.mouseNexusClick + ")");
		
		for(int opt=0;opt<optCount;opt+=1)
		{
			// Debug
			System.out.println(" - checking opt " + opt + ": " + optText[opt] + " (" + optRefNexus[opt] + ")");
			
			if(mouse.mouseNexusClick==optRefNexus[opt])
			{
				// Debug
				System.out.println("EXPAND " + opt);
				
				if(optType[opt]=="MENU"){optChildMenu[opt].menuExpand();}
			}
		}
	}
	
}