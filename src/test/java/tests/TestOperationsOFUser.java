package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestOperationsOFUser {
    private WebDriver browser;
    String user = "administrator";
    String senha = "12345654321";
    String email = "emailTest@email.com";
    String realName = "Dayvid William";
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.drive", String.valueOf(driver));
        browser = new ChromeDriver();
        browser.get("http://localhost/mantisbt/login_page.php");
    }
    @Before
    public void UserLogin() {
        browser.manage().window().maximize();
        browser.findElement(By.id("username")).sendKeys(user);
        browser.findElement(By.xpath("/html/body/div/div/div/div/div/div[4]/div/div/div[1]/form/fieldset/input[2]")).click();
        browser.findElement(By.id("password")).sendKeys(senha);
        browser.findElement(By.xpath("/html/body/div/div/div/div/div/div[4]/div/div/div/form/fieldset/input[3]")).click();
        WebElement userBox = browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a/span"));
        String nameInTheBox = userBox.getText();
        assertEquals(user, nameInTheBox);
    }

    @Test
    public void AddEmail() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        //slecionar e apagar o conteudo do campo com id "email-field"
        browser.findElement(By.id("email-field")).clear();
        //digitar no elemento de id "email-field" o email armazenado na variavel "email"
        browser.findElement(By.id("email-field")).sendKeys(email);
        //Clicar no elemento de xpath "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input"
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input")).click();
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        WebElement emailBox = browser.findElement(By.id("email-field"));
        String emailBoxText = emailBox.getText();
        assertEquals(emailBoxText, emailBoxText);

        browser.findElement(By.id("realname")).clear();
        browser.findElement(By.id("realname")).sendKeys(realName);
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input")).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a/span")).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        WebElement realNameTextBox = browser.findElement(By.id("realname"));
        String realNameText = realNameTextBox.getAttribute("value");
        assertEquals(realName, realNameText);
    }




}
