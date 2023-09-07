package Steps;

import Hooks.Helper;
import Pages.ResultPage;
import Pages.ValuationPage;
import com.github.dockerjava.api.exception.NotAcceptableException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.PReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class ValuationStepsdefinition {

    private Helper helper;
    private PReader propertiesReader;
    private ValuationPage valuationPage;
    private ResultPage resultPage;
    String regPattern = "^[A-Z]{2}\\d{2}\\s[A-Z]{3}$";
    public ValuationStepsdefinition(Helper helper, ValuationPage valuationPage, ResultPage resultPage) throws IOException {
        this.helper = helper;
        this.valuationPage = valuationPage;
        this.resultPage = resultPage;
        propertiesReader = new PReader();
    }

    @Given("I am on the valuation page")
    public void iAmOnTheValuationPage() {
        valuationPage.GotoPage(propertiesReader.getUrl());
        valuationPage.AcceptCookies();
    }

    @When("I search vehicle registration by model name {string}")
    public void iSearchVehicleRegistrationByModelNameModel(String make) throws IOException {
        List<String> regToSearch = propertiesReader.getVehicleReg("src/main/java/util/car_input_v2.txt");

        if (Objects.equals(make, "BMW"))
            valuationPage.SearchVehicleRegistration(regToSearch.get(0));
        else if (Objects.equals(make, "Toyota"))
            valuationPage.SearchVehicleRegistration(regToSearch.get(1));
        else if (Objects.equals(make, "SKODA"))
            valuationPage.SearchVehicleRegistration(regToSearch.get(2));
        else if (Objects.equals(make, "Volkswagen"))
            valuationPage.SearchVehicleRegistration(regToSearch.get(3));
        else
            throw new NotAcceptableException("Invalid Make Type");

        valuationPage.ClickGetStartedBtn();
    }

    @Then("search result is displayed {string}")
    public void searchResultIsDisplayedMake(String make) throws IOException {

        String actualExtractedTxt = resultPage.GetSearchResultHeaderTxt().replace("\n", "\n ");

        String expectedResultTxt = propertiesReader.readFromFile("src/main/java/util/car_output_v2.txt");
        String expectedTextByRegType = Objects.equals(make, "BMW")
                ? expectedResultTxt.split("\r")[2].split(",")[0]
                .concat("," + expectedResultTxt.split("\r")[2].split(",")[1])
                .concat("," + expectedResultTxt.split("\r")[2].split(",")[2])
                : Objects.equals(make, "SKODA")
                ? expectedResultTxt.split("\r")[4].split(",")[0]
                .concat("," + expectedResultTxt.split("\r")[4].split(",")[1])
                .concat("," + expectedResultTxt.split("\r")[4].split(",")[2])
                : Objects.equals(make, "Volkswagen")
                ? expectedResultTxt.split("\r")[1].split(",")[0]
                .concat("," + expectedResultTxt.split("\r")[1].split(",")[1])
                .concat("," + expectedResultTxt.split("\r")[1].split(",")[2])
                : null;

        String actualTextByRegType = Objects.equals(make, "BMW")
                || Objects.equals(make, "SKODA")
                || Objects.equals(make, "Volkswagen")
                ? actualExtractedTxt.split("\n")[1].trim()
                .concat(actualExtractedTxt.split("\n")[2]).replace("| ", "")
                .concat(actualExtractedTxt.split("\n")[3]).replace("| ", "")
                : null;
        Assert.assertTrue(Objects.equals(expectedTextByRegType, actualTextByRegType));
        //Assert.assertTrue(actualTextByRegType.contains(expectedTextByRegType));
    }

    @Then("errorMsg is displayed")
    public void errormsgIsDisplayedWeCouldnTFindACarWithThatRegistration() {
        boolean isDisplayed = resultPage.IsErrorMsgDisplayed();
        Assert.assertTrue(isDisplayed);
    }
}
