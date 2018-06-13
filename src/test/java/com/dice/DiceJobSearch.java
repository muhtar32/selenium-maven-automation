package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// set up chrome driver path
		WebDriverManager.chromedriver().setup();
		// invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		// full screen
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*
		 * Step 1. Launch browser and navigate to https://dice.com Expected: dice home
		 * page should be displayed
		 */
		String url = "https://www.dice.com";
		driver.get(url);
		
		String actualTitle=driver.getTitle();
		 String expectedTitle="Job Search for Technology Professionals | Dice.com";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage Successfully loaded");
		}else {
			System.out.println("Step FAIL. Dice homepage did not load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}
		/*
		 * Step 2. Insert search keyword and location then click on
      find tech jobs
		 */
		String keyWord="java developer";
		String location="22102";
		driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys(keyWord);
		driver.findElement(By.name("l")).clear();
		driver.findElement(By.name("l")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();
		 
		/*
		 *  Expected: -Search results page should be loaded.
                -Page title should contain count of results , 
                along with search keyword.
                -Count of results should be displayed on the page.

		 */
		
		 String count=driver.findElement(By.id("posiCountId")).getText();
		 System.out.println(count);
		 int countResult=Integer.parseInt(count.replaceAll(",", ""));
		 //make sure count is more than 0
		 if(countResult>0) {
			 System.out.println("Step PASS : KeyWord : "+keyWord+" search returned"+countResult+
					 " results in "+location);
		 }else {
			 System.out.println("Step FAIL : KeyWord : "+keyWord+" search returned"+countResult+
					 " results in "+location);
		 }
		 
		 
//		 String tabTitle= "2,520 java developer jobs in 22102 | Dice.com";
//		 String tabPage= driver.getPageSource();
//		 
//		 if(tabTitle.contains(count) && tabTitle.contains(keyWord) && tabPage.contains(count) ) {
//			 System.out.println("PASS");
//		 }else {
//			 System.out.println("FAIL");
//		 }
		 
		 driver.close();
		 System.out.println("Test completed : "+ LocalDateTime.now());
	}
}
