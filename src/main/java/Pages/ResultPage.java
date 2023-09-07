package Pages;

import Hooks.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ResultPage {
    private Helper helper;
    private FluentWait wait;
    public ResultPage(Helper helper) {
        this.helper = helper;
        wait = new FluentWait(helper.driver);
    }

    By SearchResultHeaderTxt = By.xpath("//div[contains(@class, 'top-headingstyles__VehicleDetailTopC')]");
    By errorMsg = By.xpath("//div[@role='alert']");


    public String GetSearchResultHeaderTxt(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SearchResultHeaderTxt));
        return helper.driver.findElement(SearchResultHeaderTxt).getText(); }

    private void GetText(By by){
        JavascriptExecutor js = (JavascriptExecutor)helper.driver;
        js.executeScript("arguments[0].innerText", helper.driver.findElement(by));
    }

    public boolean IsErrorMsgDisplayed(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMsg));
        return helper.driver.findElement(errorMsg).isDisplayed();
    }
}
