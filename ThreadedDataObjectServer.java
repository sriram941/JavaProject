import java.io.*;
import java.net.*;
import java.sql.*;
 
public class ThreadedDataObjectServer
{  public static void main(String[] args )
   { 
             
      try
      {  ServerSocket s = new ServerSocket(9016);
        
         for (;;)
         {  Socket incoming = s.accept( );
            new ThreadedDataObjectHandler(incoming).start();
            
                            }  
      }
      catch (Exception e)
      {  System.out.println(e);
      }
   }
}
 
class ThreadedDataObjectHandler extends Thread
{ 
public ThreadedDataObjectHandler(Socket i)
   {
                           incoming = i;
   }
  
   public void run()
   {
    
    try
      {     
      ObjectInputStream in = new ObjectInputStream(incoming.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(incoming.getOutputStream());
      String navi;
                             myObject = (DataObject)in.readObject();
      String str=myObject.getMessage();
      String []s=str.split(",,");
      navi=s[0];
      //System.out.println(navi);
   
    //
    String url = "sql2.njit.edu";
                           String ucid = "sy354"; //your ucid
                           String dbpassword = "FyaJnjp6X";            //your MySQL password
 
                           try {
                                         Class.forName("org.gjt.mm.mysql.Driver").newInstance();
                           }
                           catch (Exception e) {
                                         System.err.println(e);
                           }
  
                           //Admin Login
   if(navi.equals("AdminLogin"))
   {
   System.out.println(s[1]);
   System.out.println(s[2]);
   try {
      Connection conn;
                                         conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Admin where username=? and password=?";
      PreparedStatement ps= conn.prepareStatement(query);
                             ps.setString(1, s[1]);
                             ps.setString(2, s[2]);
                                        
                                         ResultSet res=ps.executeQuery();
                           int tuple=0;
                           while(res.next())
                           {
                                         tuple++;
                           }
                           res.close();
                             ps.close();
                             
                           if(tuple == 1)
                           {
                                         myObject.setMessage("true");
      out.writeObject(myObject);
                           }
                           else
        {
                                         myObject.setMessage("false");
      out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }
 
 
              //Member Login
   if(navi.equals("UserLogin"))
   {
   myObject.setMessage("OK");
      out.writeObject(myObject);
   try {
  
      myObject = (DataObject)in.readObject();
      String un=myObject.getMessage();
      System.out.println("Username: "+un);
      myObject.setMessage("OK");
      out.writeObject(myObject);
     
      myObject = (DataObject)in.readObject();
      String pass=myObject.getMessage();
      System.out.println("Password: "+pass);
     
      Connection conn;
                                         conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=? and Password=?";
      PreparedStatement ps= conn.prepareStatement(query);
                             ps.setString(1, un);
                             ps.setString(2, pass);
                                         ResultSet res=ps.executeQuery();
      ResultSetMetaData rsmd=res.getMetaData();
        int col=rsmd.getColumnCount();
                           int tuple=0;
                           while(res.next())
                           {
                                         tuple++;
                           }
   
       
      if(tuple == 1)
                             {
      res.beforeFirst();
        while(res.next())
                               {
          String val="";
          for(int i=1;i<=col;i++)
          {
           val+=res.getString(i)+"<td>";
          }
         
          myObject.setMessage(val);
                                             System.out.println("Row: " + myObject.getMessage());
                                             out.writeObject(myObject);
     
                                                        }
            myObject = (DataObject)in.readObject();
          System.out.println("Status: " + myObject.getMessage());
         
          myObject.setMessage("true");
      out.writeObject(myObject);
       }
     
    else if(tuple == 0)
        {
                                         myObject.setMessage("false");
      out.writeObject(myObject);
                  }
        
     res.close();
                             ps.close();  
   
                          
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }
     
            
      //Admin Adds member
      if(navi.equals("AdminAddMember"))
   {  
     try {
              Connection conn;
                 conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[5]);
                                                     
                                                        ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
      Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
                                         String query1="insert into Member(First_Name,Last_Name,Address,Contact,User_Name,Password) values(?,?,?,?,?,?)";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                       ps1.setString(2, s[2]);
                                                       ps1.setString(3, s[3]);
                                                       ps1.setString(4, s[4]);                           
                                                       ps1.setString(5, s[5]);
                                                       ps1.setString(6, s[6]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
          myObject.setMessage("true");
            out.writeObject(myObject);
                           }
                           else if(tuple == 1)
        {
                           myObject.setMessage("false");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
   }
 
//User Registration
      if(navi.equals("UserRegistration"))
   {  
     try {
                              
              Connection conn;
                 conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[5]);
                                                      
                                                        ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
      Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
                                         String query1="insert into Member(First_Name,Last_Name,Address,Contact,User_Name,Password) values(?,?,?,?,?,?)";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                       ps1.setString(2, s[2]);
                                                       ps1.setString(3, s[3]);
                                                       ps1.setString(4, s[4]);                           
                                                       ps1.setString(5, s[5]);
                                                       ps1.setString(6, s[6]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
          myObject.setMessage("true");
            out.writeObject(myObject);
                           }
                           else if(tuple == 1)
        {
                           myObject.setMessage("false");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
   }
  
   //Admin Adds Admin
      if(navi.equals("AdminAddAdmin"))
   {  
     try {
                              
              Connection conn;
                 conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Admin where username=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[2]);
                                                      
                                                        ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
      Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
                                         String query1="insert into Admin(fullname,username,password) values(?,?,?)";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                      ps1.setString(2, s[2]);
                                                       ps1.setString(3, s[3]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
          myObject.setMessage("true");
            out.writeObject(myObject);
                           }
                           else if(tuple == 1)
        {
                           myObject.setMessage("false");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
   }
     
      //Admin Delets Member  
   if(navi.equals("AdminDeleteMember"))
   {
                           try {
                                         Connection conn;
                   conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[1]);
                                                       ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
                                         myObject.setMessage("false");
            out.writeObject(myObject);
                           }
                           else if(tuple ==1)
        {
        Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query1="delete from Member where User_Name=?";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
                                         myObject.setMessage("true");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }                       
      
      //Admin Search
   if(navi.equals("AdminSearch"))
   {
                           try {
                                        
                                         Connection conn;
                   conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
                   if(s[1].equals(" "))
                    { 
      String query="select * from Member";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ResultSet res=ps.executeQuery();
                                                       ResultSetMetaData rsmd=res.getMetaData();
         int col=rsmd.getColumnCount();
         String result="";
         int rows=0;
                                                       while(res.next())
                                                       {
            rows++;
                                                       }
          
           myObject.setMessage(Integer.toString(rows));
            out.writeObject(myObject);
           
            myObject = (DataObject)in.readObject();
            System.out.println("#: " + myObject.getMessage());
           res.beforeFirst();
          
         while(res.next())
                                                       {
          String val="";
          for(int i=1;i<=col;i++)
          {
            val+=res.getString(i)+"<td>";
          }
         
          myObject.setMessage(val);
                                             System.out.println("Row: " + myObject.getMessage());
                                             out.writeObject(myObject);
     
          myObject = (DataObject)in.readObject();
          System.out.println("Status: " + myObject.getMessage());
                                                       }
          
                                                         res.close();
                                                          ps.close();                       
            myObject.setMessage(result);
            out.writeObject(myObject);
                           }
   else
   {
     String query="select idnum,First_Name,Last_Name,Address,Contact,User_Name from Member where First_Name like ? union select idnum,First_Name,Last_Name,Address,Contact,User_Name from Member where Last_Name like ? union select idnum,First_Name,Last_Name,Address,Contact,User_Name from Member where Address like ? union select idnum,First_Name,Last_Name,Address,Contact,User_Name from Member where Contact like ? union select idnum,First_Name,Last_Name,Address,Contact,User_Name from Member where User_Name like ?  ";  
                                                        PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, "%"+s[1]+"%");
         ps.setString(2, "%"+s[1]+"%");
         ps.setString(3, "%"+s[1]+"%");
         ps.setString(4, "%"+s[1]+"%");
         ps.setString(5, "%"+s[1]+"%");
                                                       ResultSet res=ps.executeQuery();
                                                       ResultSetMetaData rsmd=res.getMetaData();
         int col=rsmd.getColumnCount();
         String result="";
         int rows=0;
                                                       while(res.next())
                                                       {
            rows++;
                                                       }
          
           myObject.setMessage(Integer.toString(rows));
            out.writeObject(myObject);
           
            myObject = (DataObject)in.readObject();
            System.out.println("#: " + myObject.getMessage());
           res.beforeFirst();
          
         while(res.next())
                                                       {
          String val="";
          for(int i=1;i<=col;i++)
          {
            val+=res.getString(i)+"<td>";
          }
         
          myObject.setMessage(val);
                                             System.out.println("Row: " + myObject.getMessage());
                                             out.writeObject(myObject);
     
          myObject = (DataObject)in.readObject();
          System.out.println("Status: " + myObject.getMessage());
                                                       }
          
                                                         res.close();
                                                          ps.close();                       
            myObject.setMessage(result);
            out.writeObject(myObject);
   }
  
                           }
                  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }     
     
       //Refresh
   if(navi.equals("Refresh"))
   {
                           try {
                                        
                                         Connection conn;
                   conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
                  
      String query="select * from Member where idnum=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
         ps.setInt(1, 0);
                                                       ResultSet res=ps.executeQuery();
                                                       ResultSetMetaData rsmd=res.getMetaData();
         int col=rsmd.getColumnCount();
         String result="";
         int rows=0;
                                                       while(res.next())
                                                       {
            rows++;
                                                       }
          
           myObject.setMessage(Integer.toString(rows));
            out.writeObject(myObject);
           
            myObject = (DataObject)in.readObject();
            System.out.println("#: " + myObject.getMessage());
           res.beforeFirst();
          
         while(res.next())
                                                       {
          String val="";
          for(int i=1;i<=col;i++)
          {
            val+=res.getString(i)+"<td>";
          }
          myObject.setMessage(val);
                                             System.out.println("Row: " + myObject.getMessage());
                                             out.writeObject(myObject);
     
          myObject = (DataObject)in.readObject();
          System.out.println("Status: " + myObject.getMessage());
                                                       }
          res.close();
                                                          ps.close();                       
            myObject.setMessage(result);
            out.writeObject(myObject);
                           }
                  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }     
     
     
      
      
         //Admin Edits Profile
   if(navi.equals("AdminEdit"))
   {
                           try {
                                         Connection conn;
                   conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Admin where username=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[1]);
                                                       ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
                                         myObject.setMessage("false");
            out.writeObject(myObject);
                           }
                           else if(tuple ==1)
        {
        Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query1="update Admin set password=? where username=?";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[2]);
         ps1.setString(2, s[1]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
                                         myObject.setMessage("true");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }     
     
      //Admin Updates Member Details
    if(navi.equals("AdminUpdateMember"))
   {
                           try {
                                         Connection conn;
             
                                         conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[5]);
                                                       ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
                                         myObject.setMessage("false");
            out.writeObject(myObject);
                           }
                           else if(tuple ==1)
        {
        Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query1="update Member set First_Name=?,Last_Name=?,Address=?,Contact=?,Password=? where User_Name=?";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                       ps1.setString(2, s[2]);
                                                       ps1.setString(3, s[3]);
                                                       ps1.setString(4, s[4]);                           
                                                       ps1.setString(5, s[6]);
                                                       ps1.setString(6, s[5]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
                                         myObject.setMessage("true");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }
 
//User Updates Details
    if(navi.equals("UserUpdate"))
   {
                           try {
                                         Connection conn;
              conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query="select * from Member where User_Name=?";
                                                       PreparedStatement ps= conn.prepareStatement(query);
                                                       ps.setString(1, s[5]);
                                                       ResultSet res=ps.executeQuery();
                                                       int tuple=0;
                                                       while(res.next())
                                                       {
                                                                    tuple++;
                                                       }
                                                         res.close();
                                                          ps.close();
                             
                           if(tuple == 0)
                           {
                                         myObject.setMessage("false");
            out.writeObject(myObject);
                           }
                           else if(tuple ==1)
        {
        Connection conn1;
      conn1 = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
      String query1="update Member set First_Name=?,Last_Name=?,Address=?,Contact=?,Password=? where User_Name=?";
                                                       PreparedStatement ps1= conn1.prepareStatement(query1);
                                                       ps1.setString(1, s[1]);
                                                       ps1.setString(2, s[2]);
                                                       ps1.setString(3, s[3]);
                                                       ps1.setString(4, s[4]);                           
                                                       ps1.setString(5, s[6]);
                                                       ps1.setString(6, s[5]);
                                                       ps1.executeUpdate();
                                                       ps1.close();
                                         myObject.setMessage("true");
            out.writeObject(myObject);
                  }
                }  catch (Exception e)
                    {  System.out.println(e);
                    }
 
                           in.close();
                                        
                           out.close();
 
                          incoming.close();   
                                                                        
      }     
  
   
      }catch (Exception e)
      {  System.out.println(e);
      }
   }
  
   
   DataObject myObject = null;
   private Socket incoming;
  
}