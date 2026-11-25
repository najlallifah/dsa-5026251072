package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String[] baris = read.nextLine().trim().split(" ");

        int target = read.nextInt();

        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < baris.length; i++) {
            int nilai = Integer.parseInt(baris[i]);
            tree.insert(nilai);
        }

        System.out.print("Preorder : ");
        tree.preOrder();

        System.out.print("Inorder : ");
        tree.inOrder();

        System.out.print("Postorder : ");
        tree.postOrder();

        System.out.println("Search " + target + " : " + tree.search(target));

        System.out.println("Nodes : " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());

        read.close();
    }
}