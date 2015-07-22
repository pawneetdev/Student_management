package pack;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class adminpage extends JFrame implements ActionListener
{
    private JLabel l1, l2, l3, l4, l5;
    private JCheckBox c1;
    private int r;
    private JLabel label[];
    private JLabel label1[];
    //private JCheckBox chkbox[];
    private JButton b1, b2, b3;
    JButton button[] = new JButton[100];
    JButton button1[] = new JButton[100];
    int id;
    JPanel contentPane = new JPanel(null);
    
    public adminpage()
    {
        super("Admin Panel");
        setLayout(null); //SCROLLBAR PROBLEM
        //setResizable(false);

        contentPane.setPreferredSize(new Dimension(500, 800));
        JScrollPane pane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(pane);
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        
        catch(Exception e)
        {
            System.out.println("Please check the driver.");
        }
        
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM login WHERE admin = 1");
            
            while(rs.next())
                r++;
            
            rs = stmt.executeQuery("SELECT id, name FROM login WHERE admin = 1");
            
            l1 = new JLabel("ID");
            l1.setBounds(new Rectangle(20,40,100,25));
            contentPane.add(l1);
            
            l2 = new JLabel("ADMINS");
            l2.setBounds(new Rectangle(100,40,100,25));
            contentPane.add(l2);
            
            label = new JLabel[r];
            label1 = new JLabel[r];
            button = new JButton[r];
            button1 = new JButton[r];
            
            int p;
            
            for(p=0; rs.next() ; p++)
            {
                label[p] = new JLabel(rs.getString(1));
                label1[p] = new JLabel(rs.getString(2));
                button[p] = new JButton("Edit");
                button1[p] = new JButton("Remove");
                label[p].setBounds(new Rectangle(20,70+25*p,100,25));
                label1[p].setBounds(new Rectangle(100,70+25*p,100,25));
                button[p].setBounds(new Rectangle(280,70+25*p,55,20));
                button1[p].setBounds(new Rectangle(350,70+25*p,80,20));
                contentPane.add(label[p]);
                contentPane.add(label1[p]);
                button[p].setHorizontalAlignment(SwingConstants.LEFT);
                button[p].setFocusPainted(false);
                button1[p].setHorizontalAlignment(SwingConstants.LEFT);
                button1[p].setFocusPainted(false);
                contentPane.add(button[p]);
                contentPane.add(button1[p]);
                button[p].addActionListener(this);
                button1[p].addActionListener(this);
            }
            
            contentPane.setPreferredSize(new Dimension(500, 35*p));
            
            b2 = new JButton("Add admin");
            b2.setBounds(new Rectangle(100,70+38*p,100,25));
            b2.setHorizontalAlignment(SwingConstants.LEFT);
            b2.setFocusPainted(false);
            contentPane.add(b2);
            b2.addActionListener(this);
            
            /*b3 = new JButton("Reset");
            b3.setBounds(new Rectangle(25,70+27*p,75,25));
            b3.setHorizontalAlignment(SwingConstants.LEFT);
            b3.setFocusPainted(false);
            contentPane.add(b3);
            b3.addActionListener(this);*/
            
        }
        
        catch(Exception e)
        {
            System.out.println("Could not connect to database.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i =0; i<r; i++)
        if(e.getSource() == button[i])
        {       
                this.dispose();
                final JFrame editframe;
                JLabel l1, l2, l3;
                final JTextField t1, t2, t3;
                JButton b1, b5;
                
                editframe = new JFrame("Modify admin details");
                editframe.setLayout(null);
                
                
                l1 = new JLabel("Username");
                l1.setBounds(new Rectangle(20,20,80,25));
                l2 = new JLabel("Password");
                l2.setBounds(new Rectangle(20,80,80,25));
                l3 = new JLabel("Name");
                l3.setBounds(new Rectangle(20,140,80,25));
                t1 = new JTextField();
                t1.setBounds(new Rectangle(150,20,100,25));
                t2 = new JTextField();
                t2.setBounds(new Rectangle(150,80,100,25));
                t3 = new JTextField();
                t3.setBounds(new Rectangle(150,140,100,25));
                b1 = new JButton("Submit");
                b1.setBounds(new Rectangle(30,200,80,25));
                b1.addActionListener(this);
                b5 = new JButton("Cancel");
                b5.setBounds(new Rectangle(130,200,80,25));
                b5.addActionListener(this);
        
                editframe.add(l1);
                editframe.add(l2);
                editframe.add(l3);
                editframe.add(t1);
                editframe.add(t2);
                editframe.add(t3);
                editframe.add(b1);
                editframe.add(b5);
                
                editframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editframe.setVisible(true);
                editframe.setSize(300,300);
                
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
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT username, password, name, id FROM login WHERE id = '"+label[i].getText()+"'");
                    while(rs.next())
                    {
                        t1.setText(rs.getString(1));
                        t2.setText(rs.getString(2));
                        t3.setText(rs.getString(3));
                        id = rs.getInt(4);
                    }
                }   
            
                catch(Exception exp)
                {
                    System.out.println(exp);
                }
                
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Object source = e.getSource();
                        if (source instanceof JButton) {
                            //JButton btn = (JButton)source;
                            
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
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate("UPDATE login SET username = '"+t1.getText()+"', password = '"+t2.getText()+"', name = '"+t3.getText()+"' WHERE id = "+id);
                            
                                JOptionPane.showMessageDialog(null, "Updated successfully");
                                adminpage y = new adminpage();
                                y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                y.setVisible(true);
                                y.setSize(500,700);
                            }
                            
                            catch(Exception ex)
                            {
                                System.out.println(e);
                            }   
                        }
                    }
                });
                
                b5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Object source = e.getSource();
                        if (source instanceof JButton) {
                            //JButton btn = (JButton)source;
                            
                            editframe.dispose();
                            adminpage y = new adminpage();
                            y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            y.setVisible(true);
                            y.setSize(500,700);
                        }
                    }
                });
                
        }
        
        
        if(e.getSource() == b2)
        {
            this.dispose();
            addadmin x = new addadmin();
            x.setVisible(true);
            x.setSize(300,300);
            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        int i;
        for(i =0; i<r; i++)
        if(e.getSource() == button1[i])
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
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("DELETE FROM login WHERE id = '"+label[i].getText()+"'");
                    JOptionPane.showMessageDialog(null, "Deleted successfully");
                    adminpage y = new adminpage();
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
    
    public static void main(String args[])
    {
        adminpage x = new adminpage();  
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setVisible(true);
        x.setSize(500,700);
        //x.pack();
    }

}