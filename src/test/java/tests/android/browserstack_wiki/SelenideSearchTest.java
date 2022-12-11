package tests.android.browserstack_wiki;

import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SelenideSearchTest extends TestBase {

    @Test
    void successSearchTest() {
        step("Click Skip", () ->
                back()
        );
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });

        ElementsCollection results = $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));

        step("Verify content found", () -> {
            results.shouldHave(sizeGreaterThan(0));
        });
        step("Open content found", () -> {
            results.find(text("Java (programming language)")).click();
        });
        step("Verify content page", () -> {
            //$(AppiumBy.id("pcs-edit-section-title-description")).shouldHave(text("Object-oriented programming language"));
            String title = $(AppiumBy.className("android.widget.TextView")).getAttribute("text");
            Assertions.assertEquals( "Java (programming language)", title);
            //$$(AppiumBy.className("android.widget.TextView")).shouldHave(texts("Java (programming language)", "Object-oriented programming language"));
            //String title = $(AppiumBy.id("org.wikipedia.alpha:id/pcs-edit-section-title-description")).getAttribute("text");
            //Assertions.assertEquals( "Object-oriented programming language", title);
        });
    }
}
