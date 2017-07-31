import java.util.*;


public class Hotel{
    private final int floors = 5;
    private final int numberOfRooms = 6;
    
    private Room[][] rooms;
    
    public Hotel()
    {
        rooms = new Room[floors][numberOfRooms];
        int flr=floors;
        //int nmbrofroom=numberOfRooms;
        
        for(int i = 0; i< floors; i++)
        {
            for(int j = 0; j < numberOfRooms; j++)
            {
                rooms[i][j] = new Room(flr,(100*(flr)+j));
            }
            flr=flr-1;
        }
    }
    
    public int findFreeRoom(int numberOfPeople, int enterDay,int enterMonth,int enterYear,int outDay,int outMonth,int outYear)
    {
        Date enter = new Date(enterYear, enterMonth, enterDay);
        Date out = new Date(outYear, outMonth, outDay);
        if(out.before(enter))
        {
            return 10;
        }
        else{
            
        boolean added = false;
        
        for( int i=0 ; i<numberOfRooms ; i++)
        {
            if(!added)
            {
                Room temp=rooms[floors-numberOfPeople][i];
                resNode rTemp = temp.getReservations();
                boolean pass=true;
                
                
                while( rTemp != null  )
                {
                    if( out.after( rTemp.data.getEnter()) && enter.before( rTemp.data.getOut() ))
                        pass=false;
                    else 
                        rTemp=rTemp.next;
                }
                
                if( pass )// eðer pass ise, fiyat gösterilecek ve customer'a rezervasyonu onaylayýp onaylamadýðý sorulacak
                {
                    //rooms[numberOfPeople-1][i].addRes(enter,out,customer);
                    added = true;
                    return i;
                }
            }
            
        }
        return 10;
        }//else



    }
    
    public String removeReservation(String resCode)
    {
        String roomNo = resCode.substring(0,3);
        
        int no = Integer.parseInt(roomNo);
        
        if( (no/100) > 0 && (no/100)<= floors)
        {
            if( (no%100) >0 && (no%100) <= numberOfRooms )
            {
                Room temp = rooms[(no/100)-1][(no%100)-1];
                
                resNode tempRes = temp.getReservations();
                
                if(tempRes == null)
                    return "There is no reservation with reservation number " + resCode;
                else if( resCode.equals( tempRes.data.getResCode() ) )
                {
                    temp.setReservations(tempRes.next);
                    return " Reservation with " + resCode + " reservation number is removed.";
                }
                else
                {
                    boolean removed = false;
                    
                    while( tempRes.next != null && !removed )
                    {
                        if( resCode.equals( tempRes.next.data.getResCode() ) )
                        {
                            tempRes.next = tempRes.next.next;
                            removed = true;
                             return " Reservation with " + resCode + " reservation number is removed." ;
                        }
                    }
                    
                    if(!removed)
                        return "There is no reservation with reservation number " + resCode;
                    
                }
                
                
            }
            else
                return "There is no reservation with reservation number " + resCode;
        }
        else
            return "There is no reservation with reservation number " + resCode;
        
       return "There is no reservation with reservation number " + resCode; 
    }
    
    public String showReservation(String resCode)
    {
        String roomNo = resCode.substring(0,3);
        
        int no = Integer.parseInt(roomNo);
        
        if( (no/100) > 0 && (no/100)<= floors)
        {
            if( (no%100) >0 && (no%100) <= numberOfRooms )
            {
                Room temp = rooms[(no/100)-1][(no%100)-1];
                
                resNode tempRes = temp.getReservations();
                
                if(tempRes == null)
                    return "There is no reservation with reservation number " + resCode;
                else if( resCode.equals( tempRes.data.getResCode() ) )
                {
                    return tempRes.data.toString();
                }
                else
                {
                    boolean found = false;
                    
                    while( tempRes.next != null && !found )
                    {
                        if( resCode.equals( tempRes.next.data.getResCode() ) )
                        {
                            found = true;
                            return tempRes.next.data.toString() ;
                        }
                    }
                    
                    if(!found)
                        return "There is no reservation with reservation number " + resCode;
                    
                }
                
                
            }
            else
                return "There is no reservation with reservation number " + resCode;
        }
        else
            return "There is no reservation with reservation number " + resCode;
        
       return "There is no reservation with reservation number " + resCode; 
    }
    
    
    
    
    
    public String toString()
    {
        String roomStr = "";
        for(int i = 0; i< floors; i++)
        {
            for(int j = 0; j < numberOfRooms; j++)
            {
                roomStr += rooms[i][j].toString() + "\n";
            }
        }
        return roomStr;
     }
        
        
        
   public String makeBooking(int numb, int i, Date enter, Date out, Customer c)
   {
       return rooms[floors-numb][i].addRes(enter,out,c);
   }
        
        

    
}