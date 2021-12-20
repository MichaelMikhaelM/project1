import DAO.UsersDAO;
import DAO.UsersDAOImpl;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Javalin app =  Javalin.create(config ->
                {
                    config.addStaticFiles("/", Location.CLASSPATH);
                }
        ).start(9000);

        new FrontController(app);


        /*
        System.setProperty("webdriver.chrome.driver","/home/mmwm/chromedriver");



        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:9000");

        Wait<WebDriver> wait;
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        username.sendKeys("seamus_robinson");
        password.sendKeys("srobinson");

        submit.click();


        WebDriverWait tempWait = new WebDriverWait(driver, Duration.ofSeconds(3)); // define local/temp wait only for the "sleep"

        try {
            tempWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-createReimb"))).click(); // condition you are certain won't be true
        }catch (TimeoutException e) {
        }
        //wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(createReimb,0));
        driver.get("http://localhost:9000/employee/");
        WebElement createReimb0 = driver.findElement(By.id("btn-createReimb"));

        tempWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-createReimb")));

        createReimb0.click();
        try {
            tempWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButton1")));
        }catch (TimeoutException e) {
        }


        try {
            tempWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-createReimb"))).click();
        }catch (TimeoutException e) {
        }

        WebElement reimbType = driver.findElement(By.id("dropdownMenuButton1"));
        reimbType.click();

        try {
            tempWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("other")));
        }catch (TimeoutException e) {
        }

        WebElement other = driver.findElement(By.id("other"));
        other.click();

        WebElement amount = driver.findElement(By.id("amount_id"));
        amount.sendKeys("0");

        WebElement description = driver.findElement(By.id("descr_id"));
        description.sendKeys("*****AUTOMATION*****");

        WebElement sub = driver.findElement(By.id("submitBtn"));
        sub.click();
        */

    }
}
