/***************************************************************************************************
*  ITC-5201-RIA – Assignment 4                                                                     *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source     *

*  (including web sites) or distributed to other students/social media.                            *
                                                                                                                                                                             
*  Name: Jaimaica Daisy Eugenio  Student ID: N01516797  Date: July 7, 2022                         *
*  Name: Jaspreet Singh          Student ID: N01513743  Date: July 7, 2022                         *
*  Name: Anna Salas              Student ID: N01517324  Date: July 7, 2022                         */

package StaffInformation;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class StaffInformation extends JFrame {
	 java.sql.Connection con;
	    java.sql.Statement st;
	JFrame frame = new JFrame();
	JTextField txtCustomerID = new JTextField();
	JLabel lblStaff = new JLabel();
	JLabel lblID = new JLabel();
	JLabel lblLastName = new JLabel();
	JLabel lblFirstName = new JLabel();
	JLabel lblMI = new JLabel();
	JLabel lblAddress = new JLabel();
	JLabel lblCity = new JLabel();
	JLabel lblState = new JLabel();
	JLabel lblTelephone = new JLabel();
	JLabel lblDBConnection = new JLabel();
	JTextField txtID = new JTextField();
	JTextField txtLastName = new JTextField();
	JTextField txtFirstName = new JTextField();
	JTextField txtMI = new JTextField();
	JTextField txtAddress = new JTextField();
	JTextField txtCity = new JTextField();
	JTextField txtState = new JTextField();
	JTextField txtTelephone = new JTextField();
	JButton btnView = new JButton();
	JButton btnInsert = new JButton();
	JButton btnUpdate = new JButton();
	JButton btnClear = new JButton();
	
	//Main to show the frame
	public static void main(String[] args) //throws FileNotFoundException 
	{
		StaffInformation window = new StaffInformation();
		window.frame.setVisible(true);
	}	
	
	//Call method to create the page
	public StaffInformation() {
		frame.getContentPane().setLayout(null);
	
		createStaffPage();
	}

	//Method to create the page, connect to DB and implement buttons
	private void createStaffPage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 255);
		frame.getContentPane().setLayout(null);	
		
		lblStaff = new JLabel("Staff Information");
		lblStaff.setBounds(10, 10, 220, 15);
		frame.getContentPane().add(lblStaff);
		
		lblID = new JLabel("ID");
		lblID.setBounds(10, 40, 220, 15);
		frame.getContentPane().add(lblID);
	
		txtID.setBounds(30, 38, 140, 20);
		txtID.setColumns(10);
		txtID.setBackground(Color.yellow);
		frame.getContentPane().add(txtID);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 65, 220, 15);
		frame.getContentPane().add(lblLastName);
		
		txtLastName.setBounds(80, 63, 140, 20);
		txtLastName.setColumns(10);
		frame.getContentPane().add(txtLastName);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(225, 65, 63, 15);
		frame.getContentPane().add(lblFirstName);
		
		txtFirstName.setBounds(300, 63, 140, 20);
		txtFirstName.setColumns(10);
		frame.getContentPane().add(txtFirstName);
		
		lblMI = new JLabel("MI");
		lblMI.setBounds(445, 65, 220, 15);
		frame.getContentPane().add(lblMI);
		
		txtMI.setBounds(465, 63, 40, 20);
		txtMI.setColumns(5);
		frame.getContentPane().add(txtMI);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 90, 220, 15);
		frame.getContentPane().add(lblAddress);
		
		txtAddress.setBounds(67, 88, 140, 20);
		txtAddress.setColumns(10);
		frame.getContentPane().add(txtAddress);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(10, 115, 40, 15);
		frame.getContentPane().add(lblCity);
		
		txtCity.setBounds(43, 113, 140, 20);
		txtCity.setColumns(10);
		frame.getContentPane().add(txtCity);
		
		lblState = new JLabel("State");
		lblState.setBounds(190, 115, 220, 15);
		frame.getContentPane().add(lblState);
		
		txtState.setBounds(225, 113, 40, 20);
		txtState.setColumns(5);
		frame.getContentPane().add(txtState);
		
		lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(10, 140, 220, 15);
		frame.getContentPane().add(lblTelephone);
		
		txtTelephone.setBounds(80, 138, 140, 20);
		txtTelephone.setColumns(10);
		frame.getContentPane().add(txtTelephone);
		
		btnView = new JButton("View");
		
		//View button to view record from staff table once ID is given
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String query="Select * from staff where id='"+txtID.getText()+"'";
		         try{ 
		             ResultSet rs=st.executeQuery(query);
		              while (rs.next()) {
		            	  txtID.setText(rs.getString("ID"));
		            	  txtFirstName.setText(rs.getString("FIRSTNAME"));
		  		                    txtLastName.setText(rs.getString("LASTNAME"));
		  		                    txtMI.setText(rs.getString("MI"));
		  		                    txtAddress.setText(rs.getString("ADDRESS"));
		  		                   txtCity.setText(rs.getString("CITY"));
		  		                   txtState.setText(rs.getString("STATE"));
		  		                   txtTelephone.setText(rs.getString("TELEPHONE"));           
		                }		
			}
		         catch(Exception e1) {}
			}});
		btnView.setBounds(80, 170, 80, 25);
		frame.getContentPane().add(btnView);
		
		//Insert button to insert record to staff table, validations upon insert
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				  if (txtID.getText().equals("") || txtTelephone.getText().equals("") || 
					txtState.getText().equals("")||txtCity.getText().equals("")||
					txtAddress.getText().equals("")||txtMI.getText().equals("")||
					txtFirstName.getText().equals("")||txtLastName.getText().equals(""))
				  	{
					  JOptionPane.showMessageDialog(null, "Field cannot be empty");
					}
				  else if(txtID.getText().length()<9 || txtID.getText().length()>9)
				  {
					  JOptionPane.showMessageDialog(null, "ID length should be 9");
				  }
				  else if(txtState.getText().length()<2 || txtState.getText().length()>2)
				  {
					  JOptionPane.showMessageDialog(null, "State should have only 2 letters");
				  }
				  else if(txtMI.getText().length()<1 || txtMI.getText().length()>1)
				  {
					  JOptionPane.showMessageDialog(null, "MI should have only 1 letter");
				  }
				  else if(txtTelephone.getText().length()<10 || txtTelephone.getText().length()>10)
				  {
					  JOptionPane.showMessageDialog(null, "Telephone should have 10 digits");
				  }
				  else
				  {   //query to select from staff table
					  String query="Select * from staff where id='"+txtID.getText()+"'";
				         try{
				                           
				             ResultSet rs=st.executeQuery(query);
				              if (rs.next()) {
				            	  JOptionPane.showMessageDialog(null,"ID Already registered"); //cannot insert duplicate id
				              }
				              else
				              {   //query to insert, get the text from textfield
				            	  String sql="Insert into staff(ID,FIRSTNAME,LASTNAME,MI,ADDRESS,CITY,STATE,TELEPHONE) values('"+txtID.getText()+"','"+txtFirstName.getText()+"',"
				  		                    + "'"+txtLastName.getText()+"','"+txtMI.getText()+"','"+txtAddress.getText()+"',"
				  		                    		+ "'"+txtCity.getText()+"','"+txtState.getText()+"','"+txtTelephone.getText()+"')";

				  		                    try {
				  								st=con.createStatement();
				  								 st.executeUpdate(sql);
				  								JOptionPane.showMessageDialog(null,"Record Added");
				  								
				  							} catch (SQLException e1) {
				  								// TODO Auto-generated catch block
				  								e1.printStackTrace();
				  							} 
				              }
				         }
				         catch(Exception e1) {}				
				  }							
			}
		});
		btnInsert.setBounds(165, 170, 80, 25);
		frame.getContentPane().add(btnInsert);
		
		//Update button, to update a record from staff table, validations upon update
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String q="update staff set FIRSTNAME='"+txtFirstName.getText()+"',LASTNAME='"+txtLastName.getText()+"',MI='"+txtMI.getText()+"'"
		            		+ ",ADDRESS='"+txtAddress.getText()+"', CITY='"+txtCity.getText()+"', STATE='"+txtState.getText()+"',"
		            				+ " TELEPHONE='"+txtTelephone.getText()+"' where ID='"+txtID.getText()+"'";
		            try {
		                st.execute(q);// TODO add your handling code here:
		                JOptionPane.showMessageDialog(null,"Record Updated");            
		            }
		            catch(Exception e1)
		            {
		                e1.printStackTrace();
		            }
				
			}
		});
		btnUpdate.setBounds(250, 170, 80, 25);
		frame.getContentPane().add(btnUpdate);
		
		//Clear the contents on the textfields
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 txtID.setText("");
           	  txtFirstName.setText("");
 		                    txtLastName.setText("");
 		                    txtMI.setText("");
 		                    txtAddress.setText("");
 		                   txtCity.setText("");
 		                   txtState.setText("");
 		                   txtTelephone.setText("");
			}
		});
		btnClear.setBounds(335, 170, 80, 25);
		frame.getContentPane().add(btnClear);
		
		lblDBConnection = new JLabel("");
		lblDBConnection.setBounds(2, 200, 220, 15);
		frame.getContentPane().add(lblDBConnection);		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
			//System.out.println("connected");
			lblDBConnection.setText("Database connected");
			   st=con.createStatement();
		}catch(Exception e){System.out.println(e);}
	 
		
	}
}