
package MVC.HRS.Reservation;
//package HRS;

import java.util.Date;
import java.lang.Math;
import MVC.HRS.Room.*;
import MVC.HRS.User.*;

public class Reservation
{
    private final int CODE_LENGTH = 12;
    private  String[] featureNames;
    private  int[] featurePrices;
    
    private Date enter;
    private Date out;
    
    private Customer c;
    private String code;
    private boolean isPayed;
    private int totalPrice;
    
    private Room[] rooms;
    private String[][] roomFeatures;
    
    
    //Constructor
    public Reservation()
    {
        enter = null;
        out = null;
        c = null;
        code = "";
        isPayed = false;
        rooms = null;
        roomFeatures = null;
        totalPrice = 0;
    }
    
    //Create Reservation
    public void createReservation(Date enter, Date out, Room[] rooms, String[] featureNames, int[] featurePrices)
    {
        this.enter = enter;
        this.out = out;
        this.rooms = rooms;
        this.featureNames = featureNames;
        this.featurePrices = featurePrices;
        
        roomFeatures = new String[ rooms.length + 2 ][featureNames.length + 2];
        setDeafultFeatureTable();
        totalPrice = calculateTotalPrice();
    }
    
    //Update Reservation
    public boolean updateReservation(Room aRoom, int[] features)
    {
        boolean updated = false;
        
        for( int i = 1; (i < rooms.length + 1); i++)
        {
            String roomNo = "" + aRoom.getRoomNo();
            if( roomFeatures[i][0].equals( roomNo ) )
            {
                for(int j = 1; j < featureNames.length + 1; j++)
                    roomFeatures[i][j] = "" + features[j-1];
                
                updatePrice( i , features);
                totalPrice = calculateTotalPrice();
                
                updated = true;
            }
        }
        
        return updated;
    }
    
    //FormCode
    public void formCode()
    {
        String info = c.getName()+ c. getId()+ c.getLastName() + c. getPhoneNumber() + roomFeatures[1][0];
        
        for( int i = 0; i < CODE_LENGTH; i++)
        {
           // int j =  (int)(Math.random() * info.length());
            code = code + info.charAt((int)(Math.random() * info.length()));
        }
        code = code.toUpperCase();
    }
    
    //Verify Payment
    public void verifyPayment( boolean payed )
    {
        isPayed = payed;
    }
    
    
    //Default Features
    public void setDeafultFeatureTable()
    {
        //Room Header
        roomFeatures[0][0] = "Room Numbers ";
        roomFeatures[0][featureNames.length + 1] = "Room Prices ";
        //Room Numbers
        for( int i = 0; i < rooms.length; i++)
            roomFeatures[i+1][0] = "" + rooms[i].getRoomNo();
        
        //Feature Names
        for( int i = 1; i < featureNames.length + 1; i++)
            roomFeatures[0][i] = featureNames[i-1];
        
        //Default Features
        for(int i = 1; i < rooms.length+1; i++)
        {
            for(int j = 1; j < featureNames.length + 1; j++)
                roomFeatures[i][j] = "0";
        }
        
        //Default Prices
        for( int i = 1; i < rooms.length+1; i++)
            roomFeatures[i][ featureNames.length + 1] = "" + rooms[i-1].getDefaultPrice();
    }
    
    public void updatePrice ( int i, int[] features )
    {
        int price = Integer.parseInt( roomFeatures[i][featureNames.length+1] );
        
        for(int j = 1; j < featureNames.length + 1; j++)
        {
            if( roomFeatures[i][j].equals("1"))
                price = price + featurePrices[ j - 1];
        }
        
        roomFeatures[i][featureNames.length + 1] = "" + price;
        
    }
    
    
    //Details
    public String showDetails()
    {
        String details = "";
        
        if( c != null )
        {
            details = details + c.toString() + "\n";
            
            details = details + " RESERVATION CODE : " + code + "\n";
        }
        
        details = details + "\n Price : " + totalPrice + " TL \n";
        
        if( c != null )
        {
            if( isPayed )
                details = details + " The reservation is payed \n";
            else
                details = details + " The reservation is not payed \n\n";
        }
        
        details = details + " Enter Date : " + enter + "\n"
                  + " Leaving Date : " + out + "\n\n";
        
        details = details + " --- Room Details --- \n\n\n";
        for( int j = 0; j < featureNames.length + 2; j++ ) 
        {
            details = details + " ";
            for( int i = 0; i < rooms.length+1; i++)
            {
                
                if( !roomFeatures[i][j].equals("0") && !roomFeatures[i][j].equals("1") )
                {
                    if( i == 0)
                        details = details + roomFeatures[i][j];
                    else
                    {
                        details = details + "     " + roomFeatures[i][j] + "    ";
                    }
                }
                else
                {
                    if(roomFeatures[i][j].equals("0"))
                        details = details + "      x     ";
                    else
                        details = details + "  included  ";
                    
                }
                
                if(i == 0)
                {
                    for(int k = 0; k < 14- roomFeatures[i][j].length(); k++)
                        details = details + " "; 
                }
                
                
                details = details + "\t";
            }
            if(j == 0 || j == featureNames.length){
                details = details + "\n";}
            details = details + "\n";
        }
        
        return details;
    }
    
    
    //Setters and Getters
    public Date getEnterDate()
    {
        return enter;
    }
    
    public Room[] getRooms()
    {
        return rooms;
    }
    
    public Date getOutDate()
    {
        return out;
    }
    
    public Customer getCustomer()
    {
        return c;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public int calculateTotalPrice()
    {
        int price = 0;
        for(int i = 1; i < rooms.length +1; i++)
            price = price + Integer.parseInt( roomFeatures[i][featureNames.length + 1] );
        
        return price;
    }
    
    public int getTotalPrice()
    {
        return totalPrice;
    }
    public boolean getPayed()
    {
        return isPayed;
    }
    
    public void setCustomer(Customer c) 
    {
        this.c = c;
    }
    
    
    
    
    ///////////////////////////////////////////////////
     public Reservation(Date dayEnter, Date dayOut, Customer customer, int roomNo)
    {
        this.enter= dayEnter;
        this.out=dayOut;
        
        this.roomNo=roomNo;
       
        this.c = customer;
        formCode();
        
        //resCode olustur
    }
    
        private int roomNo;
    
    
    
}


