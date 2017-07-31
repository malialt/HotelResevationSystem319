import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ýnfoCustomerPanel extends JPanel implements ActionListener
{
    JLabel label1;
    JLabel label2;
    JLabel label3;
    
    JLabel label4;
    
    
    JLabel label5;
   
    JLabel label6;
    
    JTextField name;
    JTextField surName;
    JTextField ýd;
    
    JTextField day;
    JTextField month;
    JTextField year;
    
    JTextField outDay;
    JTextField outMonth;
    JTextField outYear;
    
    JTextField number;
    
    JButton button;
    
    RoomController check;
    
    MakeReservationPanel makeResPanel;
    
    public ýnfoCustomerPanel()
    {
        super();
        
        check = new RoomController();
        
        label1 = new JLabel("name");
        add(label1);
        
        name = new JTextField(10);
        add(name);
        
        label2 = new JLabel("surname");
        add(label2);
        
        surName = new JTextField(10);
        add(surName);
        
        label3 = new JLabel("TC");
        add(label3);
        
        ýd = new JTextField(11);
        add(ýd);
        
        label4 = new JLabel("date of entry");
        add(label4);
        
        day = new JTextField(2);
        add(day);
        month = new JTextField(2);
        add(month);
        year = new JTextField(4);
        add(year);
        
        label5 = new JLabel("release date");
        add(label5);
        
        outDay = new JTextField(2);
        add(outDay);
        outMonth = new JTextField(2);
        add(outMonth);
        outYear = new JTextField(4);
        add(outYear);
        
        label6 = new JLabel("number of people");
        add(label6);
        
        number = new JTextField(1);
        add(number);
        
        button = new JButton("search room");
        button.addActionListener(this);
        add(button);
    }
    
    public void actionPerformed(ActionEvent e) 
    {
       
        if( e.getActionCommand().equals("search room") )
        {
            
            int room = check.roomCheck(Integer.parseInt(number.getText()),Integer.parseInt(day.getText()),Integer.parseInt(month.getText()),Integer.parseInt(year.getText()),Integer.parseInt(outDay.getText()),Integer.parseInt(outMonth.getText()),Integer.parseInt(outYear.getText()));
            if( room==10 )
            {
                
               setVisible(false);
               JOptionPane.showMessageDialog(this," there is no");
               //notFoundPanel = new NotFoundPanel();
               //notFoundPanel.setBounds(0, 100, 400, 600);
               //FrameDeneme.frame.add(notFoundPanel);
               //notFoundPanel.requestFocus(true);
               //notFoundPanel.requestFocusInWindow();
               //FrameDeneme.frame.repaint();
            }
            else
            {
               Date enter = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
               Date out = new Date(Integer.parseInt(outYear.getText()), Integer.parseInt(outMonth.getText()), Integer.parseInt(outDay.getText()));
               setVisible(false);
               makeResPanel = new MakeReservationPanel(enter,out,name.getText(),surName.getText(),Integer.parseInt(ýd.getText()),Integer.parseInt(number.getText()),room);
               makeResPanel.setBounds(0, 100, 400, 600);
               GUIManager.frame.add(makeResPanel);
               makeResPanel.requestFocus(true);
               makeResPanel.requestFocusInWindow();
               GUIManager.frame.repaint(); 
               System.out.println("ýnfo asdfghjkjhgfds**************kjhgfddfgh");
            }
            
        }
        
        
    }
}