package dw.module;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public abstract class Module
{
	public abstract void tick(InputKeyboard keyboard, InputMouse mouse);
	public abstract void render(Graphics g, int width, int height, InputMouse mouse);
}