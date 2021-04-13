package logic;

import common.ValidationException;
import dal.BloodDonationDAL;
import entity.BloodBank;
import java.util.List;
import java.util.Map;
import entity.BloodDonation;
import entity.BloodGroup;
import entity.RhesusFactor;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jaylen
 */
public class BloodDonationLogic extends GenericLogic<BloodDonation, BloodDonationDAL> {

    public static final String BANK_ID = "bankId";
    public static final String MILLILITERS = "milliliters";
    public static final String BLOOD_GROUP = "blood_group";
    public static final String RHESUS_FACTOR = "rhesus_factor";
    public static final String CREATED = "created";
    public static final String ID = "id";

    BloodDonationLogic() {
        super(new BloodDonationDAL());
    }
    // Hold all the logic iside BloodDonationDAL so any findBy methods 

    @Override
    public List<BloodDonation> getAll() {
        return get(() -> dal().findAll());
    }
    // this method will get any sort of logic from BloofDonation and calls with lamda
    // The find all method which is located insdie BloodDonationDAL

    @Override
    public BloodDonation getWithId(int id) {
        return get(() -> dal().findById(id));
    }
    //returns the ID inside bloodDonation using the embeded query 
    //SELECT b FROM BloodDonation b WHERE b.donationId = :donationId

    public List<BloodDonation> getByMilliliters(int mL) {
        return get(() -> dal().findByMilliliters(mL));
    }
    //returns the mL inside bloodDonation using the embeded query 
    //SSELECT b FROM BloodDonation b WHERE b.milliliters = :milliliters

    public List<BloodDonation> getByBloodGroup(String blood_group) {
        return get(() -> dal().findByBloodGroup(blood_group));
    }
    //returns the bloodGroup inside bloodDonation using the embeded query 
    //SELECT b FROM BloodDonation b WHERE b.bloodGroup = :bloodGroup

    public List<BloodDonation> getByDate(Date date) {
        return get(() -> dal().findByCreated(date));
    }
    //returns the date inside bloodDonation using the embeded query 
    //SELECT b FROM BloodDonation b WHERE b.created = :created
    public List<BloodDonation> getByRHD(String rhd) {
        return get(() -> dal().findByRhd(rhd));
    }
    //returns the Rhesus Factor inside bloodDonation using the embeded query 
    //SELECT b FROM BloodDonation b WHERE b.rhd = :rhd

    public List<BloodDonation> getBloodDonationsWithBloodBank(int bankId) {
        return get(() -> dal().findByBloodBank(bankId));
    }
    //returns the bloodBank id inside bloodDonation using the embeded query 
    //SELECT b FROM BloodDonation b WHERE b.bloodBank.bankId = :bloodBankId

    @Override
    public BloodDonation createEntity(Map<String, String[]> parameterMap) {

        Objects.requireNonNull(parameterMap, "parameterMap cannot be null");

        BloodDonation entity = new BloodDonation();
        //Create the bloodDonation entity 
        if (parameterMap.containsKey(ID)) {
            try {
                entity.setId(Integer.parseInt(parameterMap.get(ID)[0]));
            } catch (java.lang.NumberFormatException ex) {
                throw new ValidationException(ex);
            }
        }
        //Looks for the key being ID that references BloodDonation 

        String mill = parameterMap.get(MILLILITERS)[0];
        String group = parameterMap.get(BLOOD_GROUP)[0];
        String rhd = parameterMap.get(RHESUS_FACTOR)[0];
        String bankID = parameterMap.get(BANK_ID)[0];
        String id = parameterMap.get(ID)[0];
        String date = parameterMap.get(CREATED)[0];
        //Creates a map that hold each of the information inside each search query
        date = date.replace("T", " ");

        entity.setBloodBank(BloodBank.valueOf(id));
        entity.setBloodBank(BloodBank.valueOf(bankID));
        entity.setMilliliters(Integer.valueOf(mill));
        entity.setBloodGroup(BloodGroup.valueOf(group));
        entity.setRhd(RhesusFactor.getRhesusFactor(rhd));
        entity.setCreated(convertStringToDate(date));
        return entity;
        //returns one giant entity that hold each value that in declared inside
        //Blood Donation 
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("ID", "BankID", "Milliliters", "Blood Group", "Rhesus Factor", "Created");
    }
    // returns the column names in an Array list 

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, BANK_ID, MILLILITERS, BLOOD_GROUP, RHESUS_FACTOR, CREATED);
    }
    // Grabs each column name in array form and make sure it is lined up correctly with the constuctor
    //So it is outputed correctly 

    @Override
    public List<?> extractDataAsList(BloodDonation e) {
        return Arrays.asList(e.getId(), e.getBloodBank().getId(), e.getMilliliters(), e.getBloodGroup(), e.getRhd(), e.getCreated());
    }
    // grabs each of the data inside the blood donation

}
