package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * utility class for handling CSV file operations
 * @author Adrian Zielinski
 */
public class CSVHandler {
    
    /**
     * read data from a CSV file
     * @param filePath the path to the CSV file
     * @return a list of string arrays, each representing a row in the CSV
     */
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> values = new ArrayList<>();
                boolean inQuotes = false;
                int startIndex = 0;
                
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    
                    if (c == '"') {
                        inQuotes = !inQuotes;
                    } else if (c == ',' && !inQuotes) {
                        // found a comma outside quotes - this is a field separator
                        String value = line.substring(startIndex, i);
                        // remove quotes if present
                        if (value.startsWith("\"") && value.endsWith("\"")) {
                            value = value.substring(1, value.length() - 1);
                        }
                        values.add(value);
                        startIndex = i + 1;
                    }
                }
                
                // add the last value
                String lastValue = line.substring(startIndex);
                if (lastValue.startsWith("\"") && lastValue.endsWith("\"")) {
                    lastValue = lastValue.substring(1, lastValue.length() - 1);
                }
                values.add(lastValue);
                
                data.add(values.toArray(new String[0]));
            }
            bufferedReader.close();
        } catch (IOException error) {
            throw new RuntimeException("Error reading CSV file: " + error.getMessage(), error);
        }
        
        return data;
    }
    
    /**
     * write data to a CSV file
     * @param filePath the path to the CSV file
     * @param data a list of string arrays, each representing a row in the CSV
     */
    public static void writeCSV(String filePath, List<String[]> data) {
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    String value = row[i];
                    
                    // if the value contains a comma, quote it
                    if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
                        value = "\"" + value.replace("\"", "\"\"") + "\"";
                    }
                    
                    bufferedWriter.write(value);
                    
                    // add a comma unless it's the last value in the row
                    if (i < row.length - 1) {
                        bufferedWriter.write(",");
                    }
                }
                
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException error) {
            throw new RuntimeException("Error writing CSV file: " + error.getMessage(), error);
        }
    }
}
