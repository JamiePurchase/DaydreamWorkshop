package dw.control;
import dw.Editor;
import dw.graphics.GraphicsFont;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;
import dw.module.Module;
import dw.module.ModuleApp;
import dw.module.ModuleProjectNew;

import java.awt.Color;
import java.awt.Graphics;

public class ControlMenu
{
	// Menu
	private boolean menuActive;
	private int menuPosX;
	private int menuPosY;
	private int menuSizeX;
	private String menuRef;
	
	// Options
	private int optCount = 0;
	private String[] optText = new String[10];
	private String[] optType = new String[10];
	private String[] optTypeTarget = new String[10];
	private String[] optRefNexus = new String[10];
	private boolean[] optSelect = new boolean[10];
	private ControlMenu[] optLinkMenu = new ControlMenu[10];
	private int optLinkActive = 0; 
	
	public ControlMenu(String newRef, int newPosX, int newPosY, int newSizeX, InputMouse mouse)
	{
		menuRef = newRef;
		menuPosX = newPosX;
		menuPosY = newPosY;
		menuSizeX = newSizeX;
		menuActive = false;
		optCount = 0;
	}
	
	public int menuGetLink()
	{
		int linkActive = optLinkActive;
		optLinkActive = 0;
		return linkActive;
	}
	
	public void menuExpand()
	{
		if(menuActive==true){menuActive = false;}
		else{menuActive = true;}
	}
	
	public boolean menuGetActive()
	{
		return menuActive;
	}
	
	public void menuSetActive(boolean active)
	{
		menuActive = active;
	}
	
	public int optionAdd(String newText, InputMouse mouse)
	{
		return optionAdd(newText, mouse, "", "");
	}
	
	public int optionAdd(String newText, InputMouse mouse, String newType)
	{
		return optionAdd(newText, mouse, newType, "");
	}
	
	public int optionAdd(String newText, InputMouse mouse, String newType, String newTypeTarget)
	{
		int newOpt = optCount;
		optText[optCount] = newText;
		optType[newOpt] = newType;
		optTypeTarget[newOpt] = newTypeTarget;
		String newNexus = menuRef + "-opt" + optCount;
		mouse.nexusAdd(newNexus, menuPosX, menuPosY+(35*optCount), menuSizeX, 35);
		optRefNexus[optCount] = newNexus;
		optSelect[optCount] = false;
		optCount += 1;
		return newOpt;
	}
	
	public void render(Graphics g, InputMouse mouse)
	{
		if(menuActive==true)
		{
			g.setFont(GraphicsFont.getFont("MenuOption"));
			for(int opt=0;opt<optCount;opt+=1)
			{
				g.setColor(GraphicsStyle.getColour("MenuFill"));
				if(mouse.nexusCheckRef()==optRefNexus[opt]){g.setColor(GraphicsStyle.getColour("MenuFillHover"));}
				g.fillRect(menuPosX, menuPosY+(35*opt), menuSizeX, 35);
				g.setColor(GraphicsStyle.getColour("MenuText"));
				g.drawRect(menuPosX, menuPosY+(35*opt), menuSizeX, 35);
				g.drawString(optText[opt], menuPosX+10, menuPosY+(35*opt)+25);
			}
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		if(menuActive==true)
		{
			for(int opt=0;opt<optCount;opt+=1)
			{
				if(mouse.mouseNexusClick==optRefNexus[opt])
				{
					if(optType[opt]=="DIALOG")
					{
						Editor.appModules.requestDialog(optTypeTarget[opt]);
						menuActive = false;
					}
					if(optType[opt]=="MODULE")
					{
						Editor.appModules.requestModule(optTypeTarget[opt]);
						menuActive = false;
					}
					if(optType[opt]=="QUIT")
					{
						menuActive = false;
						// Note: We should prompt the user to save if there are outstanding changes
						System.exit(0);
					}
					mouse.mouseActionDone();
				}
			}
		}
	}
	
}