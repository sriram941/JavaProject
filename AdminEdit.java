import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminEdit extends JFrame implements ActionListener
{
	private JButton jbBack;
	private JTextField jtfUName;
	private JPasswordField jtfPass;
	private JButton jbUpdate;
	String un,val,nav;
	
	
	
	public AdminEdit(String un1) 
	{
		un=un1;
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 380);
		setTitle("Edit Profile");
		getContentPane().setLayout(null);
		
		jbBack = new JButton("< Back");
		jbBack.setBounds(220, 260, 95, 25);
		jbBack.addActionListener(this);
		getContentPane().add(jbBack);
		
		jbUpdate = new JButton("Update");
		jbUpdate.setBounds(90, 260, 95, 25);
		jbUpdate.addActionListener(this);
		getContentPane().add(jbUpdate);
		
		JLabel label = new JLabel("Online Membership System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(10, 30, 420, 40);
		getContentPane().add(label);
		
		JLabel jlUName = new JLabel("User Name:");
		jlUName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		jlUName.setBackground(Color.WHITE);
		jlUName.setBounds(35, 130, 100, 25);
		getContentPane().add(jlUName);
		
		jtfUName = new JTextField(un);
		jtfUName.setEditable(false);
		jtfUName.setBounds(150, 130, 190, 25);
		getContentPane().add(jtfUName);
		
		JLabel jlPassword = new JLabel("Password:");
		jlPassword.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		jlPassword.setBackground(Color.WHITE);
		jlPassword.setBounds(35, 200, 100, 25);
		getContentPane().add(jlPassword);
		
		jtfPass = new JPasswordField();
		jtfPass.setBounds(150, 200, 190, 25);
		getContentPane().add(jtfPass);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jbBack)
		{
			dispose();
			AdminHome ah=new AdminHome(un);
			ah.setVisible(true);
		}
		
		if(e.getSource()==jbUpdate)
		{
			nav= "AdminEdit";
			String pass=String.valueOf(jtfPass.getPassword());
			   try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
					
					
					myObject.setMessage(nav+",,"+un+",,"+pass);
					myOutputStream.writeObject(myObject);
					
					myObject = (DataObject)myInputStream.readObject();

					System.out.println("Messaged received : " + myObject.getMessage());
					val=myObject.getMessage().toString();
				
					myOutputStream.close();
					myInputStream.close();
					socketToServer.close();
					
					
				}
				catch(Exception ee){
					System.out.println(ee);
		        		}
				
				if (val.equals("true"))
					{
					JOptionPane.showMessageDialog(null,"Password Changed Successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"password could not be changed","Password Not Changed",JOptionPane.ERROR_MESSAGE);
					}
		}
}
}
