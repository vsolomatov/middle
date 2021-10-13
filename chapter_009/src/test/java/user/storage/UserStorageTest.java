package user.storage;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private final UserStorage userStorage = new UserStorage();

    /**
     * Нить для добавления пользователя в хранилище
     */
    private class ThreadAddUser extends Thread {
        private final User user;

        private ThreadAddUser(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            userStorage.add(this.user);
        }
    }

    /**
     * Нить для удаления пользователя из хранилища
     */
    private class ThreadDeleteUser extends Thread {
        private final User user;

        private ThreadDeleteUser(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            userStorage.delete(this.user);
        }
    }

    /**
     * Нить для денежных переводов
     */
    private class ThreadTransfer extends Thread {
        private final int userFromId;
        private final int userToId;
        private final int amount;

        private ThreadTransfer(int userFromId, int userToId, int amount) {
            this.userFromId = userFromId;
            this.userToId = userToId;
            this.amount = amount;
        }

        @Override
        public void run() {
            userStorage.transfer(userFromId, userToId, amount);
        }
    }

    @Test
    public void whenAdd3UserThenId3() throws InterruptedException {
        System.out.println("Started CountTest.whenAdd3UserThenId3");

        //Создаем пользователей и нити для их добавления в хранилище
        User user1 = new User(100);
        Thread first = new ThreadAddUser(user1);
        User user2 = new User(200);
        Thread second = new ThreadAddUser(user2);
        User user3 = new User(0);
        Thread third = new ThreadAddUser(user3);

        //Запускаем нити.
        first.start();
        second.start();
        third.start();

        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();

        //Проверяем результат.
        assertThat(userStorage.getId().intValue(), is(3));
        for (User user : userStorage.findAll()) {
            System.out.println("user = " + user);
        }
        System.out.println("Finished CountTest.whenAdd3UserThenId3");
    }

    @Test
    public void whenAdd3AndDelete() throws InterruptedException {
        System.out.println("Started CountTest.whenAdd3AndDelete");

        //Создаем пользователей и нити для их добавления в хранилище
        User user1 = new User(100);
        Thread first = new ThreadAddUser(user1);
        User user2 = new User(200);
        Thread second = new ThreadAddUser(user2);
        User user3 = new User(0);
        Thread third = new ThreadAddUser(user3);

        //Запускаем нити.
        first.start();
        second.start();
        third.start();

        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();

        //Проверяем созданных пользователей
        for (User user : userStorage.findAll()) {
            System.out.println("user = " + user);
        }

        //Создаем нити для удаления пользователей из хранилища
        Thread firstDelete = null;
        Thread secondDelete = null;
        Thread thirdDelete = null;
        for (User user : userStorage.findAll()) {
            switch (user.getId()) {
                case 1:
                    firstDelete = new ThreadDeleteUser(user);
                    break;
                case 2:
                    secondDelete = new ThreadDeleteUser(user);
                    break;
                case 3:
                    thirdDelete = new ThreadDeleteUser(user);
                    break;
                default:
            }
        }
        //Запускаем две из трех нитей
        Objects.requireNonNull(firstDelete).start();
        Objects.requireNonNull(thirdDelete).start();

        //Заставляем главную нить дождаться выполнения наших нитей.
        firstDelete.join();
        thirdDelete.join();

        //Проверяем результат.
        assertThat(userStorage.findAll().size(), is(1));
        for (User user : userStorage.findAll()) {
            System.out.println("* user = " + user);
        }
        // Запускаем третью нить
        Objects.requireNonNull(secondDelete).start();
        //Заставляем главную нить дождаться ее выполнения
        secondDelete.join();

        //Проверяем результат.
        assertThat(userStorage.findAll().size(), is(0));
        System.out.println("userStorage.findAll().size() = " + userStorage.findAll().size());
        System.out.println("Finished CountTest.whenAdd3AndDelete");
    }

    @Test
    public void whenTransfer() throws InterruptedException {
        System.out.println("Started CountTest.whenTransfer");

        //Создаем пользователей и нити для их добавления в хранилище
        User user1 = new User(100);
        Thread first = new ThreadAddUser(user1);
        User user2 = new User(200);
        Thread second = new ThreadAddUser(user2);
        User user3 = new User(0);
        Thread third = new ThreadAddUser(user3);

        //Запускаем нити.
        first.start();
        second.start();
        third.start();

        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();

        for (User user : userStorage.findAll()) {
            System.out.println("user = " + user);
        }

        // Создаем нити для переводов денежных средств
        Thread firstTransfer = new ThreadTransfer(1, 2, 60);
        Thread secondTransfer = new ThreadTransfer(2, 3, 260);

        //Запускаем нити
        secondTransfer.start();
        firstTransfer.start();

        //Заставляем главную нить дождаться выполнения наших нитей.
        firstTransfer.join();
        secondTransfer.join();

        //Смотрим на результат
        for (User user : userStorage.findAll()) {
            System.out.println("** user = " + user);
        }
        System.out.println("Finished CountTest.whenTransfer");
    }
}
