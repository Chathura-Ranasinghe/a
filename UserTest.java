import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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

//alwaysRun dependsOnMethods description enabled  invocationCount invocationTimeOut ThreadPoolSize
// groups = {"a"}   
//<groups>
//	<run>
//		<exclude name="smoke"></exclude>
//		<include name="reg"></include>
//	</run>
//</groups>


//@Test
//@Parameters({"username", "password"})
//public void loginTest(String username, String password) {
//    System.out.println("Username: " + username);
//    System.out.println("Password: " + password);
//    // Add your login logic here
//}
//<parameter name="username" value="chathura" />

//@DataProvider(name = "login")
//public String[][] getData() {
//    String[][] data = new String[2][2];
//
//    data[0][0] = "yssyogesh";
//    data[0][1] = "12345";
//
//    data[1][0] = "bsonarika";
//    data[1][1] = "12345";
//
//    return data; 
//}
//
//@Test(dataProvider = "login")
//public void testMethod(String name, String age) {
//    System.out.println("Name: " + name + ", Age: " + age);
//}


package practical01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Practical01 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("file:///E:\\Campus\\Y3\\S2\\CS3212 Practical Work on CS 3222\\Exams\\Practical-01/registration.html");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("fn1")).sendKeys("");

		driver.findElement(By.id("male")).click();
		driver.findElement(By.className("automation1")).click();
		driver.findElement(By.className("automation2")).click();
		
		WebElement dropdown = driver.findElement(By.id("country"));
		Select selectCountry = new Select(dropdown);
		selectCountry.selectByVisibleText("Sri Lanka");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

}