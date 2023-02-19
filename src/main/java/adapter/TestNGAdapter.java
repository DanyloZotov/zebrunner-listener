package adapter;

import context.TestInvocationContext;
import model.SuiteModel;
import model.TestModel;
import model.TestResultModel;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import reporter.ZebrunnerReporter;
import util.Configuration;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestNGAdapter {
    ZebrunnerReporter reporter = new ZebrunnerReporter();
    Configuration configuration = new Configuration("src/main/resources/config.json");

    public void registerRunStart(ISuite suite){
        String name = suite.getName();
        OffsetDateTime startedAt = OffsetDateTime.now();
        reporter.registerRunStart(new SuiteModel(name, startedAt, configuration.getParam("framework")));
    }

    public void registerRunFinish(ISuite suite){
        if (suite.getXmlSuite().getParentSuite() == null) {
            OffsetDateTime finishedAt = OffsetDateTime.now();
            reporter.registerRunFinish(finishedAt.toString());
        }
    }

//    public void registerHeadlessTestStart(ITestResult testResult, ITestNGMethod nextTestMethod){
//        TestInvocationContext testContext = this.buildTestInvocationContext(testResult);
//        String id = this.generateTestId(testContext);
//        OffsetDateTime startedAt = OffsetDateTime.now();
//        this.reporter.registerHeadlessTestStart(id, new HeadlessTestModel (startedAt));
//    }

    public void registerTestStart(ITestResult testResult){
            String name = testResult.getTestName();
            if (name == null) {
                name = testResult.getName();
            }
            String className = testResult.getTestClass().getRealClass().getName();
            String methodName = testResult.getMethod().getConstructorOrMethod().getMethod().getName();
            OffsetDateTime startedAt = ofMillis(testResult.getStartMillis());
            TestInvocationContext testInvocationContext = buildTestInvocationContext(testResult);
            String testId = generateTestId(testInvocationContext);
            reporter.registerTestStart(testId, new TestModel(name, className, methodName, startedAt));

    }

    public void registerTestFinish(ITestResult testResult){
        String result = configuration.getParam("successfulTest");
        OffsetDateTime endedAt = this.ofMillis(testResult.getEndMillis());
        TestInvocationContext testInvocationContext = buildTestInvocationContext(testResult);
        String testId = generateTestId(testInvocationContext);
        reporter.registerTestFinish(testId, new TestResultModel(result, endedAt));
    }

    public void registerFailedTestFinish(ITestResult testResult){
            String result = configuration.getParam("failedTest");
            String reason = testResult.getThrowable().getMessage();
            OffsetDateTime endedAt = this.ofMillis(testResult.getEndMillis());
            TestInvocationContext testInvocationContext = buildTestInvocationContext(testResult);
            String testId = generateTestId(testInvocationContext);
            reporter.registerTestFinish(testId, new TestResultModel(result, reason, endedAt));

    }

    public void registerSkippedTestFinish(ITestResult testResult){
            String result = configuration.getParam("skippedTest");
            String reason = testResult.getThrowable().getMessage();
            OffsetDateTime endedAt = this.ofMillis(testResult.getEndMillis());
            TestInvocationContext testInvocationContext = buildTestInvocationContext(testResult);
            String testId = generateTestId(testInvocationContext);
            reporter.registerTestFinish(testId, new TestResultModel(result, reason, endedAt));

    }

    private TestInvocationContext buildTestInvocationContext(ITestResult testResult) {
        ITestNGMethod testMethod = testResult.getMethod();
        ITestContext testContext = testResult.getTestContext();
        Object[] parameters = testResult.getParameters();
        return this.buildTestInvocationContext(testMethod, parameters);
    }

    private TestInvocationContext buildTestInvocationContext(ITestNGMethod testMethod, Object[] parameters) {
        String displayName = null;
        Test testAnnotation = (Test)testMethod.getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        if (testAnnotation != null) {
            displayName = testAnnotation.testName();
        }

        List<String> stringParameters = (List) Arrays.stream(parameters).map(Object::toString).collect(Collectors.toList());
        List<String> parameterClassNames = (List)Arrays.stream(testMethod.getConstructorOrMethod().getParameterTypes()).map(Class::getName).collect(Collectors.toList());
        return TestInvocationContext.builder().className(testMethod.getTestClass().getName()).methodName(testMethod.getMethodName()).displayName(displayName).parameters(stringParameters).parameterClassNames(parameterClassNames).build();
    }


    private String generateTestId(TestInvocationContext testInvocationContext) {
        return testInvocationContext.toString();
    }

    private OffsetDateTime ofMillis(long epochMillis) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
    }
}
