package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestOperationsOFUser {
    private WebDriver browser;
    String user = "administrator";
    String senha = "root";
    String email = "root@localhost";
    String realName = "Dayvid William";
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
    public void changeRealName() {
        browser.findElement(By.id("realname")).clear();
        browser.findElement(By.id("realname")).sendKeys(realName);
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input")).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a/span")).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();

        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span")).click();
        browser.findElement(By.linkText("Gerenciar Usuários")).click();

        WebElement realNameTextBox = browser.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td[2]"));
        String realNameText = realNameTextBox.getText();
        assertEquals(realName, realNameText);
    }

    @Test
    public void AddEmail() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        browser.findElement(By.id("email-field")).clear();
        browser.findElement(By.id("email-field")).sendKeys(email);
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input")).click();
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();

        WebElement emailBox = browser.findElement(By.id("email-field"));
        String emailBoxText = emailBox.getAttribute("value");
        assertEquals(email, emailBoxText);
    }

    @Test
    public void AddPerfil() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();

        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul/li[4]/a")).click();
        browser.findElement(By.id("platform")).sendKeys("Java");
        browser.findElement(By.id("os")).sendKeys("Windowns");
        browser.findElement(By.id("os_build")).sendKeys("10");
        browser.findElement(By.id("description")).sendKeys("dados do sistema utilizado por este usuario");

        browser.findElement(By.xpath("//*[@id=\"account-profile-form\"]/fieldset/div/div[3]/button")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/div/a")).click();



        WebElement perfilBox =  browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[1]/h4"));
        String perfilText = perfilBox.getText();
        assertEquals("Perfís", perfilText);
    }
    @Test
    public void ChangePassword() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();

        browser.findElement(By.id("password-current")).sendKeys(senha);
        browser.findElement(By.id("password")).sendKeys(senha);
        browser.findElement(By.id("password-confirm")).sendKeys(senha);
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[2]/input")).click();

        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[4]/a")).click();

        browser.findElement(By.id("username")).sendKeys(user);
        browser.findElement(By.xpath("/html/body/div/div/div/div/div/div[4]/div/div/div[1]/form/fieldset/input[2]")).click();
        browser.findElement(By.id("password")).sendKeys(senha);
        browser.findElement(By.xpath("/html/body/div/div/div/div/div/div[4]/div/div/div/form/fieldset/input[3]")).click();

        WebElement userBox = browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a/span"));
        String nameInTheBox = userBox.getText();
        assertEquals(user, nameInTheBox);
    }


    @Test
    public void MakeDefaultPerfil() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul/li[4]/a")).click();


        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[6]/div/div[3]/form/button")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/div/a")).click();

        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[5]/i"));

        //if this test is run 2 times it will remove the main profile and this test is failed.
    }

    @Test
    public void ChangeProfileInfo() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul/li[4]/a")).click();

        //clicar o elemento de xpath "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[6]/div/div[1]/form/button".
        browser.findElement(By.xpath("//*[@id=\"categories\"]/div/div[2]/div/div/table/tbody/tr/td[6]/div/div[1]/form/button")).click();
        //Digitar no elemento de id "platform" o texto "Python"
        browser.findElement(By.id("platform")).clear();
        browser.findElement(By.id("platform")).sendKeys("Python");
        //Clicar no elmento de xpath "/html/body/div[2]/div[2]/div[2]/div/div/form/div/div[2]/div[2]/button"
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/form/div/div[2]/div[2]/button")).click();
        //clicar no botão de xpath "/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/div/a"
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/div/a")).click();
        //vericar se o elemento de xpath "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]" contem o texto "Python"
        WebElement checkPlatform = browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]"));
        String platformText = checkPlatform.getText();
        assertEquals("Python", platformText);

        //Need exist one perfil in the system for successful!
    }

    @Test
    public void DeletPerfil() {
        browser.findElement(By.linkText(user)).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul/li[4]/a")).click();

        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[6]/div/div[2]/form/button")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/form/input[6]")).click();

        WebElement ConfirmedTextBox = browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/p"));
        String ConfirmedText = ConfirmedTextBox.getText();
        assertEquals("Operação realizada com sucesso.", ConfirmedText);
    }

   // @After
    public void End() {
        browser.quit();
    }
}

