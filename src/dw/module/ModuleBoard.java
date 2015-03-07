package dw.module;

import java.awt.Graphics;

import dw.control.ControlDialog;
import dw.control.ControlFrame;
import dw.control.ControlMenu;
import dw.control.ControlToolbar;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

public class ModuleBoard extends Module
{
	// Application
	private int appWidth;
	private int appHeight;
	private InputKeyboard appKeyboard;
	private InputMouse appMouse;
	
	// Dialog
	private ControlDialog moduleDialog;
	private boolean moduleDialogActive = false;
	
	// Editor
	private String editorTool;
	private boolean editorViewElevation;
	private boolean editorViewGridlines;
	private boolean editorViewPortals;
	private boolean editorViewStructs;
	
	// Module
	private ControlFrame moduleFrame;
	private ControlToolbar moduleToolbar;
	
	public ModuleBoard(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		appWidth = width;
		appHeight = height;
		appKeyboard = keyboard;
		appMouse = mouse;
		// init Tiles and Data arrays
		initFrame(width, height, keyboard, mouse);
		initToolbar(width, height, keyboard, mouse);
	}
	
	public void initFrame(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		moduleFrame = new ControlFrame("AppFrame", "Daydream Workshop", 0, 0, width, height, false, true, mouse);
	}
	
	public void initToolbar(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		// Toolbar Object
		moduleToolbar = new ControlToolbar("ToolbarBoard", 10, 40, width-20, 35);
		
		// Board Menu
		int toolbarProjectID = moduleToolbar.optionAdd("BOARD", "MENU", 25, 120, mouse);
		ControlMenu toolbarProjectMenu = new ControlMenu("ToolbarBoardMenuBoard", 10, 75, 200, mouse);
		toolbarProjectMenu.optionAdd("NEW BOARD", mouse, "DIALOG", "BoardNew");
		toolbarProjectMenu.optionAdd("LOAD BOARD", mouse, "DIALOG", "BoardOpen");
		toolbarProjectMenu.optionAdd("SAVE BOARD", mouse, "DIALOG", "BoardSave");
		toolbarProjectMenu.optionAdd("CLOSE EDITOR", mouse, "RETURN");
		moduleToolbar.optionAttachMenu(toolbarProjectID, toolbarProjectMenu);
		
		// ??? Menu
		/*int toolbarEditorID = moduleToolbar.optionAdd("EDITOR", "MENU", 153, 10, mouse);
		ControlMenu toolbarEditorMenu = new ControlMenu("ToolbarBoardMenuEditor", 130, 75, 200, mouse);
		toolbarEditorMenu.optionAdd("OPTIONS", mouse);
		toolbarEditorMenu.optionAdd("THEME", mouse);
		moduleToolbar.optionAttachMenu(toolbarEditorID, toolbarEditorMenu);*/
		
		// ??? Menu
		/*int toolbarHelpID = moduleToolbar.optionAdd("HELP", "MENU", 286, 120, mouse);
		ControlMenu toolbarHelpMenu = new ControlMenu("ToolbarAppMenuHelp", 250, 75, 160, mouse);
		toolbarHelpMenu.optionAdd("GUIDE", mouse);
		toolbarHelpMenu.optionAdd("ABOUT", mouse);
		moduleToolbar.optionAttachMenu(toolbarHelpID, toolbarHelpMenu);*/
	}
	
	public void render(Graphics g, int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		renderBackground(g, width, height);
		moduleFrame.render(g, mouse);
		moduleToolbar.render(g, keyboard, mouse);
		if(moduleDialogActive){moduleDialog.render(g, mouse);}
	}
	
	public void renderBackground(Graphics g, int width, int height)
	{
		g.setColor(GraphicsStyle.getColour("FrameBackground"));
		g.fillRect(0, 0, width, height);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		
	}

}