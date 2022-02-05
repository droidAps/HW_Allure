package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import ru.netology.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ApplicationPage {
    private SelenideElement cityField = $("[data-test-id='city'] input");
    private SelenideElement dateField =  $("[data-test-id='date'] input");
    private SelenideElement nameField = $("[data-test-id='name'] input");
    private SelenideElement phoneField = $("[data-test-id='phone'] input");
    private SelenideElement checkbox = $(".checkbox__box");
    private ElementsCollection button = $$("button");

    @Step("Заполнение и отправка заявки")
    public void sendApplication(RegistrationInfo user, String date) {
        cityField.setValue(user.getCity());
        nameField.setValue(user.getUserName());
        dateField.doubleClick().sendKeys(Keys.BACK_SPACE.subSequence(0, 1));
        dateField.setValue(date);
        phoneField.setValue(user.getPhoneNumber());
        checkbox.click();
        button.find(exactText("Запланировать")).click();
    }

    @Step("Заполнение поля 'Дата встречи'")
    public void fillInTheDate(String date) {
        dateField.doubleClick().sendKeys("BackSpace");
        dateField.setValue(date);
    }

    public ElementsCollection getButton() {
        return button;
    }
}
