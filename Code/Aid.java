/**
 * Stores aid name, aid quantity and status
 */
public class Aid {
    private String aid;
    private Integer quantity;
    private String status;

    /**
     * Default constructor
     */
    Aid(){}

    /**
     * Overloaded constructor for constructing 
     * @param aid the name of the aid donated or requested
     * @param quantity the quantity of the aid donated or requested
     * @param status the status of aid (Reserved / Available / Completed)
     */
    Aid(String aid,Integer quantity,String status){
        this.aid = aid.toUpperCase();
        this.quantity = quantity;
        this.status = status;
    }

    /**
     * Overloaded constructor
     * @param aid the name of the aid donated or requested
     * @param quantity the quantity of the aid donated or requested
     */
    Aid(String aid,Integer quantity){
        this.aid = aid.toUpperCase();
        this.quantity = quantity;
    }

    /**
     * Getter for the name of aid
     * @return name of aid donated // requested
     */
    public String getAidname(){
        return this.aid;
    }

    /**
     * Getter for the quantity of aid
     * Used to display the quantity in string in the tableview of javafx
     * @return Integer of quantity of aid donated // requested
     */
    public Integer getAidqty(){
        return this.quantity;
    }

    /**
     * Getter for the quantity of aid
     * @return quantity of aid donated // requested
     */
    public Integer getQuantity(){
        return this.quantity;
    }

    /**
     * Getter for the status of aid
     * @return status of aid donated // requested
     */
    public String getAidstatus(){
        return this.status;
    }

    /**
     * Setter for the quantity of aid
     * Act as the modifier to modify the private data field (this.quantity)
     * @param quantity new quantity of aid
     */
    public void setqty(Integer quantity){
        this.quantity = quantity;
    }

    /**
     * Setter for the status of aid
     * Act as the modifier to modify the private data field (this.status)
     * @param sts new status of aid
     */
    public void setaidstatus(String sts){
        this.status = sts;
    }
}
