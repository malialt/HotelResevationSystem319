package MVC.HRS.Hotel;
import java.util.*;

import MVC.HRS.Room.*;
import MVC.HRS.ReservationNode.*;
import MVC.HRS.Reservation.*;
import MVC.HRS.User.*;

public class Hotel
{
    private int emptyRoomPrice;
    private int priceForEachPerson;
    
    private int floors;
    private int numberOfRooms;
    private Room[][] rooms;
    
    private String[] featureNames = {"tv"};
    private int[] featurePrices = {1};

    //Constructor
    public Hotel(int floors, int numberOfRooms, int emptyRoomPrice, int priceForEachPerson)
    {
        this.floors = floors;
        this.numberOfRooms = numberOfRooms;
        
        this.emptyRoomPrice = emptyRoomPrice;
        this.priceForEachPerson = priceForEachPerson;
        
        rooms = new Room[floors][numberOfRooms];
        
        for( int i = 0; i < floors; i++)
        {
            for( int j = 0; j < numberOfRooms; j++)
            {
                int roomNo = ((i + 1) * 100) + (j + 1); 
                int capacity = i + 1;  //Each floor contains one type of room
                int price = emptyRoomPrice + ( capacity * priceForEachPerson );
                
                rooms[i][j] = new Room( roomNo, capacity, price );
            }
        }
    }
    
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Find Free Room
    public Room[] findFreeRoom( Date enter, Date out, Room[] searchRoom)
    {
        Room[] temp = new Room[searchRoom.length];
        
        boolean available = true;
        
        for( int i = 0; i < searchRoom.length && available; i++)
        {
            int capacity = searchRoom[i].getCapacity();
            
            boolean found = false;
            for( int j = 0; (j < numberOfRooms && !found); j++)
            {
                Room tempRoom = rooms[capacity-1][j];
                ReservationNode rTemp = tempRoom.getReservations();
                boolean pass = true; 
                
                while( rTemp != null && rTemp.item != null && pass )
                {
                    if( out.after( rTemp.item.getEnterDate()) && enter.before( rTemp.item.getOutDate() ))
                        pass=false;
                    else 
                        rTemp=rTemp.next;
                }
                
                if( pass )
                {
                    temp[i] = tempRoom;
                    found = true;
                }
            }
            if( !found )
            {
                available = false;
                temp = null;
            }
        }
        return temp;
    }
    
    //Check Room Array if the room array is nut null
    public boolean availability (Room[] rooms)
    {
        return ( rooms != null );
    }
    
    //Not Available Room
    public String noAvailableRoomWarning()
    {
        String warning = "Sorry, \n" + 
                         "There is no available desired type of room(s) between the desired time interval. \n" + 
                         " You can search for other combination(s) of room types and time intervals.";
        
        return warning;
    }
    
    //Available Rooms
    public Room[] showAvailableRoom(Room[] rooms)
    {
        return rooms;
    }
    
    //Create a Temporary Reservation with available rooms
    public Reservation createReservation(Date enter, Date out, Room[] rooms)
    {
        Reservation r = new Reservation();
        r.createReservation( enter, out, rooms, featureNames, featurePrices);
        
        return r;
    }
    
    //Setting Room Features
    public Reservation selectRoomFeatures(Reservation r, Room aRoom, int[] features)
    {
        return aRoom.setSelections( features, r);
    }
    
    // Show Reservation Details
    public String showReservationDetails(Reservation r)
    {
        return r.showDetails();
    }
    
    // Make Booking
    public Reservation makeBooking(String name, String last, long id, long phoneNumber, Reservation r)
    {
        Customer c = new Customer(name, last, id, phoneNumber);
        r.setCustomer(c);
        r.formCode();
        
        
        Room[] resRooms = r.getRooms();
        
        for( int i = 0; i < resRooms.length; i++)
        {
            boolean added = false;
            
            for( int j = 0; ( j < floors && !added); j ++)
            {
                for( int k = 0; ( k < numberOfRooms && !added); k++)
                {
                    if (resRooms[i].getRoomNo() == rooms[j][k].getRoomNo() )
                    {
                        rooms[j][k].addReservation( r );
                        added = true;
                    }
                }
            }
        }
        return r;
    }

    //Make Payment
    public Reservation makePayment( String name, String lastName,long id, long phoneNumber,
                                   String cardName, long cardNumber, int month, int year, int cvc, Date today, Reservation r)
    {
        Date dueDateOfCard = new Date(year, month, 28);
        
        String len = cardNumber + "";
        
        if(len.length() == 16)
        {
            if( today.before(dueDateOfCard))
            {
                if(cvc <= 999 && cvc >= 100)
                {
                    r.verifyPayment(true);
                }
                else{
                    r.verifyPayment(false);
                }
            }
            else
            {
                r.verifyPayment(false);
            }
        }
        else 
        {
            r.verifyPayment(false);
        }
        
        if( verifyPayment(r))
            makeBooking( name, lastName, id, phoneNumber, r);
        
        return r;
    }
    
    //Check if The Payment is Accepted
    public boolean verifyPayment(Reservation r)
    {
        return r.getPayed();
    }
    
    //Not Accepted Warning
    public String paymentNotAcceptedWarning()
    {
        String warning = "Your trying of payment is not accepted.";
        
        return warning;
    }

    //Find Reservation
    public Reservation findReservation(String code)
    {
        boolean found = false;
        Reservation r = null;
        ReservationNode res;
        for( int i = 0; (i < floors && !found ); i++ )
        {
            for( int j = 0; (j < numberOfRooms && !found ); j++ )
            {
                 res = rooms[i][j].getReservations();
                
                while( res != null && res.item != null && !found)
                {
                    if(res.item != null)
                    {
                        if( (res.item.getCode()).equals(code))
                        {
                            r = res.item;
                            found = true;
                        }
                        else
                        {
                            res = res.next;
                        }
                    }
                }
            }
        }
        return r;
    }
    
    //Check if the Reservation r is not null
    public boolean reservationExists(Reservation r)
    {
        return ( r != null );
    }
    
    //No Exixsting Reservation Warning
    public String noExistingReservationWarning()
    {
        String warning = "There is no such reservation";
        
        return warning;
    }

    //Remove Reservation
    public boolean removeReservation(Reservation r)
    {
        boolean removeDone = true;
        
        Room[] resRooms = r.getRooms();
        
        for( int i = 0; i < resRooms.length; i++)
        {
            int no = resRooms[i].getRoomNo();
            
            removeDone = removeDone && rooms[(no/100) - 1][ (no%100) - 1].removeReservation(r.getCode());
        }
        return removeDone;
    }
    
    //Remove Verification Message (Check removeReservation's return)
    public String removedVerificationMessage( boolean isRemoved )
    {        
        String message;
        
        if( isRemoved )
            message = "Reservation is removed";
        else
            message = "Reservation is not removed";
        
        return message;
    }
    
    
    public Reservation[] getRoomReservations( Room aRoom)
    {
        ReservationNode r = aRoom.getReservations();
        ReservationNode temp = r;
        int size = 0;
        while( temp!= null && temp.item != null)
        {
            size++;
            temp = temp.next;
        }
        Reservation[] rArray = new Reservation[size];
        
        for( int i = 0; i< size; i++)
        {
            rArray[i] = r.item;
            r = r.next;
        }
        
        return rArray;
    }
    public String showRoomDetails( Room aRoom)
    {
        return aRoom.showDetails();
    }
    
    
    public Room[][] getAllRooms()
    {
        return rooms;
    }
    
    public void setFeatureNames( String[] featureNames )
    {
        this.featureNames = featureNames;
    }
    
    public void setFeaturePrices( int[] featurePrices )
    {
        this.featurePrices = featurePrices;
    }
    public String[] getFeatureNames()
    {
        return featureNames;
    }
    public int[] getfeaturePrices()
    {
        return featurePrices;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    
   public Hotel()
    {
        rooms = new Room[floors][numberOfRooms];
        
        for(int i = 0; i< floors; i++)
        {
            for(int j = 0; j < numberOfRooms; j++)
            {
                rooms[i][j] = new Room(i+1,(100*(i+1))+j+1);
            }
        }
    }
    private final int floors2 = 5;
    private final int numberOfRooms2 = 6;
}