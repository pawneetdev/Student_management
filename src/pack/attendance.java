package pack;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class attendance extends JFrame implements ActionListener
{
    private JLabel l1, l2;
    private JCheckBox c1;
    private int r;
    //private JCheckBox chkbox[];
    private JButton b1, b2, b3, b4;
    private JComboBox cb1;
    JCheckBox chkbox[] = new JCheckBox[100];
    JPanel contentPane = new JPanel(null);
    String data, date;
    Calendar c = Calendar.getInstance();
    ResultSet rs;
    
    public attendance()
    {
        super("Attendance Management System");
        setLayout(null); //SCROLLBAR PROBLEM
        //setResizable(false);

        contentPane.setPreferredSize(new Dimension(500, 800));
        JScrollPane pane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(pane);
        
        cb1 = new javax.swing.JComboBox();
        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CSE-A", "CSE-B", "ECE-A", "ECE-B" }));
        b4 = new JButton("Submit");
        b4.addActionListener(this);
        
        cb1.setBounds(new Rectangle(20,20,93,20));
        b4.setBounds(new Rectangle(130,20,93,20));
        contentPane.add(cb1);
        contentPane.add(b4);
        
        date = (c.get(Calendar.DATE) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
        System.out.println(date);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b4)
        {
            contentPane.removeAll();
            contentPane.revalidate(); 
            contentPane.repaint();
            
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
            data  = (String) cb1.getItemAt(cb1.getSelectedIndex());
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            Statement stmt = con.createStatement();
            
            //stmt.executeQuery("SELECT `" + date + "` FROM ");
            
            int s = 0;
            
            try
            {    
            rs = stmt.executeQuery("SELECT * FROM `attendance." + data + "` WHERE `"+ date +"` = 'A' or `"+ date +"` = 'P'");
            
            while(rs.next())
            {
                s=1;
                break;
            }
            }
            catch(Exception exc)
            {
                s=0;
            }
            System.out.println(s); 
             
            if(s==0)
            stmt.executeUpdate("ALTER TABLE `attendance." + data + "` ADD `" + date + "` varchar(1) DEFAULT 'A'");
            
            rs = stmt.executeQuery("SELECT name FROM info WHERE branch = '"+data+"'");
            System.out.println(data);
                    
            while(rs.next())
                r++;
            
            rs = stmt.executeQuery("SELECT name FROM info WHERE Branch = '"+data+"'");
            
            l1 = new JLabel("STUDENTS");
            l1.setBounds(new Rectangle(20,40,100,25));
            contentPane.add(l1);
            
            //Calendar d = Calendar.getInstance();
            l2 = new JLabel(date);//(d.get(Calendar.DATE) + "-" + (d.get(Calendar.MONTH)+1) + "-" + d.get(Calendar.YEAR));
            l2.setBounds(new Rectangle(170,10,100,25));
            contentPane.add(l2);
            
            b1 = new JButton("MARK ALL");
            b1.setBounds(new Rectangle(150,40,93,20));
            b1.setHorizontalAlignment(SwingConstants.LEFT);
            //b1.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            //b1.setBorderPainted(false);
            //b1.setContentAreaFilled(false);
            b1.setFocusPainted(false);
            contentPane.add(b1);
            b1.addActionListener(this);
            
            JLabel label[] = new JLabel[r];
            //chkbox = new JCheckBox[r];
            chkbox = new JCheckBox[r];
            
            int p = 0;
            
            for(p=0; rs.next() ; p++)
            {
                label[p] = new JLabel(rs.getString(1));
                chkbox[p] = new JCheckBox();
                label[p].setBounds(new Rectangle(20,70+25*p,100,25));
                chkbox[p].setBounds(new Rectangle(180,70+25*p,20,20));
                contentPane.add(label[p]);
                contentPane.add(chkbox[p]);
            }
            
            contentPane.setPreferredSize(new Dimension(500, 35*p));
            
            b2 = new JButton("Submit");
            b2.setBounds(new Rectangle(125,70+27*p,75,25));
            b2.setHorizontalAlignment(SwingConstants.LEFT);
            b2.setFocusPainted(false);
            contentPane.add(b2);
            b2.addActionListener(this);
            
            b3 = new JButton("Reset");
            b3.setBounds(new Rectangle(25,70+27*p,75,25));
            b3.setHorizontalAlignment(SwingConstants.LEFT);
            b3.setFocusPainted(false);
            contentPane.add(b3);
            b3.addActionListener(this);
            
            s=0;
            //r=0;
            p=0;
        }
        
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        }
        
        
        if(e.getSource() == b1)
            for(int i =0; i<r; i++)
            {
                //chkbox[i] = new JCheckBox(); //NULL POINTER EXCEPTION
                //System.out.println(chkbox[i].isSelected());
                chkbox[i].setSelected(true);
                
            }
        
        if(e.getSource() == b2)
        {
            int i=0, marked=0;
            for(i=0; i<r;i++)
            {
                if(chkbox[i].isSelected())
                    marked++;
            }
            
            String xyz = marked + " students present. Are you sure you want to continue?";
            
            int output = JOptionPane.showConfirmDialog(this, xyz, "????????????",JOptionPane.YES_NO_OPTION);

            if(output == JOptionPane.YES_OPTION){
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
        
            catch(Exception exc)
            {
                System.out.println("Please check the driver.");
            }
            
            try
            {   
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT MAX(rno) FROM `attendance." + data + "`");
                
                int z=1, r=0;
            
                while(rs.next())
                {
                    r = rs.getInt(1);
                }
                System.out.println(r);
                
                
                //System.out.println(r);
                
                while(z<=r)
                {
                    if(chkbox[z-1].isSelected())
                    {
                        stmt.executeUpdate("UPDATE `attendance." + data + "` SET `"+ date +"` = 'P' WHERE rno =" + z);       
                    }   
                    else            
                    {
                        stmt.executeUpdate("UPDATE `attendance." + data + "` SET `"+ date +"` = 'A' WHERE rno =" + z);    
                    }    
                    z++;
                //System.out.println(z);
                }
                
                JOptionPane.showMessageDialog(this, "Attendance submitted successfully.");
                
                this.dispose();
                attendance x = new attendance();  
                x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                x.setVisible(true);
                x.setSize(500,700);
            
            }
            catch(Exception exc)
            {
                System.out.println(exc);
            }    
            }
            //else if(output == JOptionPane.NO_OPTION){
                
            //}
        }
        
        if(e.getSource() == b3)
            for(int i =0; i<r; i++)
            {
                chkbox[i].setSelected(false);       
            }
        
    }
    
    public static void main(String args[])
    {
        attendance x = new attendance();  
        //x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*x.addWindowListener(new WindowAdapter()
                {
                    @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("Closed");
                    Login y = new Login();
                    y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    y.setVisible(true);
                    y.setSize(400,350);
                }
                });*/
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setVisible(true);
        x.setSize(500,700);
        //x.pack();
    }

}