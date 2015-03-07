package dw.file;

import java.io.File;

public class FileTest
{
	public FileTest()
	{
		final File folder = new File("C:/Eclipse/Workspace/Daydream");
		listFilesForFolder(folder);
	}
	
	public static void listFilesForFolder(final File folder)
	{
	    for(final File fileEntry : folder.listFiles())
	    {
	        if(fileEntry.isDirectory())
	        {
	            listFilesForFolder(fileEntry);
	        }
	        else
	        {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}

}