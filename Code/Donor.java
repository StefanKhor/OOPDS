/**
 * Act as Usertype of donor and store the required details of donor
 * Such as donor name and donor phone number
 */
public class Donor extends User{

    // data types
    private String phone;
    private String name;
    
    /**
     * Default constructor
     */
    Donor(){}

    /**
     * Overloaded constructor
     * @username the username of the donor and call the superclass constructor(without password)
     * @name the name of the donor
     * @phone the phone number of the donor
     */
    Donor(String username, String name, String phone){
        super(username);
        this.phone = phone;
        this.name = name;
    }

    /**
     * Overloaded constructor
     * @username the username of the donor and call the superclass constructor
     * @password the password of the donor and call the superclass constructor
     * @name the name of the donor
     * @phone the phone number of the donor
     */
    Donor(String username, String password, String name, String phone) {
        super(username, password);
        this.phone = phone;
        this.name = name;
    }

    /**
     * Getter of the donor name
     * @return the name of the donor
     */
    public String getDonorname(){
        return this.name;
    }

    /**
     * Getter of the phone number
     * @return the phone number of the donor
     */
    public String getPhonenum(){
        return this.phone;
    }

    /**
     *  Convert the donor into csv string 
     *  Userful when csv string conversion of donor to the aids.csv file
     *  @return the csv string representation of usertype,donor superclass constructor,donor name and phonenumber
     */
    public String toCSVString(){
        return "DONOR" + "," + super.toCSVString() + "," + this.name + "," + this.phone;
    }

    /**
     * Method to create null donor ("-","-","-")
     * Used to show that no donor is assigned in the aids.csv file
     * @return the null donor ("-","-","-")
     */
    public static Donor nulldonor(){
        return new Donor("-","-","-");
    }
}
