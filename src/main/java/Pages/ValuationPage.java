package Pages;

import Hooks.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ValuationPage {

    private Helper helper;
    private FluentWait wait;
    public ValuationPage(Helper helper) {
        this.helper = helper;
        wait = new FluentWait(helper.driver);
    }

    By AcceptAll = By.xpath("//button[contains(@class, 'sc-mqi')][.='Accept All']");
    By VehicleRegistration = By.id("vrm");
    By GetStarted = By.xpath("//button[@type='submit'][contains(., 'Get started')]");


    public void GotoPage(String url){helper.driver.navigate().to(url);}

    public void AcceptCookies(){ wait.until(ExpectedConditions.elementToBeClickable(AcceptAll));
        helper.driver.findElement(AcceptAll).click(); }

    public void SearchVehicleRegistration(String value) { helper.driver.findElement(VehicleRegistration).sendKeys(value); }

    public void ClickGetStartedBtn(){ helper.driver.findElement(GetStarted).click(); }
}
