package oddities;

import java.util.Scanner;

/**
 * @author Torstein Strømme
 */
class Oddities {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            System.out.print(x);
            if (x % 2 == 0) {
                System.out.println(" is even");
            }
            else {
                System.out.println(" is odd");
            }
        }
    }

}