/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import javax.swing.*;

/**
 *
 * @author derc
 */
public class Swing {

    JFrame mainFrame;
    public Swing()
    {
        mainFrame = new JFrame();
    int output = JOptionPane.showConfirmDialog(mainFrame, "Click any button","TutorialsPoint.com",JOptionPane.YES_NO_OPTION);

            if(output == JOptionPane.YES_OPTION){
                System.out.println("Yes");
            }else if(output == JOptionPane.NO_OPTION){
                System.out.println("No");
            }
         }
    
    public static void main(String[] args) {
        new Swing();
    }
}
