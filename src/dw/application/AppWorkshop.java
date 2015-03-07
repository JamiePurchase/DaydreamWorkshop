package dw.application;
import dw.Editor;

public class AppWorkshop
{

	public static void main(String args[])
	{
		// Default Values
		String module = "App";
		int width = 1366;
		int height = 768;
		
		// Arguments
		if(args.length>=1){module = args[0];}
		if(args.length>=3)
		{
			width = Integer.parseInt(args[1]);
			height = Integer.parseInt(args[2]);
		}
			
		// Launch
		new Editor(module, width, height).start();
	}

}