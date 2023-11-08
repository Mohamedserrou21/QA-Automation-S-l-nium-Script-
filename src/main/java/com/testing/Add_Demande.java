package com.testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Add_Demande {
	private WebDriverWait wait;
	private WebDriver driver;
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
	  
	        String societe = "TEST";
	        String Nom="Mohamed sarrou";
	        String Ville="Fes";
	        String Domaine = "Design";
	        String Telephone ="0762914480";
	        String email ="mohamed@gmail.com";
	        driver.get("https://www.plainofdigital.com/");
	        WebElement DemandeLink = driver.findElement(By.cssSelector("#header > nav > div.container > div.links > ul > li:nth-child(4) > a"));
	        DemandeLink.click();
	        WebElement SocieteInput = driver.findElement(By.cssSelector("#demande_societe"));
	        SocieteInput.sendKeys(societe);
	        WebElement NomInput = driver.findElement(By.cssSelector("#demande_fullname"));
	        NomInput.sendKeys(Nom);
	        WebElement VilleInput = driver.findElement(By.cssSelector("#demande_ville"));
	        VilleInput.sendKeys(Ville);
	        WebElement DomaineSelect = driver.findElement(By.cssSelector("#demande_service"));
	        Select selectDomaine = new Select(DomaineSelect);
	        selectDomaine .selectByVisibleText(Domaine);
	        WebElement TeleInput = driver.findElement(By.cssSelector("#demande_tele"));
	        TeleInput.sendKeys(Telephone);
	        WebElement EmailInput = driver.findElement(By.cssSelector("#demande_email"));
	        EmailInput.sendKeys(email);
	        WebElement Submit = driver.findElement(By.cssSelector("#contact > div > div > div.contact-form > form > button"));
	        Submit.click();
	        Thread.sleep(2000);
	        WebElement SuccessMessageDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));
	        String actualMessage = SuccessMessageDiv.getText();
	        String expectedMessage = "Demande envoye par succes! merci pour votre confiance";

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
	        if (driver != null) {
	        	driver.quit();;
	        }
	   }
	
}
