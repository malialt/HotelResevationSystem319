import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeReservationPanel extends JPanel implements ActionListener
{
    Date entDate;
    Date outDate;
    String nme;
    String srname;
    long ýD;
    int i;
    int number;
    
    private static int count;
    
    String c;
    
    String code;
    
    JButton button;
   
    MakeReservationControl makeRes ;
    
    public MakeReservationPanel(Date enter,Date out,String name,String surname,long ýd,int nmber,int ý)
    {
        entDate=enter;
        outDate=out;
        nme=name;
        srname=surname;
        ýD=ýd;
        number=nmber;
        i=ý;
        
        button = new JButton("make reservation");
        button.addActionListener(this);
        add(button);
        
        System.out.println("make const asdfghjkjhgfds**************kjhgfddfgh");
       
    }
    
    
    public void actionPerformed(ActionEvent e) 
    {
       
        if( e.getActionCommand().equals("make reservation") )
        {
            count++;
            c=""+count;
            Customer c= new Customer(nme,srname,ýD);
            makeRes= new MakeReservationControl();
            code = makeRes.booking(entDate,outDate,c,number,i);
            System.out.println(code);
            JOptionPane.showMessageDialog(this,code);
            /*setVisible(false);
            showResCode = new ShowResCodePanel();
            showResCode.setBounds(0, 100, 400, 600);
            FrameDeneme.frame.add(showResCode);
            showResCode.requestFocus(true);
            showResCode.requestFocusInWindow();
            FrameDeneme.frame.repaint();*/
        }
        
        
    }
    
}