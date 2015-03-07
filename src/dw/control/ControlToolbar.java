package dw.control;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsFont;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class ControlToolbar
{
	// Toolbar
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

	public ControlToolbar(String newRef, int newPosX, int newPosY, int newSizeX, int newSizeY)
	{
		toolbarRef = newRef;
		toolbarPosX = newPosX;
		toolbarPosY = newPosY; 
		toolbarSizeX = newSizeX;
		toolbarSizeY = newSizeY;
	}
	
	public void menuCloseAll()
	{
		for(int opt=0;opt<optCount;opt+=1)
		{
			optChildMenu[opt].menuSetActive(false);
		}
	}
	
	public void menuCloseAllOthers(int id)
	{
		for(int opt=0;opt<optCount;opt+=1)
		{
			if(opt!=id && optType[opt]=="MENU")
			{
				optChildMenu[opt].menuSetActive(false);
			}
		}
	}
	
	public int optionAdd(String newText, String newType, int newTextX, int newSizeX, InputMouse mouse)
	{
		int newOption = optCount;
		optText[optCount] = newText;
		optType[optCount] = "";
		optTextX[optCount] = newTextX;
		optSizeX[optCount] = newSizeX;
		if(optCount>0){optPosX[optCount] = optPosX[optCount-1]+optSizeX[optCount-1];}
		else{optPosX[optCount] = toolbarPosX;}
		String newNexus = toolbarRef + "-opt" + optCount;
		mouse.nexusAdd(newNexus, optPosX[optCount], toolbarPosY, optSizeX[optCount], toolbarSizeY);
		optRefNexus[optCount] = newNexus;
		optSelect[optCount] = false;
		optCount += 1;
		return newOption;
	}
	
	public void optionAttachMenu(int opt, ControlMenu newMenu)
	{
		optType[opt] = "MENU";
		optChildMenu[opt] = newMenu;
	}
	
	public void render(Graphics g, InputKeyboard keyboard, InputMouse mouse)
	{
		// Background
		g.setColor(GraphicsStyle.getColour("ToolbarFill"));
		g.fillRect(toolbarPosX, toolbarPosY, toolbarSizeX, toolbarSizeY);
		
		// Border
		g.setColor(GraphicsStyle.getColour("ToolbarBorder"));
		g.drawRect(toolbarPosX, toolbarPosY, toolbarSizeX, toolbarSizeY);
		
		// Options
		for(int opt=0;opt<optCount;opt+=1)
		{
			// Highlight
			if(mouse.nexusCheckRef()==optRefNexus[opt])
			{
				g.setColor(GraphicsStyle.getColour("ToolbarFillHover"));
				g.fillRect(optPosX[opt], toolbarPosY, optSizeX[opt], toolbarSizeY);
			}
			
			// Border
			g.setColor(GraphicsStyle.getColour("MenuBorder"));
			g.drawRect(optPosX[opt], toolbarPosY, optSizeX[opt], toolbarSizeY);
			
			// Option Text
			g.setColor(GraphicsStyle.getColour("ToolbarText"));
			if(keyboard.getModifierPressed("ALT")==true)
			{
				AttributedString as = new AttributedString(optText[opt]);
				as.addAttribute(TextAttribute.FONT, GraphicsFont.getFont("MenuOption"));
				as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, 1);
				g.drawString(as.getIterator(), optTextX[opt], toolbarPosY+24);
			}
			else
			{
				g.setFont(GraphicsFont.getFont("MenuOption"));
				g.drawString(optText[opt], optTextX[opt], toolbarPosY+24);
			}
			
			// Temp
			if(optType[opt]=="MENU"){optChildMenu[opt].render(g, mouse);}
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		for(int opt=0;opt<optCount;opt+=1)
		{
			if(optType[opt]=="MENU"){optChildMenu[opt].tick(keyboard, mouse);}
		}
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
		for(int opt=0;opt<optCount;opt+=1)
		{
			if(mouse.mouseNexusClick==optRefNexus[opt])
			{
				if(optType[opt]=="MENU"){optChildMenu[opt].menuExpand();}
				menuCloseAllOthers(opt);
				mouse.mouseActionDone();
			}
		}
	}
	
}