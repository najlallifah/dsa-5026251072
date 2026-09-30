package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File("src/lw02/prelab/transactions.txt"))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                String type = scanner.next();
                String amount = scanner.next();
                transactions.add(new String[] { name, type, amount });
            }
        } catch (FileNotFoundException e) {
            System.err.println("transactions.txt not found");
            return;
        }

        for (String[] t : transactions) {
            String name = t[0];
            if (!customerExists(customers, name)) {
                customers.add(new String[] { name, "0" });
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        Stack<String[]> failedStack = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedStack.push(t);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }

    private static boolean customerExists(LinkedList<String[]> customers, String name) {
        for (String[] c : customers) {
            if (c[0].equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] c : customers) {
            if (c[0].equals(name)) {
                return c;
            }
        }
        return null;
    }
}


//Scanner scanner = new Scanner{
    //Main.class.getResourceAsStream("transactions.txt");
//}

// if (customer == null){
//     customer = new String[] {name, "0"};

// }
