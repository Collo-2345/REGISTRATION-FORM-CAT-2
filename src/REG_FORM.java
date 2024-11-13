import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class REG_FORM {

	private JFrame frame;
	private JTextField TxtId;
	private JTextField TxtName;
	private JTextField TxtAddress;
	private JTextField TxtContact;
	private JRadioButton RrdBtMale,RrBtFemale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					REG_FORM window = new REG_FORM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public REG_FORM() {
		initialize();
		Connect();
		FetchData();
		
	}
	Connection connect;
	PreparedStatement ps;
	ResultSet rs;
	private JTable table;
	
	public void Connect()
	{
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
		 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationdb", "root", "@Collo_2543");
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
	}
	//create a method for fetching data from database
	private void FetchData() {
	    int data;
	    
	    try {
	        ps = connect.prepareStatement("select * from registrationdetails");
	        rs = ps.executeQuery();

	        // Get metadata of the result set (column information)
	        ResultSetMetaData meta = rs.getMetaData();
	        data = meta.getColumnCount();

	        // Get the table model
	        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();

	        // Clear the previous rows before fetching new data
	        tmodel.setRowCount(0);

	        // Loop through the result set and add each row to the table model
	        while (rs.next()) {
	            Vector<String> vector = new Vector<String>();
	            for (int i = 1; i <= data; i++) {
	                vector.add(rs.getString(i));  // Adding data of each column
	            }
	            tmodel.addRow(vector);  // Add the row to the model
	        }
	    } catch (SQLException sql) {
	        sql.printStackTrace();
	    }
	}

 
	/*
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(170, 255, 255));
		frame.setBounds(100, 100, 1104, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Label_Registration_Form = new JLabel("Registration Form");
		Label_Registration_Form.setBounds(31, 28, 241, 29);
		Label_Registration_Form.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		frame.getContentPane().add(Label_Registration_Form);
		
		JPanel Registration_Panel = new JPanel();
		Registration_Panel.setBackground(new Color(209, 209, 209));
		Registration_Panel.setBounds(31, 68, 401, 454);
		Registration_Panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(Registration_Panel);
		Registration_Panel.setLayout(null);
		
		JLabel Label_ID = new JLabel("ID");
		Label_ID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		Label_ID.setBounds(23, 37, 42, 26);
		Registration_Panel.add(Label_ID);
		
		JLabel Label_Name = new JLabel("NAME");
		Label_Name.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		Label_Name.setBounds(23, 86, 95, 26);
		Registration_Panel.add(Label_Name);
		
		JLabel Label_Address = new JLabel("ADDRESS");
		Label_Address.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		Label_Address.setBounds(23, 203, 95, 26);
		Registration_Panel.add(Label_Address);
		
		JLabel Label_Contact = new JLabel("CONTACT");
		Label_Contact.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		Label_Contact.setBounds(23, 252, 95, 26);
		Registration_Panel.add(Label_Contact);
		
	    RrdBtMale = new JRadioButton("Male");
		RrdBtMale.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		RrdBtMale.setBounds(138, 140, 82, 23);
		Registration_Panel.add(RrdBtMale);
		
		
	    RrBtFemale = new JRadioButton("Female");
		RrBtFemale.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		RrBtFemale.setBounds(244, 140, 107, 23);
		Registration_Panel.add(RrBtFemale);
	
		
		ButtonGroup groupgender = new ButtonGroup();//Enable selection of one gender only at a time
		groupgender.add(RrdBtMale);
		groupgender.add(RrBtFemale);
		
		JLabel Label_Gender = new JLabel("GENDER");
		Label_Gender.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		Label_Gender.setBounds(23, 138, 95, 26);
		Registration_Panel.add(Label_Gender);
		
		TxtId = new JTextField();
		TxtId.setBounds(138, 34, 195, 38);
		Registration_Panel.add(TxtId);
		TxtId.setColumns(10);
		
		TxtName = new JTextField();
		TxtName.setColumns(10);
		TxtName.setBounds(138, 83, 195, 38);
		Registration_Panel.add(TxtName);
		
		TxtAddress = new JTextField();
		TxtAddress.setColumns(10);
		TxtAddress.setBounds(138, 200, 195, 38);
		Registration_Panel.add(TxtAddress);
		
		TxtContact = new JTextField();
		TxtContact.setColumns(10);
		TxtContact.setBounds(138, 249, 195, 38);
		Registration_Panel.add(TxtContact);
		
		JButton ExitButton = new JButton("EXIT");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		ExitButton.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 19));
		ExitButton.setBounds(60, 321, 113, 45);
		Registration_Panel.add(ExitButton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// add a try and catch for exception purposes
				  try
				  { 
					 
					 ps = connect.prepareStatement("insert into  registrationdetails(ID,Name,Gender,Address,Contact) values(?,?,?,?,?)");
					 // replace each question mark with their respective value
					 ps.setString(1,TxtId.getText());
					 ps.setString(2,TxtName.getText());
					 
					 String gender = "";
					 if(RrdBtMale.isSelected()) { gender = "Male";}
					 else  if(RrBtFemale.isSelected()) {gender = "Female";}
					 
					 ps.setString(3,gender);
					 ps.setString(4,TxtAddress.getText());
					 ps.setString(5,TxtContact.getText());
					 
					 //this will return number row affect in our db
					 int RowAffect_Db = ps.executeUpdate();
					 //create a condition
					 if(RowAffect_Db > 0)
					 {
						 JOptionPane.showMessageDialog(null, "Registration done sucessfully...");
						 
					 }
					 FetchData();
					 
					 TxtId.setText("");
					 TxtName.setText("");
					 groupgender.clearSelection();
					 TxtAddress.setText("");
					 TxtContact.setText("");
					 TxtId.requestFocus();
					  
				  }catch(SQLException collo)
				  {
					 collo.printStackTrace();
				  }	
			
			}
		});
		RegisterButton.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 18));
		RegisterButton.setBounds(220, 321, 113, 45);
		Registration_Panel.add(RegisterButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(455, 74, 606, 448);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 586, 426);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "ID", "Name", "Gender", "Address", "Contact" }
			));

		JLabel lblNewLabel = new JLabel("...DataBase Information...");
		lblNewLabel.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(594, 20, 303, 46);
		frame.getContentPane().add(lblNewLabel);
	}
}
