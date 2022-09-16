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
    String senha = "12345654321";

    String realname = "Dayvid William";

    String email = "email@email.com";


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
    public void CreateNewUser() {
        //clicar no elemento de xpath "//*[@id="sidebar"]/ul/li[6]/a/i".
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/i")).click();
        //clicar no elemento de linkText "Gerenciar Usuários".
        browser.findElement(By.linkText("Gerenciar Usuários")).click();
        //clicar no elemento de linkText "Criar nova conta".
        browser.findElement(By.linkText("Criar nova conta")).click();
        //digitar no campo de id "user-username" o texto da variavel "user".
        browser.findElement(By.id("user-username")).sendKeys(user2);
        //digitar no campo de id "user-realname" o texto da variavel "real name".
        browser.findElement(By.id("user-realname")).sendKeys(realname);
        // digitar no campo de id "email-field" o texto da variavel "email".
        browser.findElement(By.id("email-field")).sendKeys(email);
        //-selecionar gerente no campo type
        WebElement AcessLevel = browser.findElement(By.xpath("//*[@id=\"user-access-level\"]"));
        new Select(AcessLevel).selectByVisibleText("gerente");
        // clicar no elemento de xpath "//*[@id="manage-user-create-form"]/div/div[3]/input"
        browser.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[3]/input")).click();
    }
}
