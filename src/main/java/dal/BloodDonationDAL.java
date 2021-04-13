package dal;

import entity.BloodDonation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jayle
 */
public class BloodDonationDAL extends GenericDAL<BloodDonation> {

    public BloodDonationDAL() {
        super(BloodDonation.class);
    }

    /*
    Constructor that holds all the values refering to the bloodDonation class
    Like id,milliliters
     */

    @Override
    public List<BloodDonation> findAll() {
        return findResults("BloodDonation.findAll", null);
    }

    /*
    This method returns the findResults method inside BloodDonation. In side 
    blood donation is where to find the search queary findAll inside BloodDonation 
     */
    @Override
    public BloodDonation findById(int donationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("donationId", donationId);
        return findResult("BloodDonation.findByDonationId", map);
    }

    /*
    This method returns the findResults method inside BloodDonation. Inside 
    blood donation is where to find the search queary findByDonationId inside 
    BloodDonation This returns a map it hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */
    public List<BloodDonation> findByMilliliters(int mL) {
        Map<String, Object> map = new HashMap<>();
        map.put("milliliters", mL);
        return findResults("BloodDonation.findByMilliliters", map);
    }

    /*
    Inside 
    blood donation is where to find the search queary findByMilliliters inside 
    BloodDonation This returns a map it hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */
    public List<BloodDonation> findByBloodGroup(String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("bloodGroup", type);
        return findResults("BloodDonation.findByBloodGroup", map);
    }
    /*
    Returns findresults which holds the search queary and a hasMap
    blood donation is where to find the search queary findByBloodGroup inside 
    BloodDonation This returns a map it hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */

    public List<BloodDonation> findByBloodBank(int bank_ID) {
        Map<String, Object> map = new HashMap<>();
        map.put("bloodBankId", bank_ID);
        return findResults("BloodDonation.findByBloodBank", map);
    }
    /*
     Returns findresults which holds the search queary and a hasMap
    blood donation is where to find the search queary findByBloodBank inside 
    BloodDonation This returns a map it hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */
    

    public List<BloodDonation> findByRhd(String rhd) {
        Map<String, Object> map = new HashMap<>();
        map.put("rhd", rhd);
        return findResults("BloodDonation.findByRhd", map);
    }
    /*
     Returns findresults which holds the search queary and a hasMap
    blood donation is where to find the search queary findByRhd inside 
    BloodDonation This returns a map it hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */

    public List<BloodDonation> findByCreated(Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("BloodDonation.findByCreated", date);
        return findResults("BloodDonation.findByCreated", map);
    }
    /*
     Returns findresults which holds the search queary and a hasMap
    blood donation is where to find the search queary findByCreated inside 
    BloodDonation This returns a map that hold the Date the 
    created holdsit hold key values inside in this case its
    a string and an Object being the bloodDonation variables   
     */
}
