/**
 * Used to store the donated aid made by the Donor
 */
public class DonatedAid extends Aid{
    private Donor donor;

    /**
     * Default Constructor
     */
    DonatedAid(){}

    /**
     * Overloaded Constructor 
     * Call a superclass overloaded constructor to construct Aid inside the DonatedAid (inheritance)
     * @param donor The Donor that donated the aid
     * @param Aid The aid name that is donated
     * @param Quantity The quantity donated
     */
    DonatedAid(Donor donor,String Aid,int Quantity){
        super(Aid,Quantity);
        this.donor = donor;
    }

    /**
     * Getter for donor in the donated aid
     * @return donor who donated for the aid
     */
    public Donor getDonor(){
        return this.donor;
    }


    /**
     * Method called to convert tha donated aid into the csv string
     * Useful in storing the aid into aids.csv file for data
     * @return AllAid with ngo (NULL) ("-"), donor ,aid name and aid quantity
     */
    public AllAid converttoAllAid(){
        return new AllAid(donor,NGO.nullngo(),super.getAidname(),super.getQuantity(),"Available");
    }
}