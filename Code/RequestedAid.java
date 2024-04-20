/**
 * Used to store the requested aid made by the ngos
 */
public class RequestedAid extends Aid{
    private NGO ngo;

    /**
     * Default Constructor
     */
    RequestedAid(){}

    /**
     * Overloaded Constructor 
     * Call a superclass overloaded constructor to construct Aid inside the RequestedAid (inheritance)
     * @param ngo The NGO that request for aid
     * @param Aid The aid name that is requested
     * @param Quantity The quantity requested // needed
     */
    RequestedAid(NGO ngo,String Aid,int Quantity){
        super(Aid,Quantity);
        this.ngo = ngo;
    }

    /**
     * Getter for ngo in the requested aid
     * @return ngo who requested for the aid
     */
    public NGO getNgo(){
        return this.ngo;
    }

    /**
     * Method called to convert tha requested aid into the csv string
     * Useful in storing the aid into aids.csv file for data
     * @return AllAid with donor (NULL) ("-"), ngo ,aid name and aid quantity
     */
    public AllAid converttoAllAid(){
        return new AllAid(Donor.nulldonor(),ngo,super.getAidname(),super.getQuantity(),"-");
    }
}
