package MainPackage;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;  
import java.util.*; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Main {
	WebDriver driver;
	String url = "";
	WebElement Element;
	String username = "";
	String pwd = "";
	String driverPath = "C:\\Selenium\\chromedriver.exe";
	int indexCount = 3; // Projects start from 3 in xpath
	PrintWriter writer;
	String outFile = "C:\\Selenium\\log.txt"

	Main() {
		System.setProperty(
				"webdriver.chrome.driver",
				driverPath);
		driver = new ChromeDriver();
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Main mantis = new Main();
		mantis.logOnToMantis();

	}

	public void logOnToMantis() throws FileNotFoundException {

		driver.get(url);
		setXpath("//*[@id='username']");
		Element.sendKeys(username);
		Element.submit();

		setXpath("//*[@id='password']");
		Element.sendKeys(pwd);
		Element.submit();

		// select projects
		writer = new PrintWriter(new FileOutputStream(
				new File(outFile), true /* append = true */));
		
		writer.write("\r\n\r\n============== "+new java.util.Date()+" ==============");
		clickProjects();
		writer.write("\r\n\r\n===========================================================");
		writer.close();


	}

	public void printTimings() {
		try {
			List<WebElement> Elements = driver
					.findElements(By
							.xpath("/html/body/div[2]/div[2]/div[2]/div[1]/div/div[4]/table/tbody/tr"));
						
			int i=0;
			
			for (WebElement ele : Elements) {
				
				if (ele.getAttribute("class").equals("") && (ele.getText()).contains(username)) {
					for(int j=1;j>0;j++){
						if(Elements.get(i-j).getAttribute("class").equals("row-category-history")){
							System.out.println(Elements.get(i-j).getText());
							writer.write("\r\n\r\n"+Elements.get(i-j).getText());
							break;
						}					
							
						}
					System.out.println(ele.getText());
					writer.write("\r\n"+ele.getText());
					}
				i++;
						
					}			
			clickProjects();
			
		} catch (Exception e) {
			writer.write("\r\n\r\n===========================================================");
			writer.close();
			 // System.out.println("Exception occurred : "+e);
		}

	}

	public void clickProjects() {

		setXpath("/html/body/div[1]/div/div[2]/ul/li[2]/a");
		Element.click();

		// selecting Project list (eg : GL = li[8])
		setXpath("/html/body/div[1]/div/div[2]/ul/li[2]/ul/li[3]/div/ul/li["
				+ indexCount + "]/a");
		indexCount++;
		Element.click();
		setXpath("/html/body/div[2]/div[1]/ul/li[4]/a");
		Element.click();
		setXpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div[2]/form/table/tbody/tr[3]/td/input");
		Element.click();

		printTimings();

	}

	public void setXpath(String path) {

		Element = driver.findElement(By.xpath(path));
	}

}
