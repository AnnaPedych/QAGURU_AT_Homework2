import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;

    }


@Test
    void selenideFieldsTest() {

//Test data
        String  firstname = "Anna",
                lastname = "Pedych",
                email = "p.ann@i.ua",
                gender = "Female",
                mobile = "0123456789",
                month = "May",
                year = "1995",
                day = "17",
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

               // unable to locate element $("#gender-radio-2").$(byValue("Female")).click();
               // element is not clickable $(".custom-control-input").selectRadio("Female");
               // element is not clickable  $("#genterWrapper").$(".custom-control-input").selectRadio("Female");
               // element not found $("#genterWrapper").$(byText("Female")).selectRadio("Female");

            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(mobile);

            //вопрос - тест остановится на упавшем шаге или можно попросить игнорировать?

            $("#dateOfBirthInput").click();
            //$(".react-datepicker__month-select").selectOption(4);
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            $(".react-datepicker__day--017").click();

            //как задать 30 и быть уверенным что выберется 30 мая, а не апреля?
            //$(".react-datepicker__week").$(byAttribute("aria-label", "Choose Wednesday, May 17th, 1995")).click();
            //$(".react-datepicker__week").$(byAttribute("aria-label~", "May 17th")).click();

            $("#subjectsInput").setValue(subject1).pressEnter();
            $("#subjectsInput").setValue(subject2).pressEnter();

            $("#hobbiesWrapper").$(byText(hobby)).click();

            $("#uploadPicture").uploadFile(new File (fileloc));

            $("#currentAddress").setValue(address);

            //Scroll
            $("#submit").scrollIntoView(false);

            $("#state").click();
            //$(".col-md-4 col-sm-12").selectOptionContainingText("NCR"); - выбирает но не сохраняет
            $("#stateCity-wrapper").$(byText(state)).click();

            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
            $("#submit").click();

// Test Results Verification
            $(".modal-title").shouldHave(text("Thanks for submitting the form"));
            //OK but stupid: $$(".table-responsive tr").shouldHave(textsInAnyOrder("Values", "Female", "p.ann@i.ua","17 May,1995", "Anna Pedych", "0123456789","Maths", "Reading", "03163e85-a0d5-4174-bb83-8eac88234887.png", "123 Main St, App.123", "NCR Noida"));
            $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(textsInAnyOrder(firstname + " " + lastname));
            $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(textsInAnyOrder(email));
            $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(textsInAnyOrder(gender));
            $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(textsInAnyOrder(mobile));
            $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(textsInAnyOrder(day + " " + month + "," + year));
            $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(textsInAnyOrder(subject1 + "," + " " + subject2));
            $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(textsInAnyOrder(hobby));
            $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(textsInAnyOrder(filename));
            $$(".table-responsive tr").filterBy(text("Address")).shouldHave(textsInAnyOrder(address));
            $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(textsInAnyOrder(state + " " + city));

    sleep(5000);
                }
}