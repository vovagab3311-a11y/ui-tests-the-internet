package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NotificationMessagePage;

public class NotificationMessageTest extends BaseTest {

    @Test(description = "Click the link, wait for the notification, verify it contains expected text")
    public void clickAndVerifyNotificationText() {
        NotificationMessagePage page = new NotificationMessagePage(driver).openPage();

        page.clickHere();
        String notificationText = page.waitForNotificationText();

        // Сайт намеренно показывает случайный вариант, в т.ч. с опечаткой
        // ("Action unsuccesful"), поэтому проверяем только стабильную часть.
        Assert.assertTrue(
                notificationText.toLowerCase().contains("action"),
                "Notification should contain 'Action', but was: " + notificationText
        );
    }
}