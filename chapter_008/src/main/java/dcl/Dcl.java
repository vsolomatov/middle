package dcl;

public class Dcl {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(DCLSingleton::instOf);
        Thread second = new Thread(DCLSingleton::instOf);
        first.start();
        //Thread.sleep(3000);
        second.start();
        first.join();
        second.join();
        System.out.println("dcl.DCLSingleton.instOf() = " + DCLSingleton.instOf());
    }
}
