package lw04.prelab;

import java.util.Scanner;
 
public class Main {
 
    static void insertionSort(int[] A, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = A[i];
            int j = i - 1;
            
            while (j >= 0 && A[j] < key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }
 
    
    static int linearSearch(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("branches.txt"));
 
        int n = Integer.parseInt(scanner.nextLine().trim());
 
        String[] barisBranch = scanner.nextLine().trim().split(" ");
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = Integer.parseInt(barisBranch[i]);
        }
 
        int m = Integer.parseInt(scanner.nextLine().trim());
 
        String[] barisTarget = scanner.nextLine().trim().split(" ");
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(barisTarget[i]);
        }
        scanner.close();
 
        insertionSort(branches, 3);
 
        String hasil = "";
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                hasil = hasil + " ";
            }
            hasil = hasil + branches[i];
        }
        System.out.println(hasil);
 
        for (int i = 0; i < m; i++) {
            int index = linearSearch(branches, targets[i]);
            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}
 