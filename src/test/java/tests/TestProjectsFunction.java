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

public class TestProjectsFunction {
    WebDriver browser;

    String user = "administrator";
    String senha = "12345654321";


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
    public void CreateProjet() {
        //clicar no elemento de xpath "//*[@id="sidebar"]/ul/li[6]/a/span"
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span")).click();
        //clicar no elemento de linktext "Gerenciar Projetos"
        browser.findElement(By.linkText("Gerenciar Projetos")).click();
        //clicar no elemento de xpath "//*[@id="main-container"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/form/button"
        browser.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/form/button")).click();
        //digitar no elemento de id "project-name"
        browser.findElement(By.id("project-name")).sendKeys("Projeto1");
        //clicar no elemento de xpath "//*[@id="manage-project-create-form"]/div/div[3]/input"
        browser.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[3]/input")).click();
        browser.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/div/a")).click();
        //verificar se o projeto foi criado
        WebElement projectBox = browser.findElement(By.linkText("Projeto1"));
        String projectBoxText = projectBox.getText();
        assertEquals("Projeto1", projectBoxText);
    }

    @Test
    public void ChangeStatusProject() {
        //clicar no elmento de xpath "//*[@id="sidebar"]/ul/li[7]/a"
        browser.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[7]/a")).click();
        //clicar no elemento de linktext "Gerenciar Projetos"
        browser.findElement(By.linkText("Gerenciar Projetos")).click();
        //clicar no elmento de linktext "Projeto1"
        browser.findElement(By.linkText("Projeto1")).click();
        //criar um webelement com o elmento de id "project-status" e troca para o status de "estavel"
        WebElement statusProject = browser.findElement(By.id("project-status"));
        new Select(statusProject).selectByVisibleText("release");
        //verificar se o status foi realmente modificado
        browser.findElement(By.xpath("//*[@id=\"manage-proj-update-form\"]/div/div[3]/input")).click();
        browser.findElement(By.linkText("Projeto1")).click();
    }
}
