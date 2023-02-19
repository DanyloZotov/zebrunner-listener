package context;

import model.SuiteModel;
import model.TestModel;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RunContext {

    private static String testRunId;
    private static final Map<String, String> TESTS = new ConcurrentHashMap();
    private static final ThreadLocal<String> CURRENT_THREAD_LOCAL_TEST = new InheritableThreadLocal();
    //private static final ThreadLocal<String> CURRENT_THREAD_LOCAL_AFTER_METHOD = new InheritableThreadLocal();
    //private static final ThreadLocal<String> PREVIOUS_COMPLETED_THREAD_LOCAL_TEST = new ThreadLocal();

    public static void setTestRun(String runId){
        testRunId = runId;
    }

    public static String getRunId(){
        return testRunId != null ? testRunId : null;
    }

    public static void addTest(String id, String testId){
        TESTS.put(id,testId);
        CURRENT_THREAD_LOCAL_TEST.set(testId);
    }

    public static String getTestId(String id){
        return TESTS.get(id);
    }

    public static void removeTest(String id){
        TESTS.remove(id);
        CURRENT_THREAD_LOCAL_TEST.remove();
    }

    public static boolean hasTests(){
        return !TESTS.isEmpty();
    }

    public static Optional<String> getCurrentTest() {
        return Optional.ofNullable(CURRENT_THREAD_LOCAL_TEST.get());
    }

}
