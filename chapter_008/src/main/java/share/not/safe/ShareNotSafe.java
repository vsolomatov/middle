package share.not.safe;

import java.util.List;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename1");
                }
        );
        first.start();
        first.join();

        System.out.println(cache.findById(1));

        List<User> users = cache.findAll();
        for (User user1 : users) {
            Thread second = new Thread(
                    () -> {
                        user1.setName("new name");
                    }
            );
            second.start();
            second.join();
        }
        // Проверяем какое значение в кеше
        System.out.println(cache.findById(1));
    }
}
