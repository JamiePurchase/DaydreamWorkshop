package dw.file;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileWrite
{
	private String writePath;
	private boolean writeAppend = false;
	private String writeBreak;
	
	public FileWrite(String newPath, boolean newAppend)
	{
		writePath = "C:/Eclipse/Workspace/Daydream/data/" + newPath;
		writeAppend = newAppend;
		writeBreak = System.getProperty("line.separator");
	}
	
	public void FileWriteLine(String textLine) throws IOException
	{
		FileWriter write = new FileWriter(writePath, writeAppend);
		PrintWriter print_line = new PrintWriter(write);
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
	}
	
	public void FileWriteArray(String[] textLines)
	{
		System.out.println("FWA");
		if(writeAppend==true){FileWriteArrayAppend(textLines);}
		else{FileWriteArrayOverwrite(textLines);}
	}
	
	public void FileWriteArrayAppend(String[] textLines)
	{
		System.out.println("FWAA");
		for(int line=0;line<textLines.length;line+=1)
		{
			try{FileWriteLine(textLines[line]);}
			catch (IOException e){e.printStackTrace();}
		}
	}
	
	public void FileWriteArrayOverwrite(String[] textLines)
	{
		System.out.println("FWAO");
		String textData = "";
		for(int line=0;line<textLines.length;line+=1)
		{
			textData = textData + textLines[line];
			if(line<textLines.length-1){textData = textData + writeBreak;}
		}
		try{FileWriteLine(textData);}
		catch (IOException e){e.printStackTrace();}
	}

}