import listener.MyTestListener;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(MyTestListener.class)

public class AnotherTestClass {

    @Test
    public void method5() {
        System.out.println("test method 1 ");
    }

    @Test
    public void method6() {
        System.out.println("test method 2 ");
        Assert.assertTrue(false);
    }

    @Test
    public void methodSkip2(){
        System.out.println("test method 3");
        throw new SkipException("Sorry I have plans");
    }


}
