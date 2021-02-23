package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    @Tag("positive")
    void FormFieldsPopulationTest() {
//Test data
        Faker faker = new Faker();
        String firstname = faker.name().firstName(),
                lastname = faker.name().lastName(),
                email = faker.internet().emailAddress("test"),
                gender = "Female",
                mobile = faker.phoneNumber().subscriberNumber(10),
                month = "May",
                year = "1995",
                day = "30",
                subject1 = "Maths",
                subject2 = "Computer Science",
                hobby = "Reading",
                filename = "1.png",
                address = faker.address().fullAddress(),
                state = "NCR",
                city = "Noida";

        step("Open students registration form", () -> open("https://demoqa.com/automation-practice-form"));
        step("Fill in students registration form", () -> {
            $("#firstName").setValue(firstname);
            $x("//input[@id='lastName']").setValue(lastname);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue(subject1).pressEnter();
            $("#subjectsInput").setValue(subject2).pressEnter();
            $("#hobbiesWrapper").$(byText(hobby)).click();
            $("#uploadPicture").uploadFromClasspath("img/" + filename);
            $("#currentAddress").setValue(address);
            $("#submit").scrollIntoView(false);
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
            $("#submit").click();
        });
        step("Verify successful form submit", () -> {
            $(".modal-title").shouldHave(text("Thanks for submitting the form"));
            $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(texts(firstname + " " + lastname));
            $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(texts(email));
            $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts(gender));
            $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(mobile));
            $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(texts(day + " " + month + "," + year));
            $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(subject1 + "," + " " + subject2));
            $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts(hobby));
            $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts(filename));
            $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(address));
            $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(state + " " + city));
        });
    }

    @Test
    @Tag("negative")
    void FormFieldsPopulationNegativeTest() {
        step("Open students registration form", () -> open("https://demoqa.com/automation-practice-form"));
        step("Submit empty form", () -> $("#submit").click());
        step("Verify successful form submit", () -> $(".modal-title").shouldHave(text("Thanks for submitting the form")));
    }
}
