package MVC.HRS.Room;

import MVC.HRS.ReservationNode.*;
import MVC.HRS.Reservation.*;
import MVC.HRS.User.*;

import java.util.Date;

public class Room
{
    private int roomNumber;
    private int roomCapacity;
    private int defaultPrice;
    private ReservationNode reservations;
    
    
    //Constructor
    public Room( int capacity )
    {
        roomNumber = 0;
        roomCapacity = capacity;
        defaultPrice = 0;
        reservations = new ReservationNode();
    }
    public Room(int roomNo, int capacity, int price)
    {
        roomNumber = roomNo;
        roomCapacity = capacity;
        defaultPrice = price;
        reservations = new ReservationNode();
    }
    
    //Set Selections
    public Reservation setSelections(int[] features, Reservation r)
    {
        boolean updated = r.updateReservation( this, features );
        
        if( updated )
            return r;
        else
            return null;
    }
    
    //Remove Reservation
    public boolean removeReservation(String code)
    {
        boolean removed = false;
        
        ReservationNode temp = reservations;
        while(temp != null && !removed )
        {
            if( code.equals(temp.item.getCode()) )
            {
                reservations = reservations.next;
                removed = true;
            }
            else if( temp.next != null )
            {
                if( code.equals(temp.next.item.getCode()) )
                {
                    temp.next = temp.next.next;
                    removed = true;
                }
            }
            else
                temp = temp.next;
        }
        
        return removed;
    }
    
    //Add Reservation
    public Reservation addReservation( Reservation r )
    {
        ReservationNode temp = new ReservationNode();
        temp.item = r;
        temp.next = reservations;
        reservations = temp;
        
        return r;
    }
    
    public String showDetails()
    {
        String details = "";
        
        details = details + " Room Number : " + roomNumber + "\n"
            + " Room Capacity : " + roomCapacity + "\n"  
            + " Default Room Price : " + defaultPrice + "\n\n";
        return details;
        
    }
    //Setters and Getters
    public ReservationNode getReservations()
    {
        return reservations;
    }
    
    public int getRoomNo()
    {
        return roomNumber;
    }  
    
    public int getCapacity()
    {
        return roomCapacity;
    }   
    
    public int getDefaultPrice()
    {
        return defaultPrice;
    }   
    
    public  void setDefaultPrice(int price)
    {
        defaultPrice = price;
    }
    
    
    ////////////////////////////////////////////////////////////////////
    public String addRes(Date enter, Date out , Customer c)
    {
        Reservation re= new Reservation(enter,out,c,roomNumber);
        ReservationNode newNode = new ReservationNode(); 
        newNode.item=re;
        newNode.next=reservations;
        reservations=newNode;
        return re.getCode();
    }
     public void setReservations( ReservationNode res)
    {
        reservations = res;
    }
     public Room( int no,int roomNo)
    {
       //Customer c = new Customer("ali","asdawe");
       reservations = null;
   
        
        roomCapacity = no;
        this.roomNumber = roomNo;
    }
}