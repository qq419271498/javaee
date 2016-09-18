import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Client extends JFrame {
     private JTextField jtf =new JTextField();
     private JTextArea jta =new JTextArea();

     private DataOutputStream toServer;
     private DataInputStream fromServer;
     
     
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	new Client();
		
	}
	
	
	public Client(){
		
		
		JPanel p =new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("enter string"),BorderLayout.WEST);
		p.add(jtf,BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		setLayout(new BorderLayout());
		add(p,BorderLayout.NORTH);
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		jtf.addActionListener(new TextFieldListener());
		
		setTitle("Client");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			

			String Rstr = null;
			String input,output;
			
			Socket socket=new Socket("localhost",3333);
			fromServer=new DataInputStream(socket.getInputStream());
			
			toServer=new DataOutputStream(socket.getOutputStream());
			
		
			
			while(true){
				System.out.print("Enter a string: ");
				System.out.flush();
				input = getString();
				if(input.equalsIgnoreCase("q"))
					break;
			}
			
			
			toServer.writeChars(Rstr);
			fromServer.readLine();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
	}
	
	
	

private class TextFieldListener implements ActionListener {
	String str=new String();
	String Rstr=new String();
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	
		try {
			toServer.writeChars(str);
			toServer.flush();
			
			jta.append("String is" +str+"\n");
			
			jta.append("Reservered String is" +Rstr+"\n");
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

		
		
		
	}

	
}


public static String getString() throws IOException{
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader(isr);
String s = br.readLine();
return s;
}




}
