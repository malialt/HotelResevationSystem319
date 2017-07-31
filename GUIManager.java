import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIManager 
{
    public static JFrame frame;
    
    public static Hotel hotel;
    
    public static void main(String[] args) 
    {
        hotel= new Hotel();
        frame = new JFrame();
        MainPanel main = new MainPanel();
        frame.add(main);
        frame.setVisible(true);
        frame.setBounds(100,100,200,150);
    }
    
}