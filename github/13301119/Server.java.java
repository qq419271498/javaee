import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;


public class Server extends JFrame {
private JTextArea jta =new JTextArea();
ReverseWords reverse=new ReverseWords();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}
	
	
	
	public Server(){
		String input,output;
		
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		setTitle("Server");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			ServerSocket serverSorcket =new ServerSocket(3333);
			jta.append("Server start at "+ new Date()+ '\n');
			Socket socket=serverSorcket.accept();
			DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
			
			while(true){
				
				String Rstr= inputFromClient.readUTF();
			
				
				//获得客户端的字符串,丢给Reverser进行逆序
			
				Reverser theReverser = new Reverser(Rstr);
				output = theReverser.doRev();
				
				
				
				outputToClient.writeBytes(output);
				jta.append("receved from client: "+output+'\n');
				jta.append("character Reserved: "+output+'\n');
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	class StackX{
		private int maxSize;
		private char[] stackArray;
		private int top;
		
		public StackX(int n){
			maxSize = n;
			stackArray = new char[maxSize];
			top = -1;
		}
		
		public void push(char c){
			stackArray[++top] = c; 
		}
		
		public char pop(){
			return stackArray[top--];
		}
		
		public boolean isEmpty(){
			return (top == -1);
		}
		
		public boolean isFull(){
			return (top == maxSize-1);
		}	
	}

	class Reverser{
		private String input;
		private String output;
		
		public Reverser(String in){
			input = in;
		}
		
		public String doRev(){
			int stackSize = input.length();
			StackX  stack1 = new StackX(stackSize);
			
			for(int j=0; j<stackSize; j++){
				char ch = input.charAt(j);
				stack1.push(ch);
			}
			
			output = "";
			while(!stack1.isEmpty()){
				char ch = stack1.pop();
				output += ch; 
			}
			
			return output;
	    }
	}
	

	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

}
