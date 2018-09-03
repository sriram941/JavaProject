import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MemberHome extends JFrame implements ActionListener
{	
	String fn,ln,ad,cn,un,nav,val;
	private JTextField jtfFN;
	private JTextField jtfLN;
	private JTextField jtfAddress;
	private JTextField jtfContact;
	private JTextField jtfUN;
	private JPasswordField jPwd;
	private JTextField jtfSearch;
	private JButton jbLogout; 
	private JButton jbEdit; 
	private JLabel jlEdit;
	private JLabel jlDetails;
	private JButton jbUpdate;
	private JButton jbSearch; 
	private JTable table;
	private JScrollPane scrollPane;
	private JButton jbRefresh;
	
	public MemberHome(String fn1,String ln1,String ad1,String cn1,String un1)
	{
		fn=fn1;
		ln=ln1;
		ad=ad1;
		cn=cn1;
		un=un1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1200, 650);
		setTitle("Member Home");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel jloms = new JLabel("Online Membership System");
		jloms.setHorizontalAlignment(SwingConstants.CENTER);
		jloms.setForeground(Color.DARK_GRAY);
		jloms.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 20));
		jloms.setBounds(375, 40, 420, 40);
		getContentPane().add(jloms);
		
		jbLogout = new JButton("Logout");
		jbLogout.setBounds(30, 120, 100, 30);
		jbLogout.addActionListener(this);
		getContentPane().add(jbLogout);
		
		jlEdit = new JLabel("Your Personal Details");
		jlEdit.setHorizontalAlignment(SwingConstants.CENTER);
		jlEdit.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		jlEdit.setBounds(135, 170, 260, 30);
		getContentPane().add(jlEdit);
		
		jlDetails = new JLabel("Edit Your Details");
		jlDetails.setHorizontalAlignment(SwingConstants.CENTER);
		jlDetails.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		jlDetails.setBounds(135, 170, 260, 30);
		getContentPane().add(jlDetails);
		jlDetails.setVisible(false);
		
		JLabel jlFN = new JLabel("First Name:");
		jlFN.setHorizontalAlignment(SwingConstants.CENTER);
		jlFN.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlFN.setBounds(30, 235, 110, 30);
		getContentPane().add(jlFN);
		
		JLabel jlLN = new JLabel("Last Name:");
		jlLN.setHorizontalAlignment(SwingConstants.CENTER);
		jlLN.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlLN.setBounds(30, 285, 110, 30);
		getContentPane().add(jlLN);
		
		JLabel jlAddress = new JLabel("Address:");
		jlAddress.setHorizontalAlignment(SwingConstants.CENTER);
		jlAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlAddress.setBounds(30, 335, 110, 30);
		getContentPane().add(jlAddress);
		
		JLabel jlCont = new JLabel("Contact:");
		jlCont.setHorizontalAlignment(SwingConstants.CENTER);
		jlCont.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlCont.setBounds(30, 385, 110, 30);
		getContentPane().add(jlCont);
		
		JLabel jlUN = new JLabel("User Name:");
		jlUN.setHorizontalAlignment(SwingConstants.CENTER);
		jlUN.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlUN.setBounds(30, 435, 110, 30);
		getContentPane().add(jlUN);
		
		JLabel jlPwd = new JLabel("Password:");
		jlPwd.setHorizontalAlignment(SwingConstants.CENTER);
		jlPwd.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlPwd.setBounds(30, 485, 110, 30);
		getContentPane().add(jlPwd);
		
		jtfFN = new JTextField(fn);
		jtfFN.setBounds(150, 235, 245, 30);
		jtfFN.setEditable(false);
		getContentPane().add(jtfFN);
		
		jtfLN = new JTextField(ln);
		jtfLN.setBounds(150, 285, 245, 30);
		jtfLN.setEditable(false);
		getContentPane().add(jtfLN);
		
		jtfAddress = new JTextField(ad);
		jtfAddress.setBounds(150, 335, 245, 30);
		jtfAddress.setEditable(false);
		getContentPane().add(jtfAddress);
		
		jtfContact = new JTextField(cn);
		jtfContact.setBounds(150, 385, 245, 30);
		jtfContact.setEditable(false);
		getContentPane().add(jtfContact);
		
		jtfUN = new JTextField(un);
		jtfUN.setEditable(false);
		jtfUN.setBounds(150, 435, 245, 30);
		getContentPane().add(jtfUN);
		
		jPwd = new JPasswordField();
		jPwd.setBounds(150, 485, 245, 30);
		getContentPane().add(jPwd);
		jPwd.setVisible(false);
		
		jbEdit = new JButton("Edit Profile");
		jbEdit.setBounds(30, 550, 110, 30);
		jbEdit.addActionListener(this);
		getContentPane().add(jbEdit);
		
		jtfSearch = new JTextField();
		jtfSearch.setColumns(10);
		jtfSearch.setBounds(830, 120, 180, 25);
		getContentPane().add(jtfSearch);
		
		jbSearch = new JButton("Search");
		jbSearch.setBounds(1030, 120, 110, 30);
		jbSearch.addActionListener(this);
		getContentPane().add(jbSearch);
		
		jbRefresh = new JButton("Refresh");
		jbRefresh.setBounds(500, 120, 95, 25);
		jbRefresh.addActionListener(this);
		getContentPane().add(jbRefresh);
		
		jbUpdate = new JButton("Update");
		jbUpdate.setBounds(170, 550, 100, 30);
		jbUpdate.addActionListener(this);
		getContentPane().add(jbUpdate);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 170, 640, 400);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Id", "First Name", "Last Name", "Address", "Contact", "User Name"})
		{
			Class[] columnTypes = new Class[] {Integer.class, String.class, String.class, String.class, Long.class, String.class};
			public Class getColumnClass(int columnIndex) {return columnTypes[columnIndex];}
			
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(117);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.setEnabled(false);
		
		
	}

	
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource()==jbLogout)
		{
			dispose();
			Login l=new Login();
			l.frame.setVisible(true);
		}
		
		if(a.getSource()==jbEdit)
		{
			jPwd.setVisible(true);
			
			jtfFN.setEditable(true);
			jtfLN.setEditable(true);
			jtfAddress.setEditable(true);
			jtfContact.setEditable(true);
			
			jlDetails.setVisible(true);
			jlEdit.setVisible(false);
			
		}
		
		if(a.getSource() == jbSearch)
		{
			nav= "AdminSearch";
			if(jtfSearch.getText().toString().equals(""))
			{
				jtfSearch.setText(" ");
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.fireTableDataChanged();
			try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
					
					int rows;
					
					myObject.setMessage(nav+",,"+jtfSearch.getText().toString());
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
		
			if(a.getSource() == jbRefresh)
			{
				dispose();
				MemberHome mh=new MemberHome(fn,ln,ad,cn,un);
		        mh.setVisible(true);
					
			}
		if(a.getSource()==jbUpdate)
		{
			nav= "UserUpdate";
			String pass=String.valueOf(jPwd.getPassword());
			   try{
					DataObject myObject = new DataObject();
					Socket socketToServer = new Socket("afsaccess1.njit.edu", 1234);
					ObjectOutputStream myOutputStream =new ObjectOutputStream(socketToServer.getOutputStream());
					ObjectInputStream myInputStream =new ObjectInputStream(socketToServer.getInputStream());
	
myObject.setMessage(nav+",,"+jtfFN.getText().toString()+",,"+jtfLN.getText().toString()+",,"+jtfAddress.getText().toString()+",,"+jtfContact.getText().toString()+",,"+jtfUN.getText().toString()+",,"+pass);
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
}
	}
