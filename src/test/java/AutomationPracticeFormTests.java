import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void FormFieldsPopulationTest() {
//Test data
        String firstname = "Anna",
                lastname = "Pedych",
                email = "p.ann@i.ua",
                gender = "Female",
                mobile = "0123456789",
                month = "May",
                year = "1995",
                day = "30",
                subject1 = "Maths",
                subject2 = "Computer Science",
                hobby = "Reading",
                fileloc = "src/test/resources/03163e85-a0d5-4174-bb83-8eac88234887.png",
                filename = "03163e85-a0d5-4174-bb83-8eac88234887.png",
                address = "123 Main St, App.123",
                state = "NCR",
                city = "Noida";
//Test steps
        open("https://demoqa.com/automation-practice-form");
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
        $("#uploadPicture").uploadFile(new File(fileloc));
        $("#currentAddress").setValue(address);
        $("#submit").scrollIntoView(false);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();
// Test Results Verification
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
    }
}