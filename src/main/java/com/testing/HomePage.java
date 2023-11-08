package com.testing;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.logging.Logger;
public class HomePage {
	 private WebDriver driver;
	 private WebDriverWait wait;
	
	@BeforeMethod
	    public void setUp() {
	        // Téléchargez et configurez automatiquement le pilote Chrome
	        WebDriverManager.chromedriver().setup();

	        // Créez une instance de WebDriver
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	       
	    }

	    @Test
	    public void HomePage() throws InterruptedException {
	        // Utilisez le WebDriver comme d'habitude
	       
	        driver.get("https://www.plainofdigital.com/");
	        WebElement contactLink = driver.findElement(By.cssSelector("a.nav-item.nav-link[href='/contact/new']"));
	        contactLink.click();
	        WebElement firstNameInput = driver.findElement(By.cssSelector("input#contact_firstname.contact-input"));
	        firstNameInput.sendKeys("Test Selenium");
	        WebElement lastNameInput = driver.findElement(By.cssSelector("input#contact_lastname.contact-input"));
	        lastNameInput.sendKeys("Sarrou");
	        WebElement phoneInput = driver.findElement(By.cssSelector("input#contact_phone.contact-input"));
	        phoneInput.sendKeys("076391448982");
	        WebElement emailInput = driver.findElement(By.cssSelector("input#contact_email.contact-input"));
	        emailInput.sendKeys("sarrou@email.com");
	        WebElement messageTextarea = driver.findElement(By.cssSelector("textarea#contact_message.contact-input.textarea"));
	        messageTextarea.sendKeys("Test selenium");
	        WebElement sendButton = driver.findElement(By.cssSelector("button.btn"));
	        sendButton.click();
	        Thread.sleep(5000);
	        WebElement SuccessMessageDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));
	        String actualMessage = SuccessMessageDiv.getText();
	        String expectedMessage = "Message envoye par succes! Merci de nous contacter on vous reponds dans le plus que possible!";

	        // Vérifiez si le message de succès correspond à celui attendu
	        if (actualMessage.contains(expectedMessage)) {
	            System.out.println("Le message de succès est correct : " + actualMessage);
	        } else {
	            System.out.println("Le message de succès ne correspond pas au texte attendu.");
	        }	        
	    }
		@AfterMethod
	    public void tearDown() {
	        // Fermez le navigateur
	        //if (driver != null) {
	        	//driver.get("https://www.plainofdigital.com/admin");
	        //}
	}

}
