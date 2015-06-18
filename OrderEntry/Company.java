package oe;

public class Company extends Customer {

    //Constructor
    public Company(String aName, String aAddress, String aPhone,String aContact, int aDiscount) {
        super(aName, aAddress, aPhone);
        contact = aContact;
        discount = aDiscount;
    }

    private String contact;
    private int discount;
    
    //Setters and Getters
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
    	
    //to String Method
    public String toString() {
     return super.toString() + " ("+ contact + ", " + discount + "%) "; 
    }
}
