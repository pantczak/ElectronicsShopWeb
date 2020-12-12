package pl.pas.model;

import pl.pas.model.resource.Device;
import pl.pas.model.user.Client;

import java.util.Date;

public class Event extends Entity {
    private Device device;
    private Client client;
    private Date borrowDate;
    private Date returnDate;


    public Event( Device device, Client client ) {
        super();
        this.device = device;
        this.client = client;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public Event( Device device, Client client, Date borrowDate) {
        super();
        this.device = device;
        this.client = client;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
