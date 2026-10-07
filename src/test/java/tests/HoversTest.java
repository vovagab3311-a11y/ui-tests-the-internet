package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HoversPage;

public class HoversTest extends BaseTest {

    @Test(description = "Hover over each profile, verify caption and link URL")
    public void hoverOverEachProfileAndVerifyCaption() {
        HoversPage page = new HoversPage(driver).openPage();
        int count = page.getFiguresCount();
        Assert.assertEquals(count, 3, "Expected 3 hoverable profiles");

        for (int i = 0; i < count; i++) {
            page.hoverOverFigure(i);

            String caption = page.getCaptionName(i);
            Assert.assertTrue(caption.startsWith("name:"),
                    "Caption should show a name for figure " + i + " but was: " + caption);

            String href = page.getProfileLinkHref(i);
            // На сайте профили ведут на /users/N — сам адрес может отдавать 404,
            // это задокументированное поведение сайта, поэтому проверяем только URL.
            Assert.assertTrue(href.contains("/users/"),
                    "Profile link for figure " + i + " should contain /users/ but was: " + href);
        }
    }
}
