package lw03.unguided;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("registrations.txt"));
        Set<String> registered = new HashSet<>();

        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();
            if (id.equals("")) {
                continue;
            }
            registered.add(id); 
        }
        scanner.close();

        scanner = new Scanner(
                Main.class.getResourceAsStream("checkins.txt"));
        Set<String> checkedIn = new HashSet<>();
        List<String> results = new ArrayList<>();
        int rejected = 0;

        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();
            if (id.equals("")) {
                continue;
            }

            if (!registered.contains(id)) {
                results.add(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                results.add(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkedIn.add(id);
                results.add(id + ": Checked in");
            }
        }
        scanner.close();

        System.out.println("===== Event Check-In Results =====");
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}



