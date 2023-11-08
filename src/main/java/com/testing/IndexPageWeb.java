package com.testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.openqa.selenium.Cookie;
public class IndexPageWeb {
	private static final Logger logger = Logger.getLogger(IndexPageWeb.class.getName());
    private WebDriver driver;
    private WebDriverWait wait;

	@BeforeMethod
    public void setUp() throws InterruptedException {
        // ...WebDriverManager.chromedriver().setup();

        // Créez une instance de WebDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Créer une session au début du test
        logger.info("Création de la session");
        driver.get("https://www.plainofdigital.com/login");

        WebElement emailInput = driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/input"));
        emailInput.sendKeys("plaindigital@gmail.com");

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        passwordInput.sendKeys("curvaarena");

        WebElement buttonLogin = driver.findElement(By.cssSelector("button.btn.btn-lg.btn-primary"));
        logger.info("Clic sur le bouton de connexion");
        buttonLogin.click();
        Thread.sleep(1000);

        // Récupérer les cookies de la session
        Set<Cookie> cookies = driver.manage().getCookies();

        // Accéder à la page admin (vous pouvez remplacer l'URL par l'URL réelle de la page admin)
        driver.get("https://www.plainofdigital.com/admin");
        
        // Ajouter les cookies de la session à la nouvelle page
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }

        // Recharger la page admin pour que les cookies prennent effet
        driver.get("https://www.plainofdigital.com/admin");

        // ...
    }
    private static void verifyMenuItem(java.util.List<WebElement> menuItems, String itemName) {
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().toLowerCase().contains(itemName)) {
                System.out.println(itemName + " trouvé dans le menu.");
                return;
            }
        }
        System.out.println(itemName + " non trouvé dans le menu.");
    }
    @Test
    public void testOpenWebPage() throws InterruptedException {
        // Utilisez le WebDriver comme d'habitude
        logger.info("Ouverture de la page Web : https://www.plainofdigital.com/admin");
        WebElement HomeMessageDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("  body > div > section > div > div.jumbotron > h1")));
        String actualMessage = HomeMessageDiv.getText();
        System.out.println("message d'acceuil"+actualMessage);
        String expectedMessage = "Plain Of digital Administration";

        // Vérifiez si le message de succès correspond à celui attendu
        assert actualMessage.trim().equalsIgnoreCase(expectedMessage.trim()) : "Le message de succès ne correspond pas au texte attendu.";
        WebElement menu = driver.findElement(By.id("main-menu"));
        List<WebElement> menuItems = menu.findElements(By.tagName("ul"));
        List<String> requiredItems = Arrays.asList("dashboard", "service", "portofolio","message","demande","article");
        for (WebElement menuItem : menuItems) {
            String text = menuItem.getText().trim().toLowerCase();
            if (!requiredItems.contains(text)) {
                // Si un élément non requis est présent, échouer le test
                System.out.println("Échec du test : Élément non requis trouvé dans le menu : " + text);
                driver.quit();
                System.exit(1); // Terminer le programme avec un code d'échec
            }
        }

        // Si tous les éléments requis sont présents, le test réussit
        System.out.println("Test réussi : Tous les éléments requis sont présents dans le menu.");
        logger.info("Test terminé");
    }

	@AfterMethod
    public void tearDown() {
        // Fermez le navigateur
        if (driver != null) {
        	driver.quit();;
        }
}
}
