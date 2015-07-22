/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Pawneet Singh
 */
public class classes extends JFrame implements ActionListener{
    
    private JLabel l0, l1, l2, l3, l4, l5, l6;
    private JCheckBox c1, c2, c3, c4, c5;
    private JButton b1;
    private int id[];
    private JComboBox cb1, cb2;
    private int r;
    
    public classes()
    {
        
        super("Set Schedule");
        setLayout(null);
        
        //l0 = new JLabel("CSE-A");
        l1 = new JLabel("Days");
        l2 = new JLabel("Monday");
        l3 = new JLabel("Tuesday");
        l4 = new JLabel("Wednesday");
        l5 = new JLabel("Thursday");
        l6 = new JLabel("Friday");
        c1 = new JCheckBox();
        c2 = new JCheckBox();
        c3 = new JCheckBox();
        c4 = new JCheckBox();
        c5 = new JCheckBox();
        b1 = new JButton("Submit");
        cb1 = new javax.swing.JComboBox();
        cb2 = new javax.swing.JComboBox();
        
        //l0.setBounds(new Rectangle(100,105,80,30));
        l1.setBounds(new Rectangle(30,140,80,30));
        l2.setBounds(new Rectangle(25,180,80,30));
        l3.setBounds(new Rectangle(25,210,80,30));
        l4.setBounds(new Rectangle(25,240,80,30));
        l5.setBounds(new Rectangle(25,270,80,30));
        l6.setBounds(new Rectangle(25,300,80,30));
        c1.setBounds(new Rectangle(160,185,20,20));
        c2.setBounds(new Rectangle(160,215,20,20));
        c3.setBounds(new Rectangle(160,245,20,20));
        c4.setBounds(new Rectangle(160,275,20,20));
        c5.setBounds(new Rectangle(160,305,20,20));
        b1.setBounds(new Rectangle(80,355,75,20));
        cb1.setBounds(new Rectangle(80,70,120,25));
        cb2.setBounds(new Rectangle(80,20,120,25));
        
        //add(l0);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
        add(b1);
        
        b1.addActionListener(this);
        
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
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM login WHERE admin = 0");
            
            while(rs.next())
                r++;
            
            rs = stmt.executeQuery("SELECT id, name FROM login WHERE admin = 0");
            
            id = new int[r];
            String p;
            int i=0;
            
            cb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Teacher" }));
            
            while(rs.next())
            {
                p = rs.getString(2);
                id[i] = rs.getInt(1);
                cb2.addItem(p);
                i++;
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Class", "CSE-A", "CSE-B", "ECE-A", "ECE-B" }));
        add(cb1);
        add(cb2);
        
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
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                        Statement stmt = con.createStatement();
                        
                        String data;
                        
                        if(cb1.getSelectedIndex()>0 && cb2.getSelectedIndex()>0)
                        {
                            data = (String) cb1.getItemAt(cb1.getSelectedIndex());
                            int i = cb2.getSelectedIndex();
                            
                            ResultSet rs;
                             
                            int a[] = new int[5];
                            for(int j=0; j<5; j++)
                                a[j] = 0;
            
                            if(c1.isSelected())
                                a[0] = 1;
                            if(c2.isSelected())
                                a[1] = 1;
                            if(c3.isSelected())
                                a[2] = 1;
                            if(c4.isSelected())
                                a[3] = 1;
                            if(c5.isSelected())
                                a[4] = 1;
                            
                            stmt.executeUpdate("UPDATE `login` SET `"+data+"` = '"+a[0]+ "," +a[1]+ "," +a[2]+ "," +a[3]+ "," +a[4]+ "' WHERE `id` = "+ id[i-1]);
                            
                            JOptionPane.showMessageDialog(null, "Added successfully.");    
                            this.dispose();
                            classes x = new classes();
                            x.setVisible(true);
                            x.setSize(300,450);
                            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                       
                        
                        else if(cb1.getSelectedIndex()>0)
                        {
                            data = "";
                            JOptionPane.showMessageDialog(null, "Please select a teacher.");
                            this.dispose();
                            classes x = new classes();
                            x.setVisible(true);
                            x.setSize(300,450);
                            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        
                        else
                        {
                            data = "";
                            JOptionPane.showMessageDialog(null, "Please select a class.");
                            this.dispose();
                            classes x = new classes();
                            x.setVisible(true);
                            x.setSize(300,450);
                            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                    }
                    
                    catch(Exception exp)
                    {
                        exp.printStackTrace();
                    }
                }
        
        
        
        
    }
    
    
    public static void main(String[] args) {
        classes x = new classes();
        x.setVisible(true);
        x.setSize(300,450);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}