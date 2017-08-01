package MVC.HRS.ReservationNode;
//package HRS;
import MVC.HRS.Reservation.*;
public class ReservationNode
{
    public Reservation item;
    public ReservationNode next;
    
    public ReservationNode()
    {
        item = null;
        next = null; 
    }
    
    public ReservationNode( Reservation r)
    {
        item = r;
        next = null;
    }
    
    public ReservationNode( Reservation r, ReservationNode n)
    {
        item = r;
        next = n;
    }
}