package stream.report.ygy;


import java.util.*;
import java.io.*;


public class JarTest {

	public static void main() throws IOException
	{
		InputStream is = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;

		DataStreamLib d = new DataStreamLib();
		
		ArrayList<Object> sList = new ArrayList<Object>();
		
		try {

			// create file output stream
			fos = new FileOutputStream("c:\\dev\\°úÁ¦\\1701173.txt");


			// create data output stream
			dos = new DataOutputStream(fos);
			d.printArray(sList, dos);
		} catch(Exception e) {

			// if any error occurs
			e.printStackTrace();
		} finally {

			// releases all system resources from the streams
			if(is!=null)
				is.close();
			if(dis!=null)
				dis.close();
			if(fos!=null)
				fos.close();
			if(dos!=null)
				dos.close();
		}
	}
	
	
}