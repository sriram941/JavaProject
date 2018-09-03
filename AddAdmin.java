import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddAdmin extends JFrame implements ActionListener 
{
	String un,pass,val,nav;
	private JTextField jtfFullName;
	private JTextField jtfUserName;
	private JPasswordField jpfPass;
	private JButton jbAddAdmin; 
	private JButton jbBack; 

	public AddAdmin(String un1)
	{
		un=un1;
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 500, 380);
		setTitle("Add Admin");
		getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Online Membership System");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		title.setBounds(45, 15, 380, 40);
		getContentPane().add(title);
		
		JLabel title2 = new JLabel("Enter Admin Details");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		title2.setBounds(100, 70, 260, 30);
		getContentPane().add(title2);
		
		JLabel jlFullName = new JLabel("Full Name:");
		jlFullName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		jlFullName.setBackground(Color.WHITE);
		jlFullName.setBounds(15, 140, 100, 25);
		getContentPane().add(jlFullName);
		
		jtfFullName = new JTextField();
		jtfFullName.setColumns(10);
		jtfFullName.setBackground(Color.WHITE);
		jtfFullName.setBounds(125, 140, 190, 25);
		getContentPane().add(jtfFullName);
		
		JLabel jlUserName = new JLabel("User Name:");
		jlUserName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		jlUserName.setBackground(Color.WHITE);
		jlUserName.setBounds(15, 190, 100, 25);
		getContentPane().add(jlUserName);
		
		jtfUserName = new JTextField();
		jtfUserName.setColumns(10);
		jtfUserName.setBackground(Color.WHITE);
		jtfUserName.setBounds(125, 190, 190, 25);
		getContentPane().add(jtfUserName);
		
		JLabel jlpass = new JLabel("Password:");
		jlpass.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		jlpass.setBackground(Color.WHITE);
		jlpass.setBounds(15, 240, 100, 25);
		getContentPane().add(jlpass);
		
		jpfPass = new JPasswordField();
		jpfPass.setBounds(125, 240, 190, 25);
		getContentPane().add(jpfPass);
		
		jbAddAdmin = new JButton("Add Admin");
		jbAddAdmin.setBounds(125, 295, 95, 25);
		jbAddAdmin.addActionListener(this);
		getContentPane().add(jbAddAdmin);
		
		jbBack = new JButton("< Back");
		jbBack.setBounds(250, 295, 95, 25);
		jbBack.addActionListener(this);
		getContentPane().add(jbBack);
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jbBack)
		{
			dispose();
			AdminHome ah=new AdminHome(un);
			ah.setVisible(true);
		}
		
		//************************
				if(e.getSource() == jbAddAdmin)
				{
					nav= "AdminAddAdmin";
					String pass=String.valueOf(jpfPass.getPassword());
					 try{
						DataObject myObject = new DataObject();
						Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
						ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
						ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
						
						myObject.setMessage(nav+",,"+jtfFullName.getText().toString()+",,"+jtfUserName.getText().toString()+",,"+pass);
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
							JOptionPane.showMessageDialog(null,"Admin Added Successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Please try a different user name","User Name Taken",JOptionPane.ERROR_MESSAGE);
						}
				}
	}
}
