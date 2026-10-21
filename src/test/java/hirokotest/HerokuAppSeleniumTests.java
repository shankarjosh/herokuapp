package hirokotest;

import hiroko.BrowserStackSeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HerokuAppSeleniumTests extends BrowserStackSeleniumBase {

    // Test 1: Add/Remove Elements
    @Test
    public void testAddRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("a[href='/add_remove_elements/']")).click();

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        addButton.click();

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(deleteButtons.size(), 2, "There should be 2 delete buttons present.");

        deleteButtons.get(0).click();
        deleteButtons = driver.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(deleteButtons.size(), 1, "There should be 1 delete button remaining.");

        System.out.println("Selenium Test 1 (Add/Remove Elements) Passed!");
    }

    // Test 2: Checkboxes
    @Test
    public void testCheckboxSelection() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("a[href='/checkboxes']")).click();

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);

        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
        Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 should be checked.");

        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
        Assert.assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unchecked.");

        System.out.println("Selenium Test 2 (Checkboxes) Passed!");
    }

    // Test 3: Dropdown
    @Test
    public void testDropdownSelection() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("a[href='/dropdown']")).click();

        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByValue("1");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "1");

        dropdown.selectByVisibleText("Option 2");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "2");

        System.out.println("Selenium Test 3 (Dropdown) Passed!");
    }

    // Test 4: Horizontal Slider
    @Test
    public void testHorizontalSlider() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("a[href='/horizontal_slider']")).click();

        WebElement slider = driver.findElement(By.cssSelector(".sliderContainer input"));
        WebElement rangeValue = driver.findElement(By.id("range"));

        // Use Actions class to move the slider right
        Actions move = new Actions(driver);
        move.clickAndHold(slider).moveByOffset(30, 0).release().perform();

        System.out.println("Slider Value after action: " + rangeValue.getText());
        Assert.assertFalse(rangeValue.getText().isEmpty(), "Slider range value should be displayed.");

        System.out.println("Selenium Test 4 (Horizontal Slider) Passed!");
    }
}