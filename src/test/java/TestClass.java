import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

//@Listeners(MyTestListener.class)

    public class TestClass {

//        @BeforeSuite
//        public void method1() {
//            System.out.println("before suite");
//        }
//
        @BeforeMethod
        public void methodBefore() {
            System.out.println("before method");
        }

        @Test
        public void method3() {
            System.out.println("test method 1 ");
        }

        @Test
        public void method4() {
            System.out.println("test method 2 ");
            Assert.assertTrue(false);
        }

    @Test
    public void methodSkip(){
            System.out.println("test method 3");
            throw new SkipException("Sorry I have plans");
        }

        @AfterMethod
        public void methodAfter() {
            System.out.println("after method");
        }

//        @AfterSuite
//        public void afterSuite() {
//            Assert.assertTrue(false);
//            System.out.println("after suite");
//        }
    }

