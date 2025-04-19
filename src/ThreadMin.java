public class ThreadMin extends Thread{
    private final int startIndex;
    private final int finishIndex;
    private final MyArray arrClass;

    public ThreadMin(int startIndex, int finishIndex, MyArray arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }
    @Override
    public void run() {
//        System.out.println("Begin => " + startIndex + "-" + finishIndex);

        int[] min = arrClass.partMin(startIndex, finishIndex);
        arrClass.compareMin(min);
        arrClass.incThreadCount();
//        System.out.println("End => " + startIndex + "-" + finishIndex);

    }




}
