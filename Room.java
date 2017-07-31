import java.util.*;

public class Room
{
    private resNode r;
    private int numOfPeople;
    private int roomNo;
    
    public Room( int no,int roomNo)
    {
       //Customer c = new Customer("ali","asdawe");
       r = null;
   
        
        numOfPeople = no;
        this.roomNo = roomNo;
    }
    
    public String addRes(Date enter, Date out , Customer c)
    {
        Reservation re= new Reservation(enter,out,c,roomNo);
        resNode newNode = new resNode(); 
        newNode.data=re;
        newNode.next=r;
        r=newNode;
        return re.getResCode();
    }
    
    public resNode getReservations()
    {
        return r;
    }
    
    public void setReservations( resNode res)
    {
        r = res;
    }
    public int getRoomNo()
    {
        return roomNo;
    }
    
    
    
    public String toString()
    {
        String reservations = "";
        resNode temp = r;
        int i = 1;
        while ( temp != null)
        {
            reservations += i + ") " + temp.data.toString() + "\n"+ "\n";
            temp = temp.next;
            i++;
        }
        
        
        return "Room : " + roomNo + "\n"+
               "Type : " + numOfPeople + " Person \n" + reservations;
            
    }

}
