package atomic.reference;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AtomicReferenceTest {
    @Test
    public void when3IncrementThen3() {
        System.out.println("Started AtomicReferenceTest.when3IncrementThen3");
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(3));
        System.out.println("    casCount.get() = " + casCount.get());
    }

    @Test
    public void when1IncrementThen1() {
        System.out.println("Started AtomicReferenceTest.when1IncrementThen1");
        CASCount casCount = new CASCount();
        casCount.increment();
        assertThat(casCount.get(), is(1));
        System.out.println("    casCount.get() = " + casCount.get());
    }

    @Test
    public void when2IncrementThen2() {
        System.out.println("Started AtomicReferenceTest.when2IncrementThen2");
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(2));
        System.out.println("    casCount.get() = " + casCount.get());
    }
}
