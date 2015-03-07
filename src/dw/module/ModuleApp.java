package dw.module;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsFont;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ModuleApp extends Module
{
	public ModuleApp(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		initNexus(width, height, keyboard, mouse);
	}
	
	public void initNexus(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		mouse.nexusAdd("EditorFrameButtonClose", width-38, 7, 26, 26);
	}
	
	public void render(Graphics g, int width, int height, InputMouse mouse)
	{
		renderBackground(g, width, height);
		renderFrame(g, width, height, mouse);
		renderToolbar(g, width);
	}
	
	public void renderBackground(Graphics g, int width, int height)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
	
	public void renderFrame(Graphics g, int width, int height, InputMouse mouse)
	{
		// Padding
		g.setColor(GraphicsDrawing.getColorRGB(25,150,25));
		g.fillRect(0, 0, 10, height);
		g.fillRect(0, 0, width, 40);
		g.fillRect(width-10, 0, width, height);
		g.fillRect(0, height-10, width, height);
		
		// Border
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		g.drawRect(10, 40, width-20, height-50);

		// Title
		g.setFont(GraphicsFont.getFont("ModuleFrameTitle"));
		g.drawString("Daydream Workshop", 28, 28);

		// Exit Button
		g.setColor(GraphicsDrawing.getColorRGB(150,25,25));
		if(mouse.nexusCheckRef()=="EditorFrameButtonClose"){g.setColor(GraphicsDrawing.getColorRGB(200,25,25));}
		g.fillRect(width-38, 7, 26, 26);
		g.setColor(Color.BLACK);
		g.drawRect(width-38, 7, 26, 26);
		g.setFont(GraphicsFont.getFont("ModuleFrameTitle"));
		g.drawString("x", width-30, 27);
	}
	
	public void renderToolbar(Graphics g, int width)
	{
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
			if(mouse.mouseNexusClick=="EditorFrameButtonClose")
			{
				System.exit(0);
			}
		}
	}

}