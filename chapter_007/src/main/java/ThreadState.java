public class ThreadState {

    public static void main(String[] args) {

        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println("Before the start " + first.getName() + ": " + first.getState());
        first.start();

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println("Before the start " + second.getName() + ": " + second.getState());
        second.start();

        while ((first.getState() != Thread.State.TERMINATED) || (second.getState() != Thread.State.TERMINATED)) {
            System.out.println(first.getName() + ": " + first.getState());
            System.out.println(second.getName() + ": " + second.getState());
        }

        System.out.println("After the finish " + first.getName() + ": " + first.getState());
        System.out.println("After the finish " + first.getName() + ": " + second.getState());

        System.out.println("Work completed");
    }

}
