package ru.sberbank.smartoffice.at.pageelements.fields;

import com.codeborne.selenide.SelenideElement;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import javax.annotation.Nonnull;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Character.toUpperCase;
import static java.lang.String.format;
import static java.util.Calendar.*;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;
import static ru.sberbank.smartoffice.at.utilities.epochtime.EpochTime.day;

/**
 * <h1>Репрезентация поля ввода даты с календарем</h1>
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CalendarField extends Field {

    SelenideElement selectContainer;
    SelenideElement calendarHead;
    SelenideElement calendarImage;
    Calendar calendar;

    public CalendarField(String name, Integer index) {
        this(name, $x(format("(//input[@mask='11.11.1111'])[%d]", index)));
    }

    public CalendarField(String name, SelenideElement element) {
        super(name, element);
        selectContainer = $x("//div[contains(@class, 'select_container')]");
        calendarHead = $x("//div[contains(@class, 'header_container')]");
        calendarImage = getElement().$x(".//following-sibling::img");
        calendar = Calendar.getInstance();
    }

    @Override
    public void sendRandomKeys() {
        throw new UnsupportedOperationException("Невозможно ввести случайный текст в поле даты");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Невозможно очистить текст в поле даты");
    }

    @Override
    public void selectValue(String value) {
        calendar.setTimeInMillis(day(value).at("00:00") * 1_000);
        calendarImage.click();
        calendarHead.shouldBe(visible);
        selectYear();
        selectMonth();
        selectDay();
    }

    @Override
    public void shouldHaveText(String text) {
        try {
            getElement().should(visible, attribute("value", text));
        } catch (AssertionError error) {
            throw new TestError(INVALID_TEXT, error, getName());
        }
    }

    @Override
    public void shouldHaveError(String text) {
        try {
            getElement().$x(format("./ancestor::div[4]/following-sibling::div[contains" +
                    "(@class, 'form-module__error') and (text()='%s')]", text)).should(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" нет предупреждения с текстом \"%s\"", getName(), text), error);
        }
    }

    @Override
    public void shouldNotHaveError() {
        try {
            getElement().$x("./ancestor::div[4]/following-sibling::div[contains" +
                    "(@class, 'form-module__error')]").shouldNot(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" есть предупреждение!", getName()), error);
        }
    }

    /**
     * Выбирает год в календаре
     */
    private void selectYear() {
        String selectableYear = Integer.toString(calendar.get(YEAR));
        SelenideElement year = selectContainer.$x("./div[2]")
                .shouldBe(visible);
        if (!year.getText().equals(selectableYear)) {
            year.click();
            $$("li")
                    .find(text(selectableYear))
                    .click();
        }
    }

    /**
     * Выбирает месяц в календаре
     */
    private void selectMonth() {
        String selectableMonth = getStandaloneMonth();
        SelenideElement month = selectContainer.$x("./div[1]")
                .shouldBe(visible);
        if (!month.getText().equals(selectableMonth)) {
            month.click();
            $$("li")
                    .find(text(selectableMonth))
                    .click();
        }
    }

    /**
     * Выбирает день в календаре
     */
    private void selectDay() {
        $$("td[role=gridcell]")
                .find(attribute("title", getSelectableDateTitle()))
                .shouldBe(visible)
                .click();
    }

    /**
     * Возвращает <code>title</code>, который должен
     * быть в календаре у ячейки выбираемого дня.
     *
     * @return <code>title</code>, который должен
     * быть в календаре у ячейки выбираемого дня
     */
    @Nonnull
    private String getSelectableDateTitle() {
        String title = format("%d %s %d г.",
                calendar.get(DAY_OF_MONTH),
                getPreferredMonthFrom(),
                calendar.get(YEAR));

        if (title.charAt(0) == '0') {
            return title.substring(1);
        }
        return title;
    }

    /**
     * Приводит локализованный месяц в форму именительного падежа.
     *
     * @return локализованный месяц в именительном падеже
     */
    @Nonnull
    private String getStandaloneMonth() {
        String month = getPreferredMonthFrom();
        return toUpperCase(month.charAt(0)) + month.substring(1, month.length() - 1) + "ь";
    }

    /**
     * Возвращает локализованное название месяца.
     *
     * @return локализованное название месяца
     *
     * @see DateFormatSymbols
     */
    private String getPreferredMonthFrom() {
        return new DateFormatSymbols(new Locale("ru")).getMonths()[calendar.get(MONTH)];
    }
}
