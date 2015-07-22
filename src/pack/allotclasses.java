package pack;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class allotclasses extends JFrame implements ActionListener
{
    //private JLabel l1;
    private JComboBox cb1, cb2;
    private JButton b1;
    //private JOptionPane p1;
    private int r;
    private int id[];
    
    public allotclasses()
    {
        super("Allot classes");
        setLayout(null);
        
        //l1 = new JLabel("Class");
        //l1.setBounds(new Rectangle(20,20,80,25));
        cb1 = new javax.swing.JComboBox();
        cb2 = new javax.swing.JComboBox();
        //p1 = new javax.swing.JOPtionPane();
        cb1.setBounds(new Rectangle(80,70,120,25));
        cb2.setBounds(new Rectangle(80,20,120,25));
        b1 = new JButton("Add");
        b1.setBounds(new Rectangle(90,120,80,25));
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
            
            //System.out.println(r);
            
            rs = stmt.executeQuery("SELECT id, name FROM login WHERE admin = 0");
            
            id = new int[r];
            String p;
            //String p[] = new String[r];
            int i=0;
            
            cb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Teacher" }));
            
            while(rs.next())
            {
                p = rs.getString(2);
                id[i] = rs.getInt(1);
                //System.out.println(p);
                cb2.addItem(p);
                i++;
            }
                //cb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Select", "a" }));
            //rs = stmt.executeQuery("SELECT id, name FROM login WHERE admin = 0");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Class", "CSE-A", "CSE-B", "ECE-A", "ECE-B" }));
        //JOptionPane.showMessageDialog(null, cb1);
        //add(l1);
        add(cb1);
        add(cb2);
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
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                        Statement stmt = con.createStatement();
                        
                        String data;
                        
                        if(cb1.getSelectedIndex()>0 && cb2.getSelectedIndex()>0)
                        {
                            data = (String) cb1.getItemAt(cb1.getSelectedIndex());
                            int i = cb2.getSelectedIndex();
                            //System.out.println(i + " " + id[i-1]);
                            ResultSet rs;// = stmt.executeQuery("SELECT classes FROM login WHERE id = "+ id[i-1] +"");
                            
                            //int r=0;
                            
                            //while(rs.next())
                                //r++;
                            
                            String classes = "";
                            String token[] = new String[10];
                            
                            rs = stmt.executeQuery("SELECT classes FROM login WHERE id = "+ id[i-1] +"");
                            
                            while(rs.next())
                                classes = rs.getString(1);
                            
                            System.out.println(classes);
                            
                            //i=0;
                            int j=0;
                            
                            StringTokenizer t = new StringTokenizer(classes, "/");
                            while(t.hasMoreTokens())
                            {
                                token[j] = t.nextToken();
                                j++;
                            }
                            
                            //String token1[] = new String[j];
                            //for(int k=0;k<j;k++)
                             //   token1[k] = token[k];
                            //System.out.println(i);
                            
                            
                            int f = j, flag = 0;
                                    
                            //for(i=0;i<f;i++)
                                //System.out.println(token[i]);
                            
                            //System.out.println("sdfdsf");
                            
                            top: for(j=0; j<f; j++)
                                if(token[j].equals(data))
                                {
                                    //System.out.println(token[i] +" "+ data);
                                    flag = 1;
                                    break top;
                                }
                            
                            if(flag == 0)
                            {
                                //if(i>0)
                                    stmt.executeUpdate("UPDATE login SET classes = CONCAT(classes, '"+ data+ "/" +"') WHERE id = "+ id[i-1] +"");
                                //else
                                  //  stmt.executeUpdate("UPDATE login SET classes = '"+ data +"' WHERE id = "+ id[i-1] +"");
                                    
                                    //stmt.executeUpdate("ALTER TABLE  login ADD "+ data +" VARCHAR( 50 ) NOT NULL WHERE id = "+ id[i-1]);
                                    
                                JOptionPane.showMessageDialog(null, "Added successfully.");
                                this.dispose();
                                allotclasses x = new allotclasses();
                                x.setVisible(true);
                                x.setSize(300,300);
                                x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                            
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Class already exists.");
                                this.dispose();
                                allotclasses x = new allotclasses();
                                x.setVisible(true);
                                x.setSize(300,300);
                                x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                            
                        } 
                        
                        else if(cb1.getSelectedIndex()>0)
                        {
                            data = "";
                            JOptionPane.showMessageDialog(null, "Please select a teacher.");
                            this.dispose();
                            allotclasses x = new allotclasses();
                            x.setVisible(true);
                            x.setSize(300,300);
                            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        
                        else
                        {
                            data = "";
                            JOptionPane.showMessageDialog(null, "Please select a class.");
                            this.dispose();
                            allotclasses x = new allotclasses();
                            x.setVisible(true);
                            x.setSize(300,300);
                            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        
                        /*ArrayList al = new ArrayList();
                        
                        Iterator<String> itr = al.iterator();
                        while(itr.hasNext())
                            System.out.println(itr.next());
                        
                        while(rs.next())
                        {
                            al.add(rs.getString(1));
                        }
                        
                        al.add(data);
                        
                        System.out.println(al);
                        
                        stmt.executeUpdate("UPDATE login SET classes = '"+ al +"' WHERE id = 1");*/
                    }
                    
                    catch(Exception exp)
                    {
                        exp.printStackTrace();
                    }
                }
            }
    
    public static void main(String[] args) 
    {
        allotclasses x = new allotclasses();
        x.setVisible(true);
        x.setSize(300,300);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}