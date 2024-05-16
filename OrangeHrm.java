package testNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
// add '*' for import whole testng package
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrangeHrm {
 public String baseURL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
 public WebDriver driver;	
 
 @BeforeTest
 public void setup()
	{
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.navigate().to(baseURL);
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
 }
	
 @Test(priority=2)    //login with valid credentials
 public void LoginWithValid()
 {

	 driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
    driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin123");
    driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")).click();
    
    String pageTitle=driver.getTitle();
   /* if (pageTitle.equals("OrangeHRM"));
    {
    	System.out.println("Login successfull");
    } 
    else
    {
    	System.out.println("Login Failed");
    }
 }*/
    Assert.assertEquals("OrangeHRM", pageTitle);
 }
 
 @Test(priority=1)   //login with invalid credentials
 public void LoginWithInvalid() throws InterruptedException
 {
	 driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("admin");
	    driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("12344");
	    driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")).click();
	    
	    String exp_message="Invalid credentials";
	  String actual_message=  driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")).getText();
 
 Assert.assertEquals(exp_message, actual_message);
 
 Thread.sleep(3000);
 
 }
  
 @Test(priority=3)
 public void AddEmployee()
 {
	driver.findElement(By.xpath("//span[text()='PIM']")).click();; 
	driver.findElement(By.xpath("//a[text()='Add Employee']")).click();;
	driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys("Shahrukh");; 
	driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys("Ansari");; 
	driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")).click();; 
 
	String ActualTxt= driver.findElement(By.xpath("//h6[text()='Personal Details']")).getText();
	
	/*if (ActualTxt.contains("Personal Details"));
	{
		System.out.println("Employee added successfully!!");
	}else
	  {
		System.out.println("Employee not added!!");
	  }*/
	String ExpTxt="Personal Details";
	Assert.assertEquals(ActualTxt, ExpTxt);
	
	
 }
 
 
 
 
 
 public void Logout()   // webpage logout method 
 {
	 driver.findElement(By.xpath("//header/div[1]/div[2]/ul[1]/li[1]/span[1]/img[1]")).click();;
	 driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
 }
 
  
 @AfterTest
 public void TearDown() throws InterruptedException
 {
  Thread.sleep(5000);
 
  Logout();
  
  driver.close();
 driver.quit();
  
  
 }
	
	
}
