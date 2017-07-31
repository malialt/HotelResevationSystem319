import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeReservationControl 
{
    
    
   
    
    
    public MakeReservationControl()
    {
        
        
    }
    
    
    public String booking(Date entDate,Date outDate,Customer c, int number, int i) 
    {
       
        return GUIManager.hotel.makeBooking(number, i, entDate, outDate, c);
        
        
    }
    
}