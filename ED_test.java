import java.util.Scanner;

public class ED_test {

    public static void ED(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] table = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) { // Creating the table
            for (int j = 0; j <= n; j++) {
                if (i == 0)  table[i][j] = j;
                else if (j == 0)  table[i][j] = i;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))table[i][j] = table[i - 1][j - 1];
                else table[i][j] = 1 + Math.min(table[i][j - 1], Math.min(table[i - 1][j], table[i - 1][j - 1])); //Finding the lowest outcome
            }
        }

        System.out.println("\nMinimum number of edits (operations) required to convert " + s1 + " into " + s2 + " : " + table[m][n]);

        int i = m, j = n; int count = 0;
        int replace_cost = 0, insert_cost = 0, remove_cost = 0;
        
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // Count is not incremented as no operation is executed
                i--;
                j--;
            } else if (table[i][j - 1] < table[i - 1][j] && table[i][j - 1] < table[i - 1][j - 1]) {  
                j--;
                insert_cost+=1;   //Count is incremented by 1 for inserting
            } else if (table[i - 1][j] < table[i][j - 1] && table[i - 1][j] < table[i - 1][j - 1]) {    
                i--;
                remove_cost+=1;   //Count is incremented by 1 for removing
            } else { 
                i--;
                j--;
                replace_cost+=2;   //Count is incremented by 2 for replacement
            }
        }
        System.out.println("\nThe Insertion Cost :"+ insert_cost +",\nThe Removal Cost :" + remove_cost +",\nThe Deletion Cost :" + replace_cost );
        count = insert_cost + remove_cost + replace_cost;
        System.out.println("\nThe total cost to convert \"" + s1 + "\" into \"" + s2 + "\":" + count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string1: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter string2: ");
        String s2 = scanner.nextLine();
        scanner.close();
        ED(s1, s2);
    }
}
