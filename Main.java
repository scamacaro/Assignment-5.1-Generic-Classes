/*
 Sanyerlis Camacaro - Sanyerliscamacaro@uat.edu - CSC263
Assignment 5.1: Generic Classes

Overview
This assignment will help you to implement generic classes and methods.

Guidelines
Based on your knowledge of Trees, give an implementation of the Generic Binary Search Tree Class.

About Code:
This Java program implements an interactive Binary Search Tree (BST) application.
The program prompts the user to input their name and provides a friendly introduction to the
BST application. Users can interactively perform various operations on the BST, such as inserting a new element,
displaying the elements in ascending order through in-order traversal, searching for an element, deleting an element,
and exiting the program.

The user-friendly menu system guides users through each option, making it easy to build, manipulate, and explore the
Binary Search Tree in a step-by-step manner. The program continues to execute until the user chooses to exit,
providing a seamless and engaging experience for working with Binary Search Trees.
 */
// Importing Scanner class for user input
import java.util.Scanner;

// Generic class for Binary Search Tree
class TreeNode<T extends Comparable<T>> {
    T data;
    TreeNode<T> left, right;

    // Constructor to initialize a tree node with given data
    public TreeNode(T item) {
        data = item;
        left = right = null;
    }
}

public class Main<T extends Comparable<T>> {
    private TreeNode<T> root;

    // Constructor to initialize the Binary Search Tree with a null root
    public Main() {
        root = null;
    }

    // Method to insert a new element into the BST
    public void insert(T data) {
        root = insertRec(root, data);
    }

    // Recursive method to insert a new element into the BST
    private TreeNode<T> insertRec(TreeNode<T> root, T data) {
        if (root == null) {
            // Create a new node if the current node is null
            root = new TreeNode<>(data);
            return root;
        }

        if (data.compareTo(root.data) < 0) {
            // Recursively insert into the left subtree if data is smaller
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            // Recursively insert into the right subtree if data is larger
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Method to perform an in-order traversal of the BST
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    // Recursive method to perform an in-order traversal of the BST
    private void inOrderTraversal(TreeNode<T> root) {
        if (root != null) {
            // Traverse the left subtree
            inOrderTraversal(root.left);
            // Print the current node's data
            System.out.print(root.data + " ");
            // Traverse the right subtree
            inOrderTraversal(root.right);
        }
    }

    // Method to search for an element in the BST
    public boolean search(T data) {
        return searchRec(root, data);
    }

    // Recursive method to search for an element in the BST
    private boolean searchRec(TreeNode<T> root, T data) {
        if (root == null) {
            // Element not found if the current node is null
            return false;
        }

        if (data.compareTo(root.data) == 0) {
            // Element found if it matches the current node's data
            return true;
        } else if (data.compareTo(root.data) < 0) {
            // Recursively search in the left subtree if data is smaller
            return searchRec(root.left, data);
        } else {
            // Recursively search in the right subtree if data is larger
            return searchRec(root.right, data);
        }
    }

    // Method to delete an element from the BST
    public void delete(T data) {
        root = deleteRec(root, data);
    }

    // Recursive method to delete an element from the BST
    private TreeNode<T> deleteRec(TreeNode<T> root, T data) {
        if (root == null) {
            // Return null if the current node is null (base case)
            return root;
        }

        if (data.compareTo(root.data) < 0) {
            // Recursively delete from the left subtree if data is smaller
            root.left = deleteRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            // Recursively delete from the right subtree if data is larger
            root.right = deleteRec(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children, get the inorder successor
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // Method to find the minimum value in a subtree
    private T minValue(TreeNode<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            // Traverse to the leftmost leaf to find the minimum value
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message and prompt for the user's name
        System.out.println("Welcome to the Interactive Binary Search Tree!");
        System.out.println("\nThis program allows you to interactively build, manipulate, and explore a Binary Search Tree (BST).");
        System.out.println("\nA BST is a data structure where each node has at most two children, with elements arranged in a specific order.");
        System.out.println("\nLet's get started!");
        System.out.print("\nPlease enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("\nHello, " + userName + "! Let's work with a Binary Search Tree.");

        // Create a Binary Search Tree of Integer type
        Main<Integer> bst = new Main<>();

        // Interactive menu for the user to perform operations on the BST
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("\n1. Insert an element: Add a new element to the Binary Search Tree.");
            System.out.println("   Example: Enter '42' to insert the element 42.");
            System.out.println("2. In-order traversal: Display elements in ascending order.");
            System.out.println("3. Search for an element: Check if an element is present in the Binary Search Tree.");
            System.out.println("   Example: Enter '23' to search for the element 23.");
            System.out.println("4. Delete an element: Remove an element from the Binary Search Tree.");
            System.out.println("   Example: Enter '15' to delete the element 15.");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Insert an element into the BST
                    System.out.print("Enter the element to insert: ");
                    int elementToInsert = scanner.nextInt();
                    bst.insert(elementToInsert);
                    System.out.println(elementToInsert + " inserted into the BST.");
                    break;
                case 2:
                    // Display in-order traversal of the BST
                    System.out.println("In-order traversal of the BST:");
                    bst.inOrderTraversal();
                    break;
                case 3:
                    // Search for an element in the BST
                    System.out.print("Enter the element to search: ");
                    int elementToSearch = scanner.nextInt();
                    System.out.println("Is " + elementToSearch + " present in the BST? " + bst.search(elementToSearch));
                    break;
                case 4:
                    // Delete an element from the BST
                    System.out.print("Enter the element to delete: ");
                    int elementToDelete = scanner.nextInt();
                    bst.delete(elementToDelete);
                    System.out.println(elementToDelete + " deleted from the BST.");
                    System.out.println("In-order traversal after deletion:");
                    bst.inOrderTraversal();
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Exiting the program. Goodbye, " + userName + "!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    // Invalid choice message
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
