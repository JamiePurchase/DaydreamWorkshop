package dw.module;
import dw.control.ControlFrame;
import dw.control.ControlMenu;
import dw.control.ControlToolbar;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsFont;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ModuleApp extends Module
{
	private ControlFrame frame;
	private ControlToolbar toolbar;
	
	public ModuleApp(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		initFrame(width, height, keyboard, mouse);
		initToolbar(width, mouse);
	}
	
	public void initFrame(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		frame = new ControlFrame("AppFrame", "Daydream Workshop", 0, 0, width, height, GraphicsDrawing.getColorRGB(25,150,25), true, mouse);
	}
	
	public void initToolbar(int width, InputMouse mouse)
	{
		// Toolbar Object
		toolbar = new ControlToolbar("ToolbarApp", 10, 40, width-20, 35, GraphicsDrawing.getColorRGB(15,100,15));
		
		// Editor Menu
		toolbar.optionAdd("EDITOR", "MENU", 33, 120, mouse);
		ControlMenu menuEditor = new ControlMenu("ToolbarAppMenuEditor", 11, 75, 120, GraphicsDrawing.getColorRGB(25,150,25), mouse);
		menuEditor.optionAdd("NEW PROJECT", "ACTION", mouse);
		menuEditor.optionAdd("LOAD PROJECT", "ACTION", mouse);
		toolbar.optionAttachMenu(0, menuEditor);
		
		// About Menu
		toolbar.optionAdd("HELP", "MENU", 153, 100, mouse);
		ControlMenu menuHelp = new ControlMenu("ToolbarAppMenuHelp", 133, 75, 120, GraphicsDrawing.getColorRGB(25,150,25), mouse);
		menuHelp.optionAdd("GUIDE", "ACTION", mouse);
		menuHelp.optionAdd("ABOUT", "ACTION", mouse);
		toolbar.optionAttachMenu(1, menuHelp);
	}
	
	public void render(Graphics g, int width, int height, InputMouse mouse)
	{
		renderBackground(g, width, height);
		frame.render(g, mouse);
		toolbar.render(g, mouse);
		
		// Temp (clicking in one place then hovering over a link was activating
		mouse.mouseActionDone();
	}
	
	public void renderBackground(Graphics g, int width, int height)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		frame.tick(keyboard, mouse);
		toolbar.tick(keyboard, mouse);
	}

}