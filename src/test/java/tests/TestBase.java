package tests;

import appmanager.ApplicationMenager;
import org.junit.After;
import org.junit.Before;

/**
 * Created by Tirex on 16.11.2016.
 */
public class TestBase {

    protected final ApplicationMenager app = new ApplicationMenager();

    @Before
    public void setUp() throws Exception  {
        app.init();


    }

    @After
    public void tearDown(){
        app.stop();
    }


    public ApplicationMenager getApp() {
        return app;
    }
}
