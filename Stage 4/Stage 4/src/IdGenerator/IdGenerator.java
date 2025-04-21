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
}
