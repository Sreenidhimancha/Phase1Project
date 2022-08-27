package phase1;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phase1Assessment {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chromedriver", "chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//Launching Amazon website
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		//Searching with Samsung Key Word
		WebElement searchbox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("samsung");
		WebElement gobutton=driver.findElement(By.id("nav-search-submit-button"));
		gobutton.click();
		//Finding and Printing all the products and prices
		List<WebElement> productlinks= driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> productprices= driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		System.out.println("Number of Products found " +productlinks.size());
		for(int i=0; i<productlinks.size(); i++) {
			System.out.println(productlinks.get(i).getText()+ " " +productprices.get(i).getText());
		}
		//Navigating to the first product by clicking on first link
		String SearchResultsWin=driver.getWindowHandle();
		String ExpectedValue=productlinks.get(0).getText();
		productlinks.get(0).click();
		System.out.println(SearchResultsWin);
	
		Set <String>FirstLinkPage=driver.getWindowHandles();
		for(String Newwin :FirstLinkPage) {
			System.out.println(Newwin);
			if(!Newwin.equals(SearchResultsWin)) {
				driver.switchTo().window(Newwin);
			}}
		//Verifying if the name is same on both the windows.
		WebElement Producttitle=driver.findElement(By.id("productTitle"));
		String title=Producttitle.getText();
		if(title.equals(ExpectedValue)) {
			System.out.println("Title is matching");
		}else
			System.out.println("Title is not matching");
			
	}

}
