package ru.netology.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;
import ru.netology.page.ApplicationPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NewCardDeliveryTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    @Step("Открытие порта")
    void setup() { open("http://localhost:9999"); }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @Feature("Доставка карт")
    @Story("Перепланирование даты встречи")
    @DisplayName("Отправка повторной заявки с другой датой встречи")
    public void shouldSendRepeatedApplication() {
        RegistrationInfo info = DataGenerator.Registration.generateRusUser("Москва");
        String testDate = DataGenerator.Registration.dateMeeting(5);
        String newTestDate = DataGenerator.Registration.dateMeeting(14);

        var applicationPage = new ApplicationPage();
        applicationPage.sendApplication(info, testDate);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(exactText("Встреча успешно запланирована на " + testDate));
        applicationPage.fillInTheDate(newTestDate);
        applicationPage.getButton().find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Встреча успешно запланирована на " + newTestDate));
    }
}
