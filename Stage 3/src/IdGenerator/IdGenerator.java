package idGenerator;
/**
 *
 * @author Owner
 */
public class IdGenerator {
    private static Integer pID = 1000;
    private static Integer aID = 1000;
    private static Integer dID = 1000;
    private static Integer rID = 1000;
    
    /**
     * 
     * Generates a random Passenger ID.
     * The ID starts with "P-" followed by 8 characters from a UUID.
     *
     * @return A unique passenger ID string.
     */
    public static String generatePassengerId() {
        
        String newID =  "P-" + pID.toString();
        pID++;
        return newID;

    }

    /**
     * Generates a random Admin ID.
     * The ID starts with "A-" followed by 8 characters from a UUID.
     *
     * @return A unique admin ID string.
     */
    public static String generateAdminId() {
        String newID =  "A-" + aID.toString();
        aID++;
        return newID;
    }

    /**
     * Generates a random Driver ID.
     * The ID starts with "D-" followed by 8 characters from a UUID.
     *
     * @return A unique driver ID string.
     */
    public static String generateDriverId() {
        String newID =  "D-" + dID.toString();
        dID++;
        return newID;
    }

    /**
     * Generates a random Route ID.
     * The ID starts with "R-" followed by 8 characters from a UUID.
     *
     * @return A unique route ID string.
     */
    public static String generateRouteId() {
        String newID =  "R-" + rID.toString();
        rID++;
        return newID;
    }

}
