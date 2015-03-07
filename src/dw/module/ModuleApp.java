package dw.module;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ModuleApp extends Module
{
	public ModuleApp()
	{
	}
	
	public void render(Graphics g, int width, int height)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, width, height);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		tickInputKeyboard(keyboard);
		tickInputMouse(mouse);
	}
	
	public void tickInputKeyboard(InputKeyboard keyboard)
	{
		if(keyboard.getKeyPressed()=="Space" || keyboard.getKeyPressed()=="Enter")
		{
			System.out.println("Keyboard pressed...");
		}
	}
	
	public void tickInputMouse(InputMouse mouse)
	{
		if(mouse.mouseActionPressedL==true)
		{
			System.out.println("Mouse clicked...");
		}
	}

}