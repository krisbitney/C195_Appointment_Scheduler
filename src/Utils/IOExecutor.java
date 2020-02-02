package Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOExecutor {

    private static class IOExecutorSingleton {
        private static final ExecutorService INSTANCE = Executors.newSingleThreadExecutor();
    }

    private IOExecutor() {}

    public static ExecutorService getInstance() {
        return IOExecutorSingleton.INSTANCE;
    }

}
