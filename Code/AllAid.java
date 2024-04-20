/**
 * Used to store all the aid data from aids.csv file
 * Including matched and unmatched aids
 */
public class AllAid extends Aid{
    private Donor donor;
    private NGO ngo;

    /**
     * Default constructor
     */
    AllAid(){}

    /**
     * Overloaded constructor
     * @param donor the donor that donates the aid
     * @param ngo the ngo that request for the aid
     * @param Aids the name of the aid
     * @param Quantity the quantity of the aid before / after aid distribution
     */
    AllAid(Donor donor,NGO ngo,String Aids,int Quantity,String status){
        super(Aids,Quantity,status);
        this.donor = donor;
        this.ngo = ngo;
    }

    /**
     * Getter for the donor name that donated the aid
     * @return the name of the donor
     */
    public String getDonorname(){
        return donor.getDonorname();
    }

    /**
     * Getter for the donor phone number that donated for aid
     * @return the phone number of the donor
     */
    public String getPhonenum(){
        return donor.getPhonenum();
    }

    /**
     * Getter for the ngo name that requested for aid
     * @return the name of the ngo
     */
    public String getNgoname(){
        return ngo.getNgoname();
    }

    /**
     * Getter for the manpower from the ngo that requested for aid
     * @return manpower of the ngo
     */
    public Integer getManpower(){
        return ngo.getManpower();
    }

    /**
     * Method to return a csv string consists of donor details and ngo details together with aid details
     * @return the string representation of the csv of all the aids no matter before or after matching
     */
    public String toCSVString(){
        return donor.getUsername() + "," + donor.getDonorname() + "," + donor.getPhonenum() + "," +
                ngo.getUsername() + "," + ngo.getNgoname() + "," + ngo.getManpower() + "," + 
                super.getAidname() + "," + super.getAidqty() + "," + super.getAidstatus();
    }

    /**
     * Method produced compilled from donorempty and ngoempty
     * Used to determine which aid havent been matched in DC functions
     * @return false if the ngo or ngo is not empty//null,true if the ngo or donor is empty
     */
    public boolean donorORngoempty(){
        if(donorempty() || ngoempty())
            return true;
        return false;
    }

    /**
     * Method used to check if the donor is empty//null or filled
     * "-" meaning that the donated aid has not be assigned yet with the requested aid
     * @return true if the donor is empty//null,false if the donor is not empty//null
     */
    public boolean donorempty(){
        if(donor.getDonorname().equals("-"))
            return true;
    return false;
    }

    /**
     * Method used to check if the ngo is empty//null or filled
     * "-" meaning that the requested aid has not be assigned yet with the donor aid
     * @return true if the ngo is empty//null,false if the ngo is not empty//null
     */
    public boolean ngoempty(){
        if(ngo.getNgoname().equals("-"))
            return true;
        return false;
    }
}
