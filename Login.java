import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
public JFrame frame;
	JTextField tfuName;
	JPasswordField pwdfield;
	JButton jbLogin;
	JButton jbRegister;
	JRadioButton rbAdmin;
	JRadioButton rbUser;
	//Connection conn=null;
	String val,nav,fn,ln,ad,cn,un;

	public static void main(String[] args)
	{
		Login wlogin = new Login();
		wlogin.frame.setVisible(true);
	}

    public Login()
    {
	   frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Online Membership System");
		frame.setBounds(200, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel oms = new JLabel("Online Membership System");
		oms.setHorizontalAlignment(SwingConstants.CENTER);
		oms.setForeground(Color.DARK_GRAY);
		oms.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		oms.setBounds(80, 15, 420, 43);
		frame.getContentPane().add(oms);
		
		JLabel ls = new JLabel("Login As");
		ls.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 18));
		ls.setBounds(220, 70, 145, 30);
		frame.getContentPane().add(ls);
		
		rbAdmin = new JRadioButton("ADMIN");
		rbAdmin.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		rbAdmin.setBackground(Color.WHITE);
		rbAdmin.setBounds(125, 105, 130, 25);
		frame.getContentPane().add(rbAdmin);
		
		rbUser = new JRadioButton("USER");
		rbUser.setBackground(Color.WHITE);
		rbUser.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		rbUser.setBounds(300, 105, 130, 25);
		frame.getContentPane().add(rbUser);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rbAdmin);
		group.add(rbUser);
		
		JLabel uName = new JLabel("Username:");
		uName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		uName.setBackground(Color.WHITE);
		uName.setBounds(30, 165, 85, 25);
		frame.getContentPane().add(uName);
		
		tfuName = new JTextField();
		tfuName.setBackground(Color.WHITE);
		tfuName.setBounds(135, 165, 190, 25);
		frame.getContentPane().add(tfuName);
		tfuName.setColumns(10);
		
		JLabel pwd = new JLabel("Password:");
		pwd.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		pwd.setBackground(Color.WHITE);
		pwd.setBounds(30, 220, 85, 25);
		frame.getContentPane().add(pwd);
		
		pwdfield = new JPasswordField();
		pwdfield.setBounds(135, 220, 190, 25);
		frame.getContentPane().add(pwdfield);
		
		jbLogin = new JButton("Login");
		jbLogin.setBounds(125, 260, 100, 25);
		jbLogin.addActionListener(this);
		frame.getContentPane().add(jbLogin);
		
		jbRegister = new JButton("Register");
		jbRegister.setBounds(255, 260, 100, 25);
		jbRegister.addActionListener(this);
		frame.getContentPane().add(jbRegister);
   }


   public void actionPerformed(ActionEvent e) 	
   {
	   if(e.getSource() == jbRegister)
	   {
		   if(rbUser.isSelected())
		    {
			   frame.dispose();
			   UserRegister ur=new UserRegister();
			   ur.setVisible(true);
		    }
	   }
	   
	   if(e.getSource() == jbLogin)
	   {
		
	    if(rbAdmin.isSelected())
	    {
	    	nav="AdminLogin";
			String pass=String.valueOf(pwdfield.getPassword());
	    	try{
				DataObject myObject = new DataObject();
				Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
				ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
				ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
				
				myObject.setMessage(nav+",,"+tfuName.getText().toString()+",,"+pass);
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
					frame.dispose();
			        AdminHome ah=new AdminHome(tfuName.getText());
			        ah.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please check the login details again","Wrong Combination",JOptionPane.ERROR_MESSAGE);
				}
		
	    }
	    
	    if(rbUser.isSelected())
	    {
	    	nav="UserLogin";
			String pass=String.valueOf(pwdfield.getPassword());
	    	try{
				DataObject myObject = new DataObject();
				Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
				ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
				ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
				
//				myObject.setMessage(nav+",,"+tfuName.getText().toString()+",,"+pass);
				myObject.setMessage(nav);
				myOutputStream.writeObject(myObject);
				

				myObject = (DataObject)myInputStream.readObject();

				myObject.setMessage(tfuName.getText().toString());
				myOutputStream.writeObject(myObject);
				
				myObject = (DataObject)myInputStream.readObject();
				
				myObject.setMessage(pass);
				myOutputStream.writeObject(myObject);
				
				String [] r;
				myObject = (DataObject)myInputStream.readObject();
				r=myObject.getMessage().split("<td>");
				fn=r[1];
				ln=r[2];
				ad=r[3];
				cn=r[4];
				un=r[5];
				
				myObject.setMessage("OK");
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
					frame.dispose();
			        MemberHome mh = new MemberHome(fn,ln,ad,cn,un);
			        mh.setVisible(true);
				}
				else if(val.equals("false"))
				{
					JOptionPane.showMessageDialog(null,"Please check the login details again","Wrong Combination",JOptionPane.ERROR_MESSAGE);
				}
	
}
   }	   
  }
}
