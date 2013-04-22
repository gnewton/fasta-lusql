
package ca.gnewton.fastalusql.fasta;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


public class FastaIterator implements Iterator<FastaEntry>
{
	public static final char COMMENT=';';
	public static final char SEQUENCE_START='>';
	
	private BufferedReader br = null;

	public FastaIterator(){
		
	}
	
	public void init(String fastaFileName) throws IOException
	{
		File f = new File(fastaFileName);
		if(! f.exists()){
			throw new FileNotFoundException("File: " + fastaFileName + " does not exist");
		}
		if(! f.canRead()){
			throw new FileNotFoundException("File: " + fastaFileName + " does not exist");
		}
		try{
			br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(fastaFileName))));
		}
		catch(Exception e){
			// log 
			throw e;
		}
		FastaFileInfo ffi = new FastaFileInfo();
		ffi.filepath = f.getCanonicalPath();
		

		FastaEntry fe = new FastaEntry();
		fe.fileInfo = ffi;
		
		try{
			String firstLine = br.readLine();
			if(firstLine.charAt(0) == COMMENT){
				ffi.globalFileHeader = firstLine.substring(1);
			}
			else{
				if(firstLine.charAt(0) != SEQUENCE_START){
					throw new IOException("Not FASTA format: first line must start with either " 
					                  + COMMENT
					                  + " or "
					                  + SEQUENCE_START);
				}
				
				fe.description = firstLine.substring(1);
			}
			
		}
		catch(IOException e){
			// log
			throw e;
		}
	}


	public boolean hasNext()
	{
		return false;
	}
	
	public FastaEntry next()
	{
		return null;
	}
	
	public void	remove()
	{
		// NOOP
	}
	
	


}



