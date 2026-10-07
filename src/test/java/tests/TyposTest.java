package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TyposPage;

public class TyposTest extends BaseTest {

    @Test(description = "Verify the paragraph text looks as expected (tolerant to typos)")
    public void verifyParagraphTextAgainstKnownVariants() {
        TyposPage page = new TyposPage(driver).openPage();

        String actualText = page.getParagraphText();
        // нормализуем все виды пробелов (в т.ч. неразрывные) в один
        String normalized = actualText.replaceAll("\\s+", " ").trim();

        // Первая часть предложения на сайте всегда неизменна — опечатка
        // вводится только в конце ("validation-worthy" -> "validation-wrothy" и т.п.)
        Assert.assertTrue(
                normalized.startsWith("This example demonstrates a typo being introduced"),
                "Unexpected paragraph text (site content is known to vary): " + actualText
        );
    }
}