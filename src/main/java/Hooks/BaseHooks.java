package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BaseHooks {
    private Helper helper;
    public BaseHooks(Helper helper) { this.helper = helper;} //Constructor

    @Before
    public void Start(){
        WebDriverManager.chromedriver().browserVersion("116.0.5845.180").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");
        helper.driver = new ChromeDriver(options);
    }

    @After
    public void Shutdown() throws IOException {
        takeScreenShot();
        helper.driver.quit();
    }

    public int AddNumber(){ Random rand = new Random(); return rand.nextInt(999); }

    private void takeScreenShot() throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)helper.driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, new File ("src/test/Images/screenshot"
                + AddNumber() + ".png"));
    }
}
