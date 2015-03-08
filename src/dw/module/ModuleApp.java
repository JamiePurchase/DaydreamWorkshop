package dw.module;
import dw.Editor;
import dw.control.ControlDialog;
import dw.control.ControlFrame;
import dw.control.ControlMenu;
import dw.control.ControlText;
import dw.control.ControlToolbar;
import dw.file.FileTest;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

public class ModuleApp extends Module
{
	private int appWidth;
	private int appHeight;
	private InputKeyboard appKeyboard;
	private InputMouse appMouse;
	private ControlDialog moduleDialog;
	private boolean moduleDialogActive = false;
	private ControlFrame moduleFrame;
	private ControlToolbar moduleToolbar;
	private boolean moduleBkgImageActive;
	private String moduleBkgImageFile;
	
	public ModuleApp(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		appWidth = width;
		appHeight = height;
		appKeyboard = keyboard;
		appMouse = mouse;
		initBackground(width, height);
		initFrame(width, height, keyboard, mouse);
		initToolbar(width, height, keyboard, mouse);
		//
		FileTest ft = new FileTest();
		//
	}
	
	public void dialogRequest(String dialog)
	{
		moduleDialog = Editor.appModules.dialogFetch(dialog);
		moduleDialogActive = true;
		Editor.appModules.requestDialogDone();
	}
	
	public ControlToolbar getModuleToolbar()
	{
		return moduleToolbar;
	}
	
	public void initBackground(int width, int height)
	{
		moduleBkgImageActive = false;
		moduleBkgImageFile = "";
	}
	
	public void initFrame(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		moduleFrame = new ControlFrame("AppFrame", "Daydream Workshop", 0, 0, width, height, false, true, mouse);
	}
	
	public void initToolbar(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		// Toolbar Object
		moduleToolbar = new ControlToolbar("ToolbarApp", 10, 40, width-20, 35);
		
		// Project Menu
		int toolbarProjectID = moduleToolbar.optionAdd("PROJECT", "MENU", 25, 120, mouse);
		ControlMenu toolbarProjectMenu = new ControlMenu("ToolbarAppMenuProject", 10, 75, 200, mouse);
		toolbarProjectMenu.optionAdd("NEW PROJECT", mouse, "DIALOG", "ProjectNew");
		toolbarProjectMenu.optionAdd("LOAD PROJECT", mouse, "MODULE", "ProjectOpen");
		toolbarProjectMenu.optionAdd("EXIT EDITOR", mouse, "QUIT");
		moduleToolbar.optionAttachMenu(toolbarProjectID, toolbarProjectMenu);
		
		// Editor Menu
		int toolbarEditorID = moduleToolbar.optionAdd("EDITOR", "MENU", 153, 120, mouse);
		ControlMenu toolbarEditorMenu = new ControlMenu("ToolbarAppMenuEditor", 130, 75, 200, mouse);
		toolbarEditorMenu.optionAdd("OPTIONS", mouse);
		toolbarEditorMenu.optionAdd("THEME", mouse);
		moduleToolbar.optionAttachMenu(toolbarEditorID, toolbarEditorMenu);
		
		// Help Menu
		int toolbarHelpID = moduleToolbar.optionAdd("HELP", "MENU", 286, 120, mouse);
		ControlMenu toolbarHelpMenu = new ControlMenu("ToolbarAppMenuHelp", 250, 75, 160, mouse);
		toolbarHelpMenu.optionAdd("GUIDE", mouse);
		toolbarHelpMenu.optionAdd("ABOUT", mouse, "DIALOG", "About");
		moduleToolbar.optionAttachMenu(toolbarHelpID, toolbarHelpMenu);
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
		
		// Test
		g.drawImage(GraphicsDrawing.getImage("ui/logo.png"), (width/2)-150, (height/2)-200+20, null);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		if(Editor.appModules.requestDialogActive()){dialogRequest(Editor.appModules.requestDialogString());}
		if(moduleDialogActive){moduleDialog.tick(keyboard, mouse);}
		else
		{
			if(mouse.mouseActionPressedL && mouse.mouseNexusClick=="")
			{
				moduleToolbar.menuCloseAll();
				mouse.mouseActionDone();
			}
			moduleFrame.tick(keyboard, mouse);
			moduleToolbar.tick(keyboard, mouse);
			//mouse.mouseActionDone();
		}
	}

}