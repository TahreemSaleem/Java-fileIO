package lab_tasks;

/* I "Tahreem Saleem" do verify that the submitted code is my own effort and that I have not copied it 
from any peer or any internet source that has not been acknowledged. I also understand that if my 
submission fails the similarity detection, I would be awarded zero marks not only for this submission 
but the whole evaluation component. */


/*this program askes the user to enter the path of a text file he/she wants to read
 * then it gives them the option of reading it through io, nio, and nio2 channel methods
*/
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.*;
//public class
	public class ts extends JFrame implements ActionListener
	{	
		
		//creating the main layout
		static  JTextArea ta = new JTextArea ();
		static JButton io = new JButton("io");
		static	JButton nio = new JButton ("nio");
		static	JButton b = new JButton ("enter");
		static JButton nio2 = new JButton("nio2");
		static JTextField tf = new JTextField("Time consumed in milliseconds:",50);
		static JTextField tff = new JTextField("",40);
		static JLabel jl = new JLabel ("ENTER THE PATH BELOW");
		static String path;
		
		//main method
		public static void main ( String args[]) throws IOException
		{
			//there are four panels 
			ts mf = new ts();
			JPanel buttons = new JPanel ();			//for nio,nio2 and io buttons
			JPanel area = new JPanel (new BorderLayout());		//to display the text
			JPanel text = new JPanel();					//time in textfield
			JPanel enter = new JPanel();				//enter path in text field
			buttons.add(io);					
			buttons.add(nio);
			buttons.add(nio2);
			JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			enter.add(tff);
			enter.add(b);
			area.add(sp,BorderLayout.CENTER);
			area.add(enter,BorderLayout.NORTH);
			area.add(buttons,BorderLayout.SOUTH);
			text.add(tf);
			io.addActionListener(mf);				//adding action event on buttons
			nio.addActionListener(mf);
			nio2.addActionListener(mf);
			b.addActionListener(mf);
			mf.setLayout(new BorderLayout());
			mf.add (area,BorderLayout.CENTER);
			mf.add(jl,BorderLayout.NORTH);
			mf.add(text,BorderLayout.SOUTH);
			mf.setTitle (" File ");
			mf.setBounds(200,100,600,600);
			mf.setVisible(true);
		
		}
		//recognizes the button which is clicked and calls different methods accordingly
		public void actionPerformed(ActionEvent ae)
			{
			if(ae.getSource().equals(b))			
				path =tff.getText();				//getting the entered path
			if(ae.getSource().equals(io))
			{
				try {
					read1();						//calling the first method that reads by io 
				}catch(Exception e){}
			}
			if(ae.getSource().equals(nio))
			{
				try {
					read2();						//calling the second method that reads by nio 
				}catch(Exception e){}
			}
			if(ae.getSource().equals(nio2))
			{
				try {
					read3();					//calling the THIRD method that reads by CHANNEL 
				}catch(Exception e){}
			}
		}//main method ends
			
			//FIRST METHOD THAT READS THE FILE USING IO
			public  void read1() throws IOException
			{
				long t1 = System.currentTimeMillis();
				ta.setText("");
				File fg = new File(path); 
				FileInputStream fis;
				if (fg.exists())
					{
					try
						{	
							fis = new FileInputStream(fg);												
							byte [] b = new byte[(int)fg.length()];
							fis.read(b);													//READING THE FILE
							for (int i= 0;i < b.length;i++)
								{
									ta.append(""+(char)b[i]);			//DISPLAYING IN THE TEXT AREA
								}//for ends
						}catch(Exception e){}									
					
				long t2 = System.currentTimeMillis();
				long sub = t2-t1;
				tf.setText(""+sub);
					}
				else 
					ta.setText("No FILE FOUND");
			}//method ends
			
			//SECOND METHOD THAT READS THE FILE USING NIO
			public void read2() throws IOException 
			{
					long t1 = System.currentTimeMillis();
					ta.setText("");
					Path file = Paths.get(path);		///sets the path
					if(Files.exists(file))
					{
						byte[] fileArray = Files.readAllBytes(file);		//file array that reads all bites
						for (int i= 0;i < Files.size(file);i++)
							{
								ta.append(""+(char)fileArray[i]);
							}
						long t2 = System.currentTimeMillis();
						long sub = t2-t1;
						tf.setText(""+sub);
					}
					else ta.setText("No FILE FOUND");
			}
			
	public void read3() throws IOException
	{	
			long t1 = System.currentTimeMillis();
			ta.setText("");
			Path file = Paths.get(path);
			if(Files.exists(file))

			{
				ByteBuffer buf = ByteBuffer.allocate(42);
				ReadableByteChannel inCh = Files.newByteChannel(file);
			
				while(inCh.read(buf) != -1) 		//read the buff until end of file
				{
					buf.flip();
					while(buf.hasRemaining())			//if remaining elements are present create byte array and display in text field 
					{
					  byte b = buf.get();			
					  ta.append(""+(char)b);
					}//while ends
					buf.clear(); // Clear the buffer for the next read
				}//while ends
				inCh.close();
				long t2 = System.currentTimeMillis();
				long sub = t2-t1;
				tf.setText(""+sub);
			}//if ends
	
			else ta.setText("No FILE FOUND");
	}//method ends
}
//public class ends


