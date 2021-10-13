package email.notification;

import org.junit.Test;

import java.util.concurrent.ExecutorService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailNotificationTest {

    @Test
    public void whenNotificatedUsersAndClosed() {
        EmailNotification emailNotification = new EmailNotification();

        User user1 = new User("Vyacheslav", "solomatoff.vyacheslav@yandex.ru");
        User user2 = new User("Viacheslav", "vv.solomatov@yandex.ru");
        User user3 = new User("Hotmail", "vv.solomatov@hotmail.com");

        emailNotification.emailTo(user1);
        emailNotification.emailTo(user2);
        emailNotification.emailTo(user3);

        emailNotification.close();

        ExecutorService pool = emailNotification.getPool();
        assertThat(pool.isShutdown(), is(true));

        System.out.println("Executed " + Thread.currentThread().getName());
    }

}
