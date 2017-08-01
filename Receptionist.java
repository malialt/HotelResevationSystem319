package MVC.HRS.User;
//package HRS;
import MVC.HRS.User.User;
public class Receptionist extends User
{
    private String password;
    
    public Receptionist(String name, String lastName, long id, String password)
    {
        super(name,lastName,id);
        this.password = password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
    public boolean checkPassword(String pw)
    {
        return ( password == pw);
    }
    
    public String toString()
    {
        String s = super.toString()+ " Password : " + password + "\n";
        return s;
    }
}
