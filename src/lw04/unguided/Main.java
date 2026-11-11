package lw04.unguided;

import java.util.Scanner;

public class Main {
    static void bubblePass(int[] A, int i) {
        int n = A.length;
        for (int j = 0; j < n - 1 - i; j++) {
            if (A[j] < A[j + 1]) {
                int temp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = temp;
            }
        }
    }

    static void tampilkan(int[] A) {
        String hasil = "";
        for (int i = 0; i < A.length; i++) {
            if (i > 0) {
                hasil = hasil + " ";
            }
            hasil = hasil + A[i];
        }
        System.out.println(hasil);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        for (int i = 0; i < 2; i++) {
            bubblePass(parcels, i);
        }

        int[] afterTwo = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwo[i] = parcels[i];
        }

        for (int i = 2; i < n - 1; i++) {
            bubblePass(parcels, i);
        }

        System.out.println("==== After 2 Passes ====");
        tampilkan(afterTwo);
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        tampilkan(parcels);
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int t = 0; t < m; t++) {
            int target = targets[t];
            int low = 0;
            int high = n - 1;
            int steps = 0;
            int index = -1;

            while (low <= high && index == -1) {
                int mid = (low + high) / 2;
                steps++;
                if (parcels[mid] == target) {
                    index = mid;
                } else if (parcels[mid] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}