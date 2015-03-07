package dw.module;
import dw.control.ControlFrame;
import dw.control.ControlText;
import dw.control.ControlToolbar;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public class ModuleProjectNew extends Module
{
	// Module
	private ControlFrame moduleFrame;
	private int modulePosX;
	private int modulePosY;
	private int moduleSizeX;
	private int moduleSizeY;
	
	// Form
	private ControlText formTextName;
	
	public ModuleProjectNew(int width, int height, InputKeyboard keyboard, InputMouse mouse, int newPosX, int newPosY, int newSizeX, int newSizeY)
	{
		modulePosX = newPosX;
		modulePosY = newPosY;
		moduleSizeX = newSizeX;
		moduleSizeY = newSizeY;
		initFrame(mouse);
		initForm(mouse);
	}
	
	public void initFrame(InputMouse mouse)
	{
		moduleFrame = new ControlFrame("ProjectNewFrame", "New Project", modulePosX, modulePosY, moduleSizeX, moduleSizeY, true, true, mouse);
	}
	
	public void initForm(InputMouse mouse)
	{
		formTextName = new ControlText("ProjectNewName", 30, modulePosX+(moduleSizeX/2), modulePosY+50, 100, 50, mouse);
		formTextName.focus();
	}
	
	public void render(Graphics g, int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		// Background
		g.setColor(GraphicsStyle.getColour("FrameBackground"));
		g.fillRect(0, 0, width, height);
		
		moduleFrame.render(g, mouse);
		//moduleForm.render(g, mouse);
		
		// Temp
		formTextName.render(g, mouse);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		
	}

}