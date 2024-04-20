import java.util.Comparator;

/**
 * Used as a compartor for queue by comparing the ngo manpower count
 */
public class NGOComparator implements Comparator<NGO> {

    /**
     * Default constructor
     */

    NGOComparator(){};

    /**
     * Method Used to compare the ngo manpower count between ngos
     * @return negative number if the second ngo manpower count is less than first, 0 if both are same , and positive number if the second ngo manpower count is greater than first
     */
    public int compare(NGO N1,NGO N2){
        return N2.getManpower() - N1.getManpower();
    }
}