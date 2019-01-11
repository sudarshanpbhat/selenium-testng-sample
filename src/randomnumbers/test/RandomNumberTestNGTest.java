package randomnumbers.test;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RandomNumberTestNGTest {
	
	private final String webUrl = "https://random.org/integers";
	private WebDriver driver = null;
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/sudarshan/Downloads/chromedriver");
		
		// Initialize chrome driver 
		driver = new ChromeDriver();
		
		// Load web page 
		driver.get("https://random.org/integers");
	}
	
	@Test(priority = 1)
	public void populateRandomNumbers() throws InterruptedException {
		Thread.sleep(2000);
		
		// Find input field for "number of random numbers" 
		WebElement numOfRandoms = driver.findElement(By.xpath("//input[@name='num']"));
		
		// Set number of randoms to 10
		numOfRandoms.clear(); 
		numOfRandoms.sendKeys("10");
	}
	
	@Test(priority = 2) 
	public void populateFormat() throws InterruptedException {
		Thread.sleep(2000);
		
		// Find input field for formatting in cols
		WebElement numOfCols = driver.findElement(By.xpath("//input[@name='col']"));
		
		// Set columns to 10
		numOfCols.clear();
		numOfCols.sendKeys("10");
	}
	
	@Test(priority = 3)
	public void submitForm() throws InterruptedException {
		Thread.sleep(2000);
		
		// Submit the form = clicking generate button
		WebElement numOfCols = driver.findElement(By.xpath("//input[@name='col']"));
		numOfCols.submit();	
	}
	
	@Test(priority = 4)
	public void getAndSortData() throws InterruptedException {
		Thread.sleep(2000);
		
		// Get data web element where results are present 
		WebElement data = driver.findElement(By.xpath("//pre[@class='data']"));
		String value = data.getText();
		
		// Format data to sort 
		// Convert to string array
		String[] stringArray = value.split(" ");
		
		// Convert string array to integer array 
		int[] integerArray = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			integerArray[i] = Integer.parseInt(stringArray[i]);
		}
		
		int[] sortedArray = sort(integerArray);
		System.out.println(Arrays.toString(sortedArray));
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		
		driver.close();
	}
	
	
	// Sorting function
	int[] sort(int[] unsortedArray) {
		if (unsortedArray == null) {
			return null;
		}
		
		int temp = 0; // Variable used to swap integers
		for (int i = 0; i < unsortedArray.length; i++) {
			for (int j = 1; j < unsortedArray.length; j++) {
				if (unsortedArray[j-1] > unsortedArray[j]) {
					temp = unsortedArray[j];
					unsortedArray[j] = unsortedArray[j-1];
					unsortedArray[j-1] = temp;
				}
			}
		}
		return unsortedArray;
	}
}
