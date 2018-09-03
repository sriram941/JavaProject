import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class AdminHome extends JFrame implements ActionListener
{
	private JTextField tfSearch;
	private JLabel jlEnterDetails;
	private JTextField jtfFName;
	private JTextField jtfLName;
	private JTextField jtfAddress;
	private JButton jbLogout;
	private JTextField jtfContact;
	private JTextField jtfUName;
	private JPasswordField passwordField;
	private JButton jbAddMember; 
	private JButton jbEdit; 
	private JButton jbDeleteMember; 
	private JButton jbRefresh ;
	private JButton jbUpdateMember;
	private JButton jbSearch; 
	private JButton jbAddAdmin;
	String un,val,nav;
	private JTable table;
	private JScrollPane scrollPane;
	
	

	public AdminHome(String un1)
	{
		un=un1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1200, 650);
		setTitle("Admin Home");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(830, 120, 180, 25);
		getContentPane().add(tfSearch);
		tfSearch.setColumns(10);
		
		jbSearch = new JButton("Search");
		jbSearch.setBounds(1030, 120, 110, 30);
		jbSearch.addActionListener(this);
		getContentPane().add(jbSearch);
		
		jlEnterDetails = new JLabel("Enter Member Details");
		jlEnterDetails.setHorizontalAlignment(SwingConstants.CENTER);
		jlEnterDetails.setBounds(135, 170, 260, 30);
		jlEnterDetails.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		getContentPane().add(jlEnterDetails);
		
		JLabel jlFName = new JLabel("First Name:");
		jlFName.setBounds(30, 235, 110, 30);
		jlFName.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlFName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlFName);
		
		jtfFName = new JTextField();
		jtfFName.setBounds(150, 235, 245, 30);
		getContentPane().add(jtfFName);
		
		JLabel jlLName = new JLabel("Last Name:");
		jlLName.setBounds(30, 285, 110, 30);
		jlLName.setHorizontalAlignment(SwingConstants.CENTER);
		jlLName.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(jlLName);
		
		jtfLName = new JTextField();
		jtfLName.setBounds(150, 285, 245, 30);
		getContentPane().add(jtfLName);
		
		JLabel jlAddress = new JLabel("Address:");
		jlAddress.setBounds(30, 335, 110, 30);
		jlAddress.setHorizontalAlignment(SwingConstants.CENTER);
		jlAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(jlAddress);
		
		jtfAddress = new JTextField();
		jtfAddress.setBounds(150, 335, 245, 30);
		getContentPane().add(jtfAddress);
		
		jbAddMember = new JButton("Add Member");
		jbAddMember.setBounds(30, 550, 110, 30);
		jbAddMember.addActionListener(this);
		getContentPane().add(jbAddMember);
		
		jbDeleteMember = new JButton("Delete Member");
		jbDeleteMember.setBounds(170, 550, 120, 30);
		jbDeleteMember.addActionListener(this);
		getContentPane().add(jbDeleteMember);
		
		jbUpdateMember = new JButton("Update Member");
		jbUpdateMember.setBounds(320, 550, 125, 30);
		jbUpdateMember.addActionListener(this);
		getContentPane().add(jbUpdateMember);
		
		jbLogout = new JButton("Logout");
		jbLogout.setBounds(30, 120, 100, 30);
		jbLogout.addActionListener(this);
		getContentPane().add(jbLogout);
		
		JLabel jlContact = new JLabel("Contact:");
		jlContact.setHorizontalAlignment(SwingConstants.CENTER);
		jlContact.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlContact.setBounds(30, 385, 110, 30);
		getContentPane().add(jlContact);
		
		jtfContact = new JTextField();
		jtfContact.setBounds(150, 385, 245, 30);
		getContentPane().add(jtfContact);
		
		JLabel jlUName = new JLabel("User Name:");
		jlUName.setHorizontalAlignment(SwingConstants.CENTER);
		jlUName.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlUName.setBounds(30, 435, 110, 30);
		getContentPane().add(jlUName);
		
		jtfUName = new JTextField();
		jtfUName.setBounds(150, 435, 245, 30);
		getContentPane().add(jtfUName);
		
		JLabel jlPassword = new JLabel("Password:");
		jlPassword.setHorizontalAlignment(SwingConstants.CENTER);
		jlPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlPassword.setBounds(30, 485, 110, 30);
		getContentPane().add(jlPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 485, 245, 30);
		getContentPane().add(passwordField);
		
		jbEdit = new JButton("Edit Profile");
		jbEdit.setBounds(160, 120, 110, 30);
		jbEdit.addActionListener(this);
		getContentPane().add(jbEdit);
		
		JLabel jlOMS = new JLabel("Online Membership System");
		jlOMS.setHorizontalAlignment(SwingConstants.CENTER);
		jlOMS.setForeground(Color.DARK_GRAY);
		jlOMS.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		jlOMS.setBounds(374, 40, 420, 43);
		getContentPane().add(jlOMS);
		
		jbRefresh = new JButton("Refresh");
		jbRefresh.setBounds(500, 120, 95, 25);
		jbRefresh.addActionListener(this);
		getContentPane().add(jbRefresh);
		
		jbAddAdmin = new JButton("Add Admin");
		jbAddAdmin.setBounds(300, 120, 110, 30);
		jbAddAdmin.addActionListener(this);
		getContentPane().add(jbAddAdmin);
		
		scrollPane = new JScrollPane();
		//scrollPane.setEnabled(false);
		scrollPane.setBounds(500, 170, 640, 400);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Id", "First Name", "Last Name", "Address", "Contact", "User Name"})
		{
			Class[] columnTypes = new Class[] {Integer.class, String.class, String.class, String.class, Long.class, String.class};
			public Class getColumnClass(int columnIndex) {return columnTypes[columnIndex];}
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(175);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.setEnabled(false);
	
}
public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == jbLogout)
		{
			dispose();
			Login l=new Login();
			l.frame.setVisible(true);
		}
		
		if(e.getSource() == jbAddAdmin)
		{
			dispose();
			AddAdmin aa=new AddAdmin(un);
			aa.setVisible(true);
		}
		
		//************************
		if(e.getSource() == jbAddMember)
		{
			nav= "AdminAddMember";
			String pass=String.valueOf(passwordField.getPassword());
			 try{
				DataObject myObject = new DataObject();
				Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
				ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
				ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
				
				myObject.setMessage(nav+",,"+jtfFName.getText().toString()+",,"+jtfLName.getText().toString()+",,"+jtfAddress.getText().toString()+",,"+jtfContact.getText().toString()+",,"+jtfUName.getText().toString()+",,"+pass);
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
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please try a different user name","User Name Taken",JOptionPane.ERROR_MESSAGE);
				}
		}
//			   //************************
		if(e.getSource() == jbDeleteMember)
		{
			nav= "AdminDeleteMember";
			try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
					
					myObject.setMessage(nav+",,"+jtfUName.getText().toString());
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
						JOptionPane.showMessageDialog(null,"Deleted Successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"User Name doesnot exist in database","User Not Found",JOptionPane.ERROR_MESSAGE);
					}
		}
		
		//************************		
		if(e.getSource() == jbUpdateMember)
		{
			nav= "AdminUpdateMember";
			String pass=String.valueOf(passwordField.getPassword());
			   try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
					
myObject.setMessage(nav+",,"+jtfFName.getText().toString()+",,"+jtfLName.getText().toString()+",,"+jtfAddress.getText().toString()+",,"+jtfContact.getText().toString()+",,"+jtfUName.getText().toString()+",,"+pass);
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
						JOptionPane.showMessageDialog(null,"Updated Successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"User Name doesnot exist in database","User Not Found",JOptionPane.ERROR_MESSAGE);
					}
		}
		
		//************************		
		if(e.getSource() == jbSearch)
		{
			nav= "AdminSearch";
			if(tfSearch.getText().toString().equals(""))
			{
				tfSearch.setText(" ");
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.fireTableDataChanged();
			
			try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
					
					int rows;
	myObject.setMessage(nav+",,"+tfSearch.getText().toString());
					myOutputStream.writeObject(myObject);
					
					myObject = (DataObject)myInputStream.readObject();
					rows= Integer.parseInt(myObject.getMessage());
					//System.out.println("# of rows:"+rows);
					
					myObject.setMessage("OK");
					myOutputStream.writeObject(myObject);

					int i=1;
					while(i<=rows)
					{
						String [] r;
						myObject = (DataObject)myInputStream.readObject();
						System.out.println("Row : " + myObject.getMessage());
						r=myObject.getMessage().split("<td>");
				Object[] row = { r[0], r[1], r[2], r[3], r[4], r[5]};
					    model.addRow(row);
	myObject.setMessage("OK");
						System.out.println("Message sent : " + myObject.getMessage());
						myOutputStream.writeObject(myObject);
		
						i++;
					}
				
					myOutputStream.close();
					myInputStream.close();
					socketToServer.close();
					
					
				}
				catch(Exception ee){
					System.out.println(ee);
		        		}
				
		}
		
		
		if(e.getSource() == jbEdit)
		{
			dispose();
			AdminEdit ae=new AdminEdit(un);
			ae.setVisible(true);
		}
		if(e.getSource() == jbRefresh)
		{
			dispose();
			AdminHome ah=new AdminHome(un);
	        ah.setVisible(true);
				
		}
		}
}