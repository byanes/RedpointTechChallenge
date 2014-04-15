/**
 * RedpointChallenge --- Program that randomly set pairings of gift givers/receivers.
 * Each person has to be both a gift-giver and gift-receiver and there should
 * be no reciprocal pairings. Also there are no repeated pairings with the 4
 * previous seasons. Just press "enter" to generate a set of pairs and press
 * "q" or "Q" to exit the program.
 * 
 * @author Borja Yanes
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Challenge {
	
    public static void main(String[] args) {
    	
    	String lecture = null; // User's input
    	Boolean keep = true;
    	int count = 0; // Number of set pairings generated 
    	
		// Array with the names that will be used as keys in the map
    	String residents[] = {"Buddy Holly", "John Bonham", "Elvis Presley", "Kurt Cobain",
				"Janis Joplin", "Richie Valens", "John Lennon"};
		
		// Array with the names that will be shuffled and used as values in the map
		String residents2[] = {"Buddy Holly", "John Bonham", "Elvis Presley", "Kurt Cobain",
				"Janis Joplin", "Richie Valens", "John Lennon"};
		
		// Map to store the current set of pairings
		TreeMap<String, String> map = new TreeMap<String,String>();
		
		// Maps to store the pairings of 4 seasons
		TreeMap<String, String> lastYearMap = new TreeMap<String,String>(); 
		TreeMap<String, String> lastYearMap2 = new TreeMap<String,String>();
		TreeMap<String, String> lastYearMap3 = new TreeMap<String,String>();
		TreeMap<String, String> lastYearMap4 = new TreeMap<String,String>();
		
		// Last year pairs
		lastYearMap.put("Buddy Holly", "Elvis Presley");
		lastYearMap.put("Elvis Presley", "Janis Joplin");
		lastYearMap.put("Janis Joplin", "John Lennon");
		lastYearMap.put("John Bonham", "Kurt Cobain");
		lastYearMap.put("John Lennon", "John Bonham");
		lastYearMap.put("Kurt Cobain", "Richie Valens");
		lastYearMap.put("Richie Valens", "Buddy Holly");	
		
		while (keep) {
		
		System.out.println("\nEnter something here: ");
		 
		try { 
		    //BufferReader to accept console input
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    lecture = bufferRead.readLine(); // User's input
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Exit the program if user press 'q' or 'Q'
		if (lecture.equals("q") || lecture.equals("Q")) { 
			keep=false; // Finish the while
			System.exit(0); // Exit program
		}		

		do {
		
			Collections.shuffle(Arrays.asList(residents2)); // Shuffle the array with rock stars names
					
			for (int i=0; i<residents.length; i++) {		
				
				String value = map.get(residents2[i]);
				String value2 = lastYearMap.get(residents[i]); // Get values for each key
				String value3 = lastYearMap2.get(residents[i]);
				String value4 = lastYearMap3.get(residents[i]);
				String value5 = lastYearMap4.get(residents[i]);
				
				// Check if the pair is already stored in the current map or in the last years map
				// and avoid reciprocal pairings
				// Also check if the gift giver and receiver is not the same person
				if ((value != residents[i]) && (residents[i]!=residents2[i]) && (value2 != residents2[i])
					&& (value3 != residents2[i]) && (value4 != residents2[i]) && (value5 != residents2[i])) {
					
					map.put(residents[i], residents2[i]); // Store each pair in the map
				} 
					
			}
			
			// Clear the map if it hasn't been fully filled to try to fill it again
			if (map.size() < residents.length) {
				map.clear();
			}
			
		} while (map.size() != residents.length); // Fill the map until it has the 7 pairs needed
		
		// Output the pairings stored in the map
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey(); // Getting the keys
		    String value = entry.getValue(); // Getting the values
		    System.out.println(key + " is buying to " + value);
		}
		
		// Copy the current pairings to the last years pairings
		switch(count) {
			case 0: lastYearMap2.putAll(map);
					break;
			case 1: lastYearMap3.putAll(map);
					break;
			case 2: lastYearMap4.putAll(map);
					break;
			case 3: lastYearMap.clear();
					lastYearMap.putAll(map);
					break;
		}
		
		map.clear(); // Remove the elements of the map
		count++; // Increment the count
		
		if (count==4) { // Restart the count. Just 4 values needed.
			count=0;
		}
		
		}
	}
}