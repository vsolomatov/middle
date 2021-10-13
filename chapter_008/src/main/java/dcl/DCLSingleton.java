package dcl;

public class DCLSingleton {
    private static DCLSingleton inst;

    public static DCLSingleton instOf() {
        System.out.println("1. inst = " + inst + ", Thread.currentThread().getName() = " + Thread.currentThread().getName());
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        System.out.println("2. inst = " + inst + ", Thread.currentThread().getName() = " + Thread.currentThread().getName());
        return inst;
    }

    private DCLSingleton() {
    }
}
