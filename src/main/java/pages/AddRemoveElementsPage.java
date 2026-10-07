package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    private static final String URL = "/add_remove_elements/";

    private final By addButton = By.xpath("//button[text()='Add Element']");
    // Кнопки Delete динамически создаются в контейнере #elements
    private final By deleteButtons = By.xpath("//div[@id='elements']//button");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    public AddRemoveElementsPage openPage() {
        open(URL);
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        return this;
    }

    public void clickAddElement() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        try {
            btn.click();
        } catch (Exception e) {
            // Если обычный click не сработал (перекрытие и т.п.) — жмём через JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        // Ждём появления хотя бы одной кнопки Delete
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(deleteButtons, 0));
    }

    public void clickDeleteElement() {
        List<WebElement> deletes = driver.findElements(deleteButtons);
        int before = deletes.size();
        if (before > 0) {
            deletes.get(0).click();
            // Ждём, пока количество уменьшится
            wait.until(ExpectedConditions.numberOfElementsToBe(deleteButtons, before - 1));
        }
    }

    public int getElementsCount() {
        return driver.findElements(deleteButtons).size();
    }
}