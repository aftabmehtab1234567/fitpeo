package com.test4;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Fitpeo {

    public static void main(String[] args) throws Exception {
    	ScreenRecorderUtil.startRecord("main");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://fitpeo.com/");
        driver.manage().window().maximize();

        Thread.sleep(3000);
        driver.findElement(By.linkText("Revenue Calculator")).click();
        Thread.sleep(3000);

        // Locate the slider element
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-duk49p']"));
        WebElement sliderHandle = slider.findElement(By.xpath(".//span[contains(@class, 'MuiSlider-thumb')]"));

        // Maximum value and desired value
        int maxValue = 2000;
        double desiredValue = 693.2;

        // Calculate the offset required to move the slider to the desired value
        int sliderWidth = slider.getSize().getWidth();
        int handleWidth = sliderHandle.getSize().getWidth();
        
        // Calculate the offset based on the proportion of the desired value
        int xOffset = (int) ((double) desiredValue / maxValue * sliderWidth) - handleWidth / 2;


        // Create an Actions object for interacting with the element
        Actions actions = new Actions(driver);

        // Move the slider to the desired value
        actions.clickAndHold(sliderHandle)
               .moveByOffset(xOffset, 0)
               .release()
               .build()
               .perform();

        Thread.sleep(1000);

        // Check checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Iterate over each checkbox and click it if it's not already checked
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                Thread.sleep(900); // Optional: wait briefly to see the action
            }
        }

        Thread.sleep(3000);
        driver.close();
        ScreenRecorderUtil.stopRecord();
    }
}
