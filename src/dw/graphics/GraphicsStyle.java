package dw.graphics;
import dw.Editor;

import java.awt.Color;

public class GraphicsStyle
{

	public static Color getColour(String style)
	{
		if(Editor.settingsGetTheme().contains("DigitalSapling"))
		{
			if(style=="ButtonQuit"){return GraphicsDrawing.getColorRGB(150,25,25);}
			if(style=="ButtonQuitHover"){return GraphicsDrawing.getColorRGB(200,25,25);}
			if(style=="FrameBackground"){return GraphicsDrawing.getColorRGB(100,150,75);}
			if(style=="FrameBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="FrameFill"){return GraphicsDrawing.getColorRGB(25,150,25);}
			if(style=="InputBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="InputFill"){return GraphicsDrawing.getColorRGB(255,255,255);}
			if(style=="InputText"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="MenuBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="MenuFill"){return GraphicsDrawing.getColorRGB(15,100,15);}
			if(style=="MenuFillHover"){return GraphicsDrawing.getColorRGB(75,200,75);}
			if(style=="MenuText"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="ToolbarBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="ToolbarFill"){return GraphicsDrawing.getColorRGB(15,100,15);}
			if(style=="ToolbarFillHover"){return GraphicsDrawing.getColorRGB(75,200,75);}
			if(style=="ToolbarText"){return GraphicsDrawing.getColorRGB(0,0,0);}
		}
		if(Editor.settingsGetTheme().contains("New"))
		{
			if(style=="ButtonQuit"){return GraphicsDrawing.getColorRGB(150,25,150);}
			if(style=="ButtonQuitHover"){return GraphicsDrawing.getColorRGB(200,25,200);}
			if(style=="FrameBackground"){return GraphicsDrawing.getColorRGB(100,150,100);}
			if(style=="FrameBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="FrameFill"){return GraphicsDrawing.getColorRGB(25,150,50);}
			if(style=="InputBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="InputFill"){return GraphicsDrawing.getColorRGB(255,255,255);}
			if(style=="InputText"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="MenuBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="MenuFill"){return GraphicsDrawing.getColorRGB(15,100,50);}
			if(style=="MenuFillHover"){return GraphicsDrawing.getColorRGB(75,200,125);}
			if(style=="MenuText"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="ToolbarBorder"){return GraphicsDrawing.getColorRGB(0,0,0);}
			if(style=="ToolbarFill"){return GraphicsDrawing.getColorRGB(15,100,50);}
			if(style=="ToolbarFillHover"){return GraphicsDrawing.getColorRGB(75,200,100);}
			if(style=="ToolbarText"){return GraphicsDrawing.getColorRGB(0,0,0);}
		}
		return GraphicsDrawing.getColorRGB(0, 0, 0);
	}
	
}