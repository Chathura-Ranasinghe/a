import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTest {
	
  WebDriver driver;
	
  @BeforeTest
  public void setUp() {
	  System.setProperty("webdriver,chrome.driver", "C:\\Users\\chath\\eclipse-workspace\\user_demo\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.navigate().to("http://localhost/employee/admin/users.php");
	  driver.manage().window().maximize();
  }

  @Test(priority = 1)
  public void login() {
	  WebElement inputUserName =  driver.findElement(By.name("username"));
	  inputUserName.sendKeys("admin");
	  WebElement inputPassword =  driver.findElement(By.name("password"));
	  inputPassword.sendKeys("admin123");
	  
	  if(inputUserName.getAttribute("value").isEmpty()) {
		  System.out.println("Please Enter Username");
	  }else if(inputPassword.getAttribute("value").isEmpty()) {
		  System.out.println("Please Enter Password");
	  }else {
	      driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	  }
	  
      if (driver.getPageSource().contains("error")) {
          System.out.println("Incorrect Username or Password");
      } else {
          System.out.println("Login successful");
      }
  }

  @Test(priority = 2, dependsOnMethods = "login")
  public void search() {
	  driver.findElement(By.id("Employee")).click();
	  driver.findElement(By.xpath("//input[@type = 'search']")).sendKeys("a");
      if (driver.getPageSource().contains("No matching records found")) {
          System.out.println("data undetected");
      } else {
          System.out.println("data detected");
      }
  }
  
  @Test(priority = 3, dependsOnMethods = "search")
  public void delete() {
	  driver.findElement(By.id("Employee")).click();
	  driver.findElement(By.xpath("//input[@type = 'search']")).sendKeys("Chathura");
      if (driver.getPageSource().contains("No matching records found")) {
          System.out.println("data undetected");
      } else {
          System.out.println("data detected");
    	  driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr/td[7]/center/button[2]")).click();
      }
  }
  
  @Test(priority = 4)
  public void update() {
  	driver.findElement(By.id("Employee")).click();
    driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[7]/center/button[1]")).click(); 
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("Chathura");
    driver.findElement(By.xpath("//*[@id=\"new_employee\"]/div/div/div/button")).click(); 
  }


  @AfterTest
  public void logout() {
	driver.findElement(By.xpath("/html/body/nav/div/div[2]/a")).click(); 
	driver.quit();
  }
}
