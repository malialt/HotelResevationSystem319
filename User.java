package MVC.HRS.User;
//package HRS;
public class User
{
    protected String name;
    protected String lastName;
    protected long id;
    
    public User(String name, String lastName, long id)
    {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }
    
    public void setName(String name)
    { 
        this.name = name;
    }
    public void setLastName(String lastName)
    { 
        this.lastName = lastName;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public String getLastName()
    {
        return lastName;
    }
    public long getId()
    {
        return id;
    }
    
    public String toString()
    {
        String s =  " First Name : " + name + "\n"+ " Last Name : " + lastName + "\n" 
            +" Id Number : " + id + "\n" ;
        return s;
    }
}
