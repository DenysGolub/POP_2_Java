import java.util.Random;

public class MyArray {

    private final int dim;
    private final int threadNum;
    private final int[] arr;
    int minIndexTest = 914581;

    public MyArray(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;

        Random rnd = new Random();

        for (int i = 0; i < dim; i++) {
            arr[i] = rnd.nextInt(0, dim);
        }


        arr[minIndexTest] = -1;
    }

    public int[] partMin(int startIndex, int finishIndex) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = startIndex; i < finishIndex; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return new int[] {min, minIndex};
    }

    private int min = Integer.MAX_VALUE;
    private int minIndexThread = 0;

    synchronized private int[] getMin() {
        while (getThreadCount() < threadNum) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new int[] {min, minIndexThread};
    }

    private int getThreadCount() {
        return threadCount;
    }

    private int threadCount = 0;

    synchronized public void incThreadCount() {
        threadCount++;
        notify();
    }

    public int[] threadMin() {
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int basePartition = dim / threadNum;
        int equalParts = threadNum;

        int remainingSize = dim % threadNum;
        int index = 0;
        int i = 0;

        for (; i < equalParts; i++) {
            int sIndex = index;
            int fIndex = index + basePartition;
            threadMins[i] = new ThreadMin(sIndex, fIndex, this);
            threadMins[i].start();
            index += basePartition;
        }
        i--;
        if (remainingSize > 0) {
            threadMins[i] = new ThreadMin(dim - remainingSize, dim - 1, this);
            threadMins[i].start();
        }


        return getMin();
    }

    synchronized public void compareMin(int[] min) {
        if(min[0] < this.min) {
            this.min = min[0];
            this.minIndexThread = min[1];
        }
    }

}
