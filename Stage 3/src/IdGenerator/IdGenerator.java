/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IdGenerator;

import java.util.UUID;
/**
 *
 * @author Owner
 */
public class IdGenerator {
    /**
     * Generates a random Passenger ID.
     * The ID starts with "P-" followed by 8 characters from a UUID.
     *
     * @return A unique passenger ID string.
     */
    public static String generatePassengerId() {
        String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "P-" + uniquePart;
    }

    /**
     * Generates a random Admin ID.
     * The ID starts with "A-" followed by 8 characters from a UUID.
     *
     * @return A unique admin ID string.
     */
    public static String generateAdminId() {
        String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "A-" + uniquePart;
    }

    /**
     * Generates a random Driver ID.
     * The ID starts with "D-" followed by 8 characters from a UUID.
     *
     * @return A unique driver ID string.
     */
    public static String generateDriverId() {
        String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "D-" + uniquePart;
    }

}
