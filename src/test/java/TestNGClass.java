import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestNGClass {

        public WebDriver driver;
        public String URL, Node;
        protected ThreadLocal<RemoteWebDriver> threadDriver = null;
        @Parameters("browser")
        @BeforeTest
        public void launchapp(String browser) throws MalformedURLException
        {
            String URL = "http://www.calculator.net";
            if (browser.equalsIgnoreCase("firefox"))
            {
                System.out.println(" Executing on FireFox");
                String Node = "http://135.135.137.11:5555/wd/hub";
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");

                driver = new RemoteWebDriver(new URL(Node), cap);
// Puts an Implicit wait, Will wait for 10 seconds
// before throwing exception
                driver.manage().timeouts()
                        .implicitlyWait(10, TimeUnit.SECONDS);
// Launch website
                driver.navigate().to(URL);
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("chrome"))
            {
                System.out.println(" Executing on CHROME");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                String Node = "http://10.112.66.52:5557/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts()
                        .implicitlyWait(10, TimeUnit.SECONDS);
//Launch website
                driver.navigate().to(URL);
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("ie"))
            {
                System.out.println(" Executing on IE");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("ie");
                String Node = "http://10.112.66.52:5558/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts()
                        .implicitlyWait(10, TimeUnit.SECONDS);

//Launch website
                driver.navigate().to(URL);
                driver.manage().window().maximize();
            }
            else
            {
                throw new IllegalArgumentException
                        ("The Browser Type is Undefined");
            }
        }
        @Test
        public void calculatepercent()
        {
// Click on Math Calculators
            driver.findElement(By.xpath(".//*[@id='menu']/div[3]/a")).click();

// Click on Percent Calculators
            driver.findElement(By.xpath(".//*[@id='menu']/div[4]/div[3]/a")).click();

// Enter value 10 in the first number of the percent Calculator
            driver.findElement(By.id("cpar1")).sendKeys("10");

// Enter value 50 in the second number of the percent Calculator
            driver.findElement(By.id("cpar2")).sendKeys("50");

// Click Calculate Button
            driver.findElement(By.xpath(".//*[@id='content']/table/tbody /tr/td[2]/input")).click();

// Get the Result Text based on its xpath
                    String result =
                            driver.findElement(By.xpath(".//*[@id='content']/p[2] /span/font/b")).getText();
                                    // Print a Log In message to the screen
                                    System.out.println(" The Result is " + result);

            if(result.equals("5"))
            {
                System.out.println(" The Result is Pass");
            }
            else
            {
                System.out.println(" The Result is Fail");
            }
        }

        @AfterTest
        public void closeBrowser()
        {
            driver.quit();
        }
    }


