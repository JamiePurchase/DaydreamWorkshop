package dw.graphics;

import java.awt.Font;

public class GraphicsFont
{

	public static Font getFont(String style)
	{
		if(style=="ModuleFrameTitle"){return new Font("Segoe Print", Font.PLAIN, 22);}
		if(style=="renameCode"){return new Font("courier", Font.PLAIN, 26);}
		return new Font("MV Boli", Font.BOLD, 26);
	}

}