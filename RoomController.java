import java.util.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RoomController
{
    
    
    
    public RoomController()
    {
        
       
       
    }
    
    
    public int roomCheck(int number,int day,int month,int year,int outDay,int outMonth,int outYear) 
    {
        return GUIManager.hotel.findFreeRoom(number,day,month,year,outDay, outMonth, outYear);
    }
    
}