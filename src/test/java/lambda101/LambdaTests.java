package lambda101;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LambdaTests {
	
public RemoteWebDriver driver;
	
	@Parameters(value= {"browsername","version","platform"})
	@BeforeClass
	public void setUp(String browsername, String version, String platform) {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName(platform);
		browserOptions.setCapability("browserName", browsername);
		browserOptions.setBrowserVersion(version);
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "gagansarao28");
		ltOptions.put("accessKey", "R8xVAvVJzKCevpyMayyyBNKc0DJFHx45NZjyx07KRToM9eEpQP");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "LambdaProject");
		ltOptions.put("console", "true");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		try {
			driver = new RemoteWebDriver(new URL("https://gagansarao28:R8xVAvVJzKCevpyMayyyBNKc0DJFHx45NZjyx07KRToM9eEpQP@hub.lambdatest.com/wd/hub"),browserOptions);
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(priority =1)
	public void testScenario1() throws InterruptedException {
		driver.get("https://www.lambdatest.com/selenium-playground");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebElement formDemo = driver.findElement(By.xpath("//a[text()=\"Simple Form Demo\"]"));
		Thread.sleep(2000);
		formDemo.click();
		String expectedUrl = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
		Assert.assertTrue(actualUrl.contains(expectedUrl));
		String value = "Welcome to LambdaTest";
		WebElement textBox = driver.findElement(By.id("user-message"));
		textBox.sendKeys(value);
		WebElement button = driver.findElement(By.id("showInput"));
		button.click();
		String confirmation = driver.findElement(By.xpath("//p[@id=\"message\"]")).getText();
		Assert.assertEquals(confirmation, value);
		
	}
	
	
	@Test(priority =2)
	public void testScenario2() {
		driver.get("https://www.lambdatest.com/selenium-playground");
		driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();

		WebElement slider = driver.findElement(By.xpath("//input[@type='range' and @value='15']"));
		WebElement sliderValue = driver.findElement(By.id("rangeSuccess"));

        Actions actions = new Actions(driver);
        
        Point location = slider.getLocation();
        int x = location.getX();
        
        actions.dragAndDropBy(slider, 215, 0).build().perform();
        String expectedVal = "95"; 
        String value = sliderValue.getText();
        if (expectedVal.equals(value)) {
            System.out.println("Slider has moved to 95");
        } else {
            System.out.println("Slider has failed to move to 95 value. Current value is " + value);
		
	}

}
	
	
	@Test(priority =3)
	public void testScenario3() throws InterruptedException {
		driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
		
		
		WebElement Name = driver.findElement(By.id("name"));
		String error = Name.getAttribute("validationMessage");
		
		String expected = "Please fill out this field.";
		System.out.println(error);
		Assert.assertEquals(error, expected);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement name  = driver.findElement(By.id("name"));
		name.sendKeys("Gagan");
		WebElement email  = driver.findElement(By.id("inputEmail4"));
		email.sendKeys("test@gmail.com");
		WebElement password  = driver.findElement(By.id("inputPassword4"));
		password.sendKeys("TestPassword");
		WebElement company  = driver.findElement(By.id("company"));
		company.sendKeys("Lambda Company");
		WebElement website  = driver.findElement(By.id("websitename"));
		website.sendKeys("www.test.com");
		WebElement dropDown = driver.findElement(By.name("country"));
		Select select = new Select(dropDown);
		select.selectByVisibleText("United States");
		WebElement city = driver.findElement(By.id("inputCity"));
		city.sendKeys("Edison");
		WebElement address1 = driver.findElement(By.id("inputAddress1"));
		address1.sendKeys("Edison street");
		WebElement address2 = driver.findElement(By.id("inputAddress2"));
		address2.sendKeys("Edison street2");
		WebElement state = driver.findElement(By.id("inputState"));
		state.sendKeys("New York");
		WebElement zip = driver.findElement(By.id("inputZip"));
		zip.sendKeys("06850");
		driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
		String successMessage = driver.findElement(By.cssSelector(".success-msg")).getText();
		String expectedMessage = "Thanks for contacting us, we will get back to you shortly.";
		Assert.assertEquals(successMessage,expectedMessage);
		
		}
	

}
