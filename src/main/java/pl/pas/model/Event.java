package pl.pas.model;

import pl.pas.model.resource.Device;
import pl.pas.model.user.Client;

import java.util.Date;
import java.util.UUID;

public class Event extends Entity {
    private Device device;
    private Client client;
    private Date borrowDate;
    private Date returnDate;


    public Event(UUID id, Device device, Client client, Date borrowDate) {
        super(id);
        this.device = device;
        this.client = client;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public Event(UUID id, Device device, Client client, Date borrowDate, Date returnDate) {
        super(id);
        this.device = device;
        this.client = client;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public void ReturnDevice(){
        this.borrowDate = new Date();
    }

    public Device getDevice() {
        return device;
    }

    public Client getClient() {
        return client;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
