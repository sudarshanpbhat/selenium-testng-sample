package randomnumbers.test;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RandomNumberTest {
	
	public static void main(String[] args) throws InterruptedException {
		RandomNumberTest.testGoogleChromeDriver();
	}
	
	
	public static void testGoogleChromeDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/sudarshan/Downloads/chromedriver");
		
		// Initialize chrome driver 
		WebDriver driver = new ChromeDriver();
		
		// Load web page 
		driver.get("https://random.org/integers");
		
		// Delay to wait until webpage is loaded
		Thread.sleep(3000);
		
		// Find input field for "number of random numbers" 
		WebElement numOfRandoms = driver.findElement(By.xpath("//input[@name='num']"));
		assert(numOfRandoms != null);
		
		// Set number of randoms to 10
		numOfRandoms.clear(); 
		numOfRandoms.sendKeys("10");
		
		// Delay to see what's happening (not necessary) 
		Thread.sleep(1000);
		
		// Find input field for formatting in cols
		WebElement numOfCols = driver.findElement(By.xpath("//input[@name='col']"));
		assert(numOfRandoms != null);
		
		// Set columns to 10
		numOfCols.clear();
		numOfCols.sendKeys("10");
		
		// Delay to see what's happening (not necessary) 
		Thread.sleep(1000);
		
		// Submit the form = clicking generate button
		numOfCols.submit();
		
		// Delay for page to load with random numbers (required) 
		Thread.sleep(3000);
		
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
		
		int[] sortedArray = RandomNumberTest.sort(integerArray);
		System.out.println(Arrays.toString(sortedArray));
		
		driver.close();
	}
	
	private static int[] sort(int[] unsortedArray) {
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
