public class Customer
{
    String name;
    String lastName;
    long tc;
    
    public Customer(String n,String l,long tc)
    {
        name = n;
        lastName = l;
        this.tc=tc;
    }
    public String getName()
    {
        return name;
    }
    public String getLastName()
    {
        return lastName;
    }
    
    public long getTc()
    {
        return tc;
    }
    public String toString()
    {
        return name + " , " +lastName + " , " + tc;
    }
}
