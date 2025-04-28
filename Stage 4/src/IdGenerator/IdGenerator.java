package IdGenerator;

/**
 * A utility class for generating unique IDs for different entities in the system.
 * 
 * Author: Handsome Onojerame
 */
public class IdGenerator {
    private static Integer pID = 1000; // Passenger ID counter
    private static Integer aID = 1000; // Admin ID counter
    private static Integer dID = 1000; // Driver ID counter
    private static Integer rID = 1000; // Route ID counter

    /**
     * Generates a unique Passenger ID.
     * The ID starts with "P-" followed by an incrementing number.
     *
     * @return A unique passenger ID string.
     */
    public static String generatePassengerId() {
        String newID = "P-" + pID.toString();
        pID++;
        return newID;
    }
    
    /**
     * updates the passenger ID counter if the provided ID is higher than the current counter
     * used when loading passengers from CSV to ensure new IDs don't conflict
     * 
     * @param idNumber the ID number to check against the current counter
     */
    public static void updatePassengerIdIfHigher(int idNumber) {
        if (idNumber >= pID) {
            pID = idNumber + 1;
        }
    }

    /**
     * Generates a unique Admin ID.
     * The ID starts with "A-" followed by an incrementing number.
     *
     * @return A unique admin ID string.
     */
    public static String generateAdminId() {
        String newID = "A-" + aID.toString();
        aID++;
        return newID;
    }
    
    /**
     * updates the admin ID counter if the provided ID is higher than the current counter
     * used when loading admin employees from CSV to ensure new IDs don't conflict
     * @param idNumber the ID number to check against the current counter
     */
    public static void updateAdminIdIfHigher(int idNumber) {
        if (idNumber >= aID) {
            aID = idNumber + 1;
        }
    }

    /**
     * Generates a unique Driver ID.
     * The ID starts with "D-" followed by an incrementing number.
     *
     * @return A unique driver ID string.
     */
    public static String generateDriverId() {
        String newID = "D-" + dID.toString();
        dID++;
        return newID;
    }
    
    /**
     * updates the driver ID counter if the provided ID is higher than the current counter
     * used when loading driver employees from CSV to ensure new IDs don't conflict
     * @param idNumber the ID number to check against the current counter
     */
    public static void updateDriverIdIfHigher(int idNumber) {
        if (idNumber >= dID) {
            dID = idNumber + 1;
        }
    }

    /**
     * Generates a unique Route ID.
     * The ID starts with "R-" followed by an incrementing number.
     *
     * @return A unique route ID string.
     */
    public static String generateRouteId() {
        String newID = "R-" + rID.toString();
        rID++;
        return newID;
    }
    
    /**
     * updates the route ID counter if the provided ID is higher than the current counter
     * used when loading routes from CSV to ensure new IDs don't conflict.
     * @param idNumber the ID number to check against the current counter
     */
    public static void updateRouteIdIfHigher(int idNumber) {
        if (idNumber >= rID) {
            rID = idNumber + 1;
        }
    }
}
