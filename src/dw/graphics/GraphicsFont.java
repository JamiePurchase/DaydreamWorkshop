package dw.graphics;

import java.awt.Font;

public class GraphicsFont
{

	public static Font getFont(String style)
	{
		if(style=="FrameTitle"){return new Font("Segoe Print", Font.PLAIN, 22);}
		if(style=="InputValue"){return new Font("Courier", Font.PLAIN, 22);}
		if(style=="MenuOption"){return new Font("Courier", Font.PLAIN, 22);}
		return new Font("MV Boli", Font.BOLD, 26);
	}

}