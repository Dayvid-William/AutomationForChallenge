package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

public class LoginWithUserAndPassword {
    private WebDriver browser;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.drive", String.valueOf(driver));
        browser = new ChromeDriver();
        browser.get("http://localhost/mantisbt/login_page.php");
    }


    @Test
    public void FristLogin() {
        String senha = "12345654321";
        String user = "administrator";
        WebElement userBox = browser.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/a/span"));
        String NameInTheBox = userBox.getText();

        browser.findElement(By.id("username")).sendKeys(user);
        browser.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();
        browser.findElement(By.id("password")).sendKeys(senha);
        browser.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();

        assertEquals("administrator", NameInTheBox);
    }
}
