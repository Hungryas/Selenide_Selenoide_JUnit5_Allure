package tests.mtsbank;


import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.mtsbank.MainPage;
import pages.mtsbank.deposit.DepositPage;
import pages.mtsbank.deposit.SchetPage;
import pages.mtsbank.deposit.VkladDohodniyPage;
import pages.mtsbank.deposit.VkladPage;

import static elements.PageContent.waitHeaderElementIsExist;

class DepositsTests extends BaseTest {
    private static final MainPage MAIN_PAGE = new MainPage();

    @BeforeEach
    void preview() {
        MAIN_PAGE.openMainPage().
                openHoverMenu("chastnim-licam/vkladi", "Вклады и счета");
    }

    @AllArgsConstructor
    @Getter
    private enum Deposits {
        VKLAD(new VkladPage()),
        VKLAD_DOHODNIY(new VkladDohodniyPage()),
        SCHET(new SchetPage());

        private final DepositPage depositPage;
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с корректным заполнением всех полей.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendCorrectData(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client();

        depositPage.
                enterClientData(client).
                clickNext().
                confirmationFieldIsDisplayed();
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания ФИО.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithoutFIO(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withFIO("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("FIO");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного ФИО.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithWrongFIO(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withFIO("a!,").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("CYRILLIC");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания номера телефона.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithoutPhone(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withPhone("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("PHONE");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного номера телефона.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithWrongPhone(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withPhone("800").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("PHONE");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания email.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithoutEmail(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withEmail("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                confirmationFieldIsDisplayed();
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного email.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithWrongEmail(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client().toBuilder().
                withEmail("ivanov@mail,ru").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("EMAIL");
    }


    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без согласия на обработку персональных данных.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendDataWithoutConditionAgree(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        Client client = new Client();

        depositPage.
                enterClientData(client).
                uncheckedConditionAgree().
                clickNext().
                helperTextIsDisplayed("FLAG");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без заполнения полей.")
    @ParameterizedTest
    @EnumSource(value = Deposits.class)
    void trySendWithoutData(Deposits deposit) {
        DepositPage depositPage = deposit.getDepositPage();
        MAIN_PAGE.openMenuPage(depositPage.getRelativeUrl(), depositPage.getSubsectionText());
        waitHeaderElementIsExist();
        depositPage.
                clickNext().
                helperTextIsDisplayed("FIO").
                helperTextIsDisplayed("EMPTY");
    }
}