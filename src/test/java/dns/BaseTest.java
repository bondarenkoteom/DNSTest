package dns;

import org.junit.After;
import org.junit.Before;


public class BaseTest {


    @Before
    public void startUp() {
        Init.initWebDriver();
    }

    @After
    public void end() {
        Init.getDriver().close();
    }
}
