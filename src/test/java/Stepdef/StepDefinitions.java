package Stepdef;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.Before;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class StepDefinitions {

    public static WebDriver driver;
    Properties prop = new Properties();
    public static String productName;
    public static String price;

    @Before
    public void getProperties() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/ObjectRepository.properties");
        prop.load(fis);
    }

    @After
    public void closeDriver() {
        driver.close();
    }

    @Given("^Open '([^\"]*)'$")
    public void login(String url) {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @Then("^assert page header to '([^\"]*)'$")
    public void Validate(String Message) {
        assert (driver.getTitle().equals(Message));
    }

    @When("^click '([^\"]*)'$")
    public void clickOnButton(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        driver.findElement(locator).click();
    }

    public By getLocator(String element) throws Exception {
        String dummy = prop.getProperty(element);
        String[] value = dummy.split("~");
        String locatorType = value[0];
        String locatorValue = value[1];

        if (locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if (locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if ((locatorType.toLowerCase().equals("classname")))
            return By.className(locatorValue);
        else if ((locatorType.toLowerCase().equals("tagname")))
            return By.className(locatorValue);
        else if ((locatorType.toLowerCase().equals("linktext")))
            return By.linkText(locatorValue);
        else if (locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if ((locatorType.toLowerCase().equals("cssselector")))
            return By.cssSelector(locatorValue);
        else if (locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Unknown locator type '" + locatorType + "'");
    }

    @When("^type '([^\"]*)' into '([^\"]*)'$")
    public void typeIntoTextBox(String data, String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        driver.findElement(locator).sendKeys(data);
    }

    @Then("^assert '([^\"]*)' with '([^\"]*)'$")
    public void assertText(String expected, String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        String actual = driver.findElement(locator).getText();
        assert actual.equals(expected);
    }

    @And("^get product name from '([^\"]*)'$")
    public void getProdName(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        String value = driver.findElement(locator).getText();
        productName = value;
    }

    @And("^get price from '([^\"]*)'$")
    public void getPrice(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        String value = driver.findElement(locator).getText();
        price = value;
    }

    @Then("^assert product name in '([^\"]*)'$")
    public void assertProductNameIn(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        String value = driver.findElement(locator).getText();
        assert value.equals(productName);
    }

    @Then("^assert price in '([^\"]*)'$")
    public void assertPriceIn(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        String value = driver.findElement(locator).getText();
        assert value.equals(price);
    }

    @And("^click signOut under '([^\"]*)'$")
    public void clickAndHoldButton_AccountsList(String locator_value) throws Throwable {
        By locator = getLocator(locator_value);
        final Actions clickOnElementAndHold = new Actions(driver);
        WebElement element = driver.findElement(locator);
        clickOnElementAndHold.moveToElement(element).perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("nav-item-signout-sa")));
        element2.click();
    }

    @And("^click on 1st product$")
    public void clickProduct() throws Throwable {
        By product_one = getLocator("Lnk_ProductOne");
        By product_two = getLocator("Lnk_ProducTwo");
        if (driver.findElements(product_one).size() != 0) {
            driver.findElement(product_one).click();
        }
        if (driver.findElements(product_two).size() != 0) {
            driver.findElement(product_two).click();
        }
    }
}
