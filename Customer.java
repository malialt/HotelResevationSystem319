package MVC.HRS.User;
//package HRS;
import MVC.HRS.User.User;
public class Customer extends User
{
    private long phoneNumber;
    
    public Customer(String name, String lastName, long id, long phoneNumber)
    {
        super(name,lastName,id);
        this.phoneNumber = phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public long getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public String toString()
    {
        String s = super.toString()+ " Phone Number : " + phoneNumber + "\n";
        return s;
    }
    
    //////////////////////////////////////////////////
    public Customer(String n,String l,long id)
    {
        super( n,l,id );
        phoneNumber = 905061542500L;
    }
}