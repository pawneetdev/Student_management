package pack;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class addteacher extends JFrame implements ActionListener{

    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4;
    private JButton b1;
    private JComboBox cb1;
    
        public addteacher()
        {       
            super("Add Teacher");
            setLayout(null);
            
            l1 = new JLabel("Username");
            l1.setBounds(new Rectangle(20,20,80,25));
            l2 = new JLabel("Password");
            l2.setBounds(new Rectangle(20,80,80,25));
            l3 = new JLabel("Name");
            l3.setBounds(new Rectangle(20,140,80,25));
            l4 = new JLabel("Subject");
            l4.setBounds(new Rectangle(20,200,80,25));
            t1 = new JTextField();
            t1.setBounds(new Rectangle(150,20,100,25));
            t2 = new JTextField();
            t2.setBounds(new Rectangle(150,80,100,25));
            t3 = new JTextField();
            t3.setBounds(new Rectangle(150,140,100,25));
            cb1 = new javax.swing.JComboBox();
            cb1.setBounds(new Rectangle(150,200,100,25));
            b1 = new JButton("Add");
            b1.setBounds(new Rectangle(80,260,60,25));
            b1.addActionListener(this);
            
            cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Java", "Linux", "OOPS", "CA", "C++", "DCS-1", "DCS-2" }));
        
            add(l1);
            add(t1);
            add(l2);
            add(t2);
            add(l3);
            add(t3);
            add(l4);
            add(cb1);
            add(b1);
            
        }       
            
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                if(e.getSource() == b1)
                {  
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                }
                
                catch(Exception exp)
                {
                    System.out.println("Please check the driver.");
                }
            
                try
                {
                    String a = t1.getText();
                    String b = t2.getText();
                    String c = t3.getText();
                    String data = "";
                    data  = (String) cb1.getItemAt(cb1.getSelectedIndex());
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("INSERT INTO login(username, password, name, admin, classes, subject) VALUES('"+a+"','"+b+"','"+c+"',0, '', '"+ data +"')");
                    JOptionPane.showMessageDialog(null, "Teacher added successfully");
                    this.dispose();
                    teacherpage y = new teacherpage();
                    y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    y.setVisible(true);
                    y.setSize(500,700);
                }   
            
                catch(Exception exp)
                {
                    System.out.println(exp);
                }
                }
            }     
    
        
        
        public static void main(String[] args) 
        {
            addteacher x = new addteacher();  
            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            /*x.addWindowListener(new WindowAdapter()
                {
                    @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("Closed");
                    teacherpage y = new teacherpage();
                    y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    y.setVisible(true);
                    y.setSize(500,700);
                }
                });*/
            x.setVisible(true);
            x.setSize(300,350);
        }
}