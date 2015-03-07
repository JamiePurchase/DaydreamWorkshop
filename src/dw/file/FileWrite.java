package dw.file;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileWrite
{
	private String writePath;
	private boolean writeAppend = false;
	
	public FileWrite(String newPath, boolean newAppend)
	{
		writePath = newPath;
		writeAppend = newAppend;
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
		for(int line=0;line<textLines.length;line+=1)
		{
			System.out.println(textLines[line]);
			try{FileWriteLine(textLines[line]);}
			catch (IOException e){e.printStackTrace();}
		}
	}

}