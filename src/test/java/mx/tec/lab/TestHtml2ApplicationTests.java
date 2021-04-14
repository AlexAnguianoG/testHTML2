package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestHtml2ApplicationTests {
	private static WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\alex1\\Documents\\Codigo\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php");
		String title = driver.getTitle();
		
		//Then
		assertEquals("My Store", title);
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("alexag@alexag.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("alexagalex" + Keys.ENTER);
		String title = driver.getTitle();
		
		//Then
		assertEquals("My account - My Store", title);
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed() throws Exception{
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("alexag@alexag.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("alexagale" + Keys.ENTER);
		String title = driver.getTitle();
		
		//Then
		assertEquals("Login - My Store", title);
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed() throws Exception{
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("alexag@alexag.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("alexagale" + Keys.ENTER);
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		String error = errorMessage.getText();
		
		//Then
		assertEquals("Authentication failed.", error);
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsIsDisplayed() throws Exception{
		//When
		driver.get("http://automationpractice.com/index.php");
		WebElement textInput = driver.findElement(By.id("search_query_top"));
		textInput.sendKeys("alexag"+ Keys.ENTER);
		WebElement resultParagraph = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		String result = resultParagraph.getText();
		
		//Then
		assertEquals("No results were found for your search \"alexag\"", result);
	}
	
	//@Disabled
	@Test
	public	void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed() throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php");
		WebElement textInput = driver.findElement(By.id("search_query_top"));
		textInput.sendKeys(Keys.ENTER);
		WebElement resultParagraph = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		String result = resultParagraph.getText();
		
		//Then
		assertEquals("Please enter a search keyword", result);
	}

	//@Disabled
	@Test
	public	void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed() throws Exception {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("alexag@alexag.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("alexagalex" + Keys.ENTER);
		WebElement anchor = driver.findElement(By.xpath("//a[@class='logout']"));
		anchor.click();
		String logoutPage = driver.getTitle();
		
		//Then
		assertEquals("Login - My Store", logoutPage);
	}
}