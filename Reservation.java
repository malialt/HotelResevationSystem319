import java.util.*;

public class Reservation
{
    
    
    private Date dayEnter;
    private Date dayOut;
    
    private int roomNo;
    
    
    
    Customer customer;
    private String resCode; 
    
    public Reservation(Date dayEnter, Date dayOut, Customer customer, int roomNo)
    {
        this.dayEnter= dayEnter;
        this.dayOut=dayOut;
        
        this.roomNo=roomNo;
       
        this.customer = customer;
        formResCode();
        
        //resCode olustur
    }
    
    public Date getEnter()
    {
        return dayEnter;
    }
    
    public Date getOut()
    {
        return dayOut;
    }
    
    public void formResCode()
    {
        resCode= "" + roomNo + customer.getName().charAt(0) + customer.getLastName().charAt(0) + (customer.getTc()/1000000000) + (customer.getTc()%100) + "";
        resCode = resCode.toUpperCase();
    }
    public String getResCode()
    {
        return resCode;
    }
    public String toString()
    {
        return "Reservation Code : " + resCode + "\n"+
            "Enter: "+dayEnter +"\n"+
            "Out: "+dayOut +"\n"+
            "Customer : " + customer;
    }
}