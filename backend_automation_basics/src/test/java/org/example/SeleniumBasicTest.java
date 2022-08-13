package org.example;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SeleniumBasicTest {


    public WebDriver driver;
    protected static String google = "https://www.google.com/";
    protected static String toolsQA = "https://www.toolsqa.com/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mert.aksoy\\git_repositories\\back-end-automation" +
                "\\backend_automation_basics\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void searchJavaAndClickSecondResult() {
        driver.get(google);
        Assert.assertEquals(driver.getTitle(), "Google");

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.cssSelector("input[name='btnK']"));

        searchBox.sendKeys("Java");
        searchButton.click();

        List<WebElement> secondResult = driver.findElements(By.cssSelector("div[id='search'] > div > div > div"));
        secondResult.get(1).click();
    }

    @Test
    public void toolsQA() {
        driver.get(toolsQA);
        Assert.assertEquals(driver.getTitle(), "Tools QA");

        WebElement scrollTo = driver.findElement(By.cssSelector("a.btn-primary-shadow"));
        WebElement testProjectCategory = driver.findElement(By.xpath("//div[text()='Test Project']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollTo);
        testProjectCategory.click();

        WebElement homeButton = driver.findElement(By.cssSelector("ul.navbar__links > li:nth-child(1)"));
        homeButton.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}