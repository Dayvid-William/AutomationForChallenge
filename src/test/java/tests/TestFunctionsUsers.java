package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestFunctionsUsers {
    WebDriver browser;

    String user = "administrator";
    String user2 = "dayvidwilliam";
    String senha = "root";

    String realname = "Dayvid William";

    String email = "email@email.com";


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.drive", String.valueOf(driver));
        browser = new ChromeDriver();
        browser.get("http://localhost/mantisbt/mantisbt-2.25.4/login_page.php");
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
    public void CreateNewUser() {
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/i")).click();
        browser.findElement(By.linkText("Gerenciar Usuários")).click();
        browser.findElement(By.linkText("Criar nova conta")).click();
        browser.findElement(By.id("user-username")).sendKeys(user2);
        browser.findElement(By.id("user-realname")).sendKeys(realname);
        browser.findElement(By.id("email-field")).sendKeys(email);


        WebElement AcessLevel = browser.findElement(By.xpath("//*[@id=\"user-access-level\"]"));
        new Select(AcessLevel).selectByVisibleText("gerente");
        browser.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[3]/input")).click();
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span")).click();
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/i")).click();
        browser.findElement(By.linkText("Gerenciar Usuários")).click();

        WebElement userBox = browser.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td[1]"));
        String userBoxText = userBox.getText();
        assertEquals(user2, userBoxText);
    }

    @Test
    public void EditUser() {
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/i")).click();
        browser.findElement(By.linkText("Gerenciar Usuários")).click();
        browser.findElement(By.linkText(user2)).click();

        WebElement AcessLevel = browser.findElement(By.xpath("//*[@id=\"edit-access-level\"]"));
        new Select(AcessLevel).selectByVisibleText("desenvolvedor");
        browser.findElement(By.xpath("//*[@id=\"edit-user-form\"]/div/div[2]/div[2]/input")).click();


        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/i")).click();
        browser.findElement(By.linkText("Gerenciar Usuários")).click();

        WebElement functionBox = browser.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td[4]"));
        String fuctionBoxText = functionBox.getText();
        assertEquals("desenvolvedor", fuctionBoxText);
    }
}
