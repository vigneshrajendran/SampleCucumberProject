package pom;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class Home_Page {

    private static WebElement element = null;

    public static WebElement header(WebDriver driver){

        element = driver.findElement(By.xpath("//title[text() = 'Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more']"));

        return element;

    }
}
