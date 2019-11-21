import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class ProsteLokatory {

     WebDriver driver;

    @BeforeEach
    public void driverSetup()
    {
        String amazonSite = "https://www.amazon.com/";
        String testerkaShop = "https://fakestore.testelka.pl/moje-konto/";
        String dunckelfeld = "https://www.dunckelfeld.de/";
        String zoouniverse = "https://www.zooniverse.org/";
        String gofile = "https://gofile.io/?t=uploadFiles";
        String fakeStore = "https://fakestore.testelka.pl/moje-konto/";
        String symulatorPaczek = "https://fifacoins.pl/logowanie";

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(fakeStore);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void driverClose()
    {
        driver.close();
        driver.quit();
    }
    
    @Test
    public void existentUsernameCorrectPasswordTest() {
        String username = "igor2019";
        String password =  "igortest2019";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String userDisplayName = "igor2019p1";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayName), "My account page does not contain correct name. Expected name" +
               userDisplayName + " was not found in a string" + myAccountContent);
    }

    @Test
    public void existentEmailCorrectPasswordTest() {
        String userEmail = "igor2019@op.pl";
        String password =  "igortest2019";
        driver.findElement(By.id("username")).sendKeys(userEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String userDisplayName = "igor2019p1";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayName), "My account page does not contain correct name. Expected name" +
                userDisplayName + " was not found in a string" + myAccountContent);
    }


    @Test
    public void existentUserNameIncorrectPasswordTest() {
        String username = "igor2019";
        String password =  "wrong";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "Nieznana użytkownik. Proszę sprawdzić ponownie lub spróbować swój email";
        Assertions.assertEquals(expectedMessage, errorMessageText , "Error message is not correct");
    }

    @Test
    public void nonexistentUserNameIncorrectPasswordTest() {
        String username = "NotExistingUser";
        String password =  "wrong";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "BŁĄD. Nieprawidłowa nazwa użytkownika. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText , "Error message is not correct");
    }

    @Test
    public void existentEmailInorrectPasswordTest() {
        String userEmail = "igor2019@op.pl";
        String password =  "wrong";
        driver.findElement(By.id("username")).sendKeys(userEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "BŁĄD: Dla adresu e-mail " + userEmail + " podano nieprawidłowe hasło. Nie Pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText , "Error message is not correct");
    }

    @Test
    public void existentEmailNoPasswordTest() {
        String userEmail = "igor2019@op.pl";
        String password =  "";
        driver.findElement(By.id("username")).sendKeys(userEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "BŁĄD: Pole Hasło jest puste";
        Assertions.assertEquals(expectedMessage, errorMessageText , "Error message is not correct");
    }

    @Test
    public void noUsernameDummyPasswordTest() {
        String userEmail = "";
        String password =  "xyz";
        driver.findElement(By.id("username")).sendKeys(userEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement element = driver.findElement(By.cssSelector("button[name='login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        Assertions.assertEquals(expectedMessage, errorMessageText , "Error message is not correct");
    }





}
