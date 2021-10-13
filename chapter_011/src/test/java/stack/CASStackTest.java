package stack;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CASStackTest {
    @Test
    public void when3PushThen3Poll() {
        CASStack<Integer> casStack = new CASStack<>();
        casStack.push(1);
        casStack.push(2);
        casStack.push(3);
        assertThat(casStack.poll(), is(3));
        assertThat(casStack.poll(), is(2));
        assertThat(casStack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        CASStack<Integer> casStack = new CASStack<>();
        casStack.push(1);
        assertThat(casStack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        CASStack<Integer> casStack = new CASStack<>();
        casStack.push(1);
        casStack.push(2);
        assertThat(casStack.poll(), is(2));
        assertThat(casStack.poll(), is(1));
    }
}
