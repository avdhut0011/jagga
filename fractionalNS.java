// import java.util.Arrays;
// import java.util.Comparator;
// import java.util.Scanner;
import java.util.*;
class Item {
    int weight, value;

    public Item(int weight, int value){
        this.weight = weight;
        this.value = value; 
    }
}

public class fractionalNS {
    static double fractionalKnapsack(int capacity, Item[] items) {
        Arrays.sort(items, Comparator.comparingDouble((Item i) -> i.value / (double) i.weight).reversed());

        double totalValue = 0.0;
        int[] selectedWeights = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            if (capacity <= 0) {
                break;
            }

            int currentWeight = Math.min(items[i].weight, capacity);
            selectedWeights[i] = currentWeight;

            totalValue += (currentWeight * (items[i].value / (double) items[i].weight));
            capacity -= currentWeight;
        }

        // System.out.println("Selected items and their weights:");
        // for (int i = 0; i < items.length; i++) {
        //     System.out.println("Item " + (i + 1) + ": Weight = " + selectedWeights[i]);
        // }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter the weight and value of each item:");

        for (int i = 0; i < n; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items[i] = new Item(weight, value);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("Total weight in Fractional Knapsack: " + capacity);
        System.out.println("Maximum value in Fractional Knapsack: " + maxValue);

        scanner.close();
    }
}
