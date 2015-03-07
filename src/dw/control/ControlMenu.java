package dw.control;
import dw.graphics.GraphicsFont;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ControlMenu
{
	// Menu
	private boolean menuActive;
	private Color menuBkg;
	private int menuPosX;
	private int menuPosY;
	private int menuSizeX;
	private String menuRef;
	
	// Options
	private int optCount = 0;
	private String[] optText = new String[10];
	private String[] optType = new String[10];
	private String[] optRefNexus = new String[10];
	private boolean[] optSelect = new boolean[10];
	
	public ControlMenu(String newRef, int newPosX, int newPosY, int newSizeX, Color newBkg, InputMouse mouse)
	{
		menuRef = newRef;
		menuPosX = newPosX;
		menuPosY = newPosY;
		menuSizeX = newSizeX;
		menuBkg = newBkg;
		menuActive = false;
		optCount = 0;
	}
	
	public void menuExpand()
	{
		if(menuActive==true){menuActive = false;}
		else{menuActive = true;}
		
		// Debug
		System.out.println("Expanded " + menuRef + " (now " + menuActive + ")");
	}
	
	public boolean menuGetActive()
	{
		return menuActive;
	}
	
	public void menuSetActive(boolean active)
	{
		menuActive = active;
	}
	
	public void optionAdd(String newText, String newType, InputMouse mouse)
	{
		optText[optCount] = newText;
		optType[optCount] = newType;
		String newNexus = menuRef + "-opt" + optCount;
		mouse.nexusAdd(newNexus, menuPosX, menuPosY+(35*optCount), menuSizeX, 35);
		optRefNexus[optCount] = newNexus;
		optSelect[optCount] = false;
		optCount += 1;
		
		// Debug
		System.out.println("Added nexus for the new toolbar option ("+newText+")");
		System.out.println(newNexus + "x " + menuPosX + ", y " + menuPosY+(35*optCount) + ", w " + menuSizeX + ", h 35");
	}
	
	public void render(Graphics g, InputMouse mouse)
	{
		if(menuActive==true)
		{
			// Debug
			System.out.println("Rendering " + menuRef + " (optionCount: " + optCount + ")");
			
			g.setFont(GraphicsFont.getFont("ModuleFrameMenu"));
			for(int opt=0;opt<optCount;opt+=1)
			{
				g.setColor(Color.GRAY);
				if(mouse.nexusCheckRef()==optRefNexus[opt]){g.setColor(Color.WHITE);}
				g.fillRect(menuPosX, menuPosY+(35*opt), menuSizeX, 35);
				g.setColor(Color.BLACK);
				g.drawRect(menuPosX, menuPosY+(35*opt), menuSizeX, 35);
				g.drawString(optText[opt], menuPosX+22, menuPosY+(35*opt)+10);
			}
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
	
	}
	
}