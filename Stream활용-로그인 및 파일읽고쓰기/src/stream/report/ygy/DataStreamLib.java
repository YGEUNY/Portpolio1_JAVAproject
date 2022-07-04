package stream.report.ygy;

import java.util.*;
import java.io.*;

public class DataStreamLib {

	public void printArray(ArrayList<Object> sList,DataOutputStream dos) 
			throws IOException
	{
		// for each  string in string buffer
		for(Object o: sList.toArray() ) {

				if ( o instanceof Integer )
				{
					dos.writeInt( (Integer)o );
				}
				else if ( o instanceof String )
				{
					// write string encoded as modified UTF-8
					dos.writeUTF((String)o);
				}
				else if ( o instanceof Double )
				{
					dos.writeDouble( (Double)o );
				}

		}
	}
	
	public static void main(String[] args) throws IOException {
		InputStream is = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		ArrayList<Object> sList = new ArrayList<Object>();
		
		sList.add( new Integer(3) );
		sList.add( "Hello World");
		sList.add( new Double(10.3));
		
		DataStreamLib demo = new DataStreamLib();
		
		try {

			// create file output stream
			fos = new FileOutputStream("c:\\dev\\과제\\test.txt");

			// create data output stream
			dos = new DataOutputStream(fos);

			demo.printArray( sList, dos );
			demo.printArray( sList, dos );
	
			// force data to the underlying file output stream
			dos.flush();

			// create file input stream
			is = new FileInputStream("c:\\dev\\과제\\test.txt");

			// create new data input stream
			dis = new DataInputStream(is);

			// available stream to be read
			while(dis.available()>0) {
				
				Integer i = dis.readInt();
				// reads characters encoded with modified UTF-8
				String k = dis.readUTF();

				Double d = dis.readDouble();
				// print
				System.out.print(i + " " + k+" " + d + "\n");
			}

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





