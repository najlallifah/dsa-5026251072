package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static String gabungKata(String[] kata, int start) {
        String hasil = "";
        for (int i = start; i < kata.length; i++) {
            if (i > start) {
                hasil = hasil + " ";
            }
            hasil = hasil + kata[i];
        }
        return hasil;
    }

    static void problem1() {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                continue;
            }

            String[] kata = line.split(" ");
            String operasi = kata[0];

            if (operasi.equals("ADD")) {
                String song = gabungKata(kata, 1);
                playlist.add(song);
            } else if (operasi.equals("INSERT")) {
                int index = Integer.parseInt(kata[1]);
                String song = gabungKata(kata, 2);
                playlist.add(index, song);
            } else if (operasi.equals("REMOVE")) {
                String song = gabungKata(kata, 1);
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("participants.txt"));
        Set<String> peserta = new LinkedHashSet<>();
        int duplikat = 0;

        while (scanner.hasNextLine()) {
            String nama = scanner.nextLine();
            if (nama.equals("")) {
                continue;
            }

            if (peserta.contains(nama)) {
                duplikat++;
            } else {
                peserta.add(nama);
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + peserta.size());
        int nomor = 1;
        for (String nama : peserta) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
        System.out.println("Duplicate registrations: " + duplikat);
    }

    static void problem3() {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stok = new LinkedHashMap<>();
        int gagal = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                continue;
            }

            String[] kata = line.split(" ");
            String tipe = kata[0];
            String produk = kata[1];
            int jumlah = Integer.parseInt(kata[2]);

            if (tipe.equals("ADD")) {
                if (stok.containsKey(produk)) {
                    stok.put(produk, stok.get(produk) + jumlah);
                } else {
                    stok.put(produk, jumlah);
                }
            } else if (tipe.equals("SELL")) {
                if (stok.containsKey(produk) && stok.get(produk) >= jumlah) {
                    stok.put(produk, stok.get(produk) - jumlah);
                } else {
                    gagal++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (String produk : stok.keySet()) {
            System.out.println(produk + ": " + stok.get(produk));
        }
        System.out.println("Failed sales: " + gagal);
    }

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }
}
