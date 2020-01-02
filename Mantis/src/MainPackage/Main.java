package MainPackage;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileWriter;
import java.io.PrintWriter;  
import java.util.*; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Main {
	WebDriver driver;
	String URL = "";
	WebElement Element;
	String Pwd = "Gotyeah44";
	int indexCount = 3;

	Main() {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\ND0080\\Code\\eclipse-standard-kepler-SR2-win32-x86_64\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main mantis = new Main();
		mantis.logOnToMantis();

	}

	public void logOnToMantis() {

		driver.get("http://192.168.168.205/wrgmantis/login_page.php");
		setXpath("//*[@id='username']");
		Element.sendKeys("rkarthick");
		Element.submit();

		setXpath("//*[@id='password']");
		Element.sendKeys(Pwd);
		Element.submit();

		// select projects
		clickProjects();

	}

	public void printTimings() {
		try {
			List<WebElement> Elements = driver
					.findElements(By
							.xpath("/html/body/div[2]/div[2]/div[2]/div[1]/div/div[4]/table/tbody/tr"));
			
			//int count = Elements.size();
			int i=0;
			BufferedWriter writer = new BufferedWriter(
                    new FileWriter("C:/Users/rkarthick/Desktop/Dailymantisdata.txt", true)  //Set true for append mode
                );  
				writer.newLine();   //Add new line
				
			for (WebElement ele : Elements) {
				writer.write("============================");
				if (ele.getAttribute("class").equals("") && (ele.getText()).contains("rkarthick")) {
					for(int j=1;j>0;j++){
						if(Elements.get(i-j).getAttribute("class").equals("row-category-history")){
							System.out.println(Elements.get(i-j).getText());
							writer.write(Elements.get(i-j).getText());
							break;
						}					
							
						}
					System.out.println(ele.getText());
					writer.write(ele.getText());
					}
				i++;
						
					}			
			clickProjects();
			writer.close();

		} catch (Exception e) {

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
