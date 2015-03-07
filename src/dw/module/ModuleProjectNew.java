package dw.module;
import dw.control.ControlFrame;
import dw.control.ControlToolbar;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public class ModuleProjectNew extends Module
{
	//private ControlForm moduleForm;
	private ControlFrame moduleFrame;
	private int modulePosX;
	private int modulePosY;
	private int moduleSizeX;
	private int moduleSizeY;
	
	public ModuleProjectNew(int width, int height, InputKeyboard keyboard, InputMouse mouse, int newPosX, int newPosY, int newSizeX, int newSizeY)
	{
		modulePosX = newPosX;
		modulePosY = newPosY;
		moduleSizeX = newSizeX;
		moduleSizeY = newSizeY;
		initFrame(mouse);
		initForm();
	}
	
	public void initFrame(InputMouse mouse)
	{
		moduleFrame = new ControlFrame("ProjectNewFrame", "New Project", modulePosX, modulePosY, moduleSizeX, moduleSizeY, true, false, mouse);
	}
	
	public void initForm()
	{
		//moduleForm = new ControlForm();
	}
	
	public void render(Graphics g, int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		moduleFrame.render(g, mouse);
		//moduleForm.render(g, mouse);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		
	}

}