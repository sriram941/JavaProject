import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserRegister extends JFrame implements ActionListener
{

	private JTextField tfFName;
	private JTextField tfLName;
	private JTextField tfAddress;
	private JTextField tfContact;
	private JButton jbRegister;
	private JButton jbBack;
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	private JLabel label;
	String nav,val;

	
	public UserRegister() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 550);
		setTitle("User Registration");
		getContentPane().setLayout(null);
		
		JLabel ur = new JLabel("User Registration");
		ur.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		ur.setBounds(100, 90, 210, 30);
		getContentPane().add(ur);
		
		JLabel fname = new JLabel("First Name:");
		fname.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		fname.setBackground(Color.WHITE);
		fname.setBounds(30, 160, 100, 25);
		getContentPane().add(fname);
		
		JLabel lname = new JLabel("Last Name:");
		lname.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		lname.setBackground(Color.WHITE);
		lname.setBounds(30, 210, 100, 25);
		getContentPane().add(lname);
		
		JLabel address = new JLabel("Address:");
		address.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		address.setBackground(Color.WHITE);
		address.setBounds(30, 260, 100, 25);
		getContentPane().add(address);
		
		JLabel contact = new JLabel("Contact:");
		contact.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		contact.setBackground(Color.WHITE);
		contact.setBounds(30, 310, 100, 25);
		getContentPane().add(contact);
		
		tfFName = new JTextField();
		tfFName.setColumns(10);
		tfFName.setBackground(Color.WHITE);
		tfFName.setBounds(150, 160, 190, 25);
		getContentPane().add(tfFName);
		
		tfLName = new JTextField();
		tfLName.setColumns(10);
		tfLName.setBackground(Color.WHITE);
		tfLName.setBounds(150, 210, 190, 25);
		getContentPane().add(tfLName);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBackground(Color.WHITE);
		tfAddress.setBounds(150, 260, 190, 25);
		getContentPane().add(tfAddress);
		
		tfContact = new JTextField();
		tfContact.setColumns(10);
		tfContact.setBackground(Color.WHITE);
		tfContact.setBounds(150, 310, 190, 25);
		getContentPane().add(tfContact);
		
		jbRegister = new JButton("Register");
		jbRegister.setBounds(130, 465, 100, 25);
		jbRegister.addActionListener(this);
		getContentPane().add(jbRegister);
		
		jbBack = new JButton("< Back");
		jbBack.setBounds(255, 465, 100, 25);
		jbBack.addActionListener(this);
		getContentPane().add(jbBack);
		
		JLabel username = new JLabel("User Name:");
		username.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		username.setBackground(Color.WHITE);
		username.setBounds(30, 360, 100, 25);
		getContentPane().add(username);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBackground(Color.WHITE);
		tfUserName.setBounds(150, 360, 190, 25);
		getContentPane().add(tfUserName);
		
		JLabel pwd = new JLabel("Password:");
		pwd.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		pwd.setBackground(Color.WHITE);
		pwd.setBounds(30, 410, 100, 25);
		getContentPane().add(pwd);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(150, 410, 190, 25);
		getContentPane().add(tfPassword);
		
		label = new JLabel("Online Membership System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(10, 15, 395, 40);
		getContentPane().add(label);
		
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == jbRegister)
		{
			nav= "UserRegistration";
			String pass=String.valueOf(tfPassword.getPassword());
			
			try{
				DataObject myObject = new DataObject();
				Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
				ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
				ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
				
				myObject.setMessage(nav+",,"+tfFName.getText().toString()+",,"+tfLName.getText().toString()+",,"+tfAddress.getText().toString()+",,"+tfContact.getText().toString()+",,"+tfUserName.getText().toString()+",,"+pass);
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
					JOptionPane.showMessageDialog(null,"Registered Successfully");
					dispose();
					MemberHome mh=new MemberHome(tfFName.getText(),tfLName.getText(),tfAddress.getText(),tfContact.getText(),tfUserName.getText());
					mh.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please try a different user name","User Name Taken",JOptionPane.ERROR_MESSAGE);
				}

		}
		
		if(e.getSource() == jbBack)
		{
			dispose();
			Login l=new Login();
			l.frame.setVisible(true);
		}
	}
}
