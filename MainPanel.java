import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainPanel extends JPanel implements ActionListener
{
    
    JButton button;
   
    �nfoCustomerPanel �nfoCustomer ;
    
    public MainPanel()
    {
        
        button = new JButton("customer");
        button.addActionListener(this);
        add(button);
       
    }
    
    
    public void actionPerformed(ActionEvent e) 
    {
       
        if( e.getActionCommand().equals("customer") )
        {
            setVisible(false);
            �nfoCustomer = new �nfoCustomerPanel();
            �nfoCustomer.setBounds(0, 100, 400, 600);
            GUIManager.frame.add(�nfoCustomer);
            �nfoCustomer.requestFocus(true);
            �nfoCustomer.requestFocusInWindow();
            GUIManager.frame.repaint();
        }
        
        
    }
    
}