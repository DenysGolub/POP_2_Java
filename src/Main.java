
//ЛАБА БЕЗ JOIN
public class Main {
    public static void main(String[] args) {
        int[] threadsNumbers = new int[]{4, 8, 16, 32};

        for (int i = 0; i < threadsNumbers.length; i++) {
            int threadsNumber = threadsNumbers[i];
            System.out.println(String.format("Number of threads: %s", threadsNumber));
            int dim =500_000_000;


            MyArray myArray = new MyArray(dim, threadsNumber);
            long start = System.currentTimeMillis();
            int[] min = myArray.threadMin();
            long end = System.currentTimeMillis();
            System.out.println(String.format("Min=%s\nIndex=%s", min[0], min[1]));
            System.out.println(String.format("Elapsed Time: %s", end - start));
            System.out.println("------");
        }

    }
}