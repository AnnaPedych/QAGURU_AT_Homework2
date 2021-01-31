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
            open("https://demoqa.com/automation-practice-form");
            $("#firstName").setValue("Anna");
            $("#lastName").setValue("Pedych");
            $("#userEmail").setValue("p.ann@i.ua");

               // unable to locate element $("#gender-radio-2").$(byValue("Female")).click();
               // element is not clickable $(".custom-control-input").selectRadio("Female");
               // element is not clickable  $("#genterWrapper").$(".custom-control-input").selectRadio("Female");
               // element not found $("#genterWrapper").$(byText("Female")).selectRadio("Female");

            $("#genterWrapper").$(byText("Female")).click();
            $("#userNumber").setValue("0123456789");

            //вопрос - тест остановится на упавшем шаге или можно попросить игнорировать?

            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(4);
            $(".react-datepicker__year-select").selectOption("1995");
            $(".react-datepicker__day--017").click();

            //как задать 30 и быть уверенным что выберется 30 мая, а не апреля?
            //$(".react-datepicker__week").$(byAttribute("aria-label", "Choose Wednesday, May 17th, 1995")).click();
            //$(".react-datepicker__week").$(byAttribute("aria-label~", "May 17th")).click();

            $("#subjectsInput").setValue("Math").pressEnter();
            $("#subjectsInput").setValue("Computer Science").pressEnter();

            $("#hobbiesWrapper").$(byText("Reading")).click();

            $("#uploadPicture").uploadFile(new File ("src/test/resources/03163e85-a0d5-4174-bb83-8eac88234887.png"));
            $("#currentAddress").setValue("123 Main St, App.123");

    //Scroll
            $("#submit").scrollIntoView(false);

            $("#state").click();
            //$(".col-md-4 col-sm-12").selectOptionContainingText("NCR"); - выбирает но не сохраняет
            $("#stateCity-wrapper").$(byText("NCR")).click();

            $("#city").click();
            $("#stateCity-wrapper").$(byText("Noida")).click();
            $("#submit").click();

    //Results Verification
            $(".modal-title").shouldHave(text("Thanks for submitting the form"));
            // simple and stupid: $$(".table-responsive tr").shouldHave(textsInAnyOrder("Values", "Female", "p.ann@i.ua","17 May,1995", "Anna Pedych", "0123456789","Maths", "Reading", "03163e85-a0d5-4174-bb83-8eac88234887.png", "123 Main St, App.123", "NCR Noida"));
            $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(textsInAnyOrder("Anna Pedych"));
            $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(textsInAnyOrder("p.ann@i.ua"));
            $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(textsInAnyOrder("Female"));
            $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(textsInAnyOrder("0123456789"));
            $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(textsInAnyOrder("17 May,1995"));
            $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(textsInAnyOrder("Maths, Computer Science"));
            $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(textsInAnyOrder("Reading"));
            $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(textsInAnyOrder("03163e85-a0d5-4174-bb83-8eac88234887.png"));
            $$(".table-responsive tr").filterBy(text("Address")).shouldHave(textsInAnyOrder("123 Main St, App.123"));
            $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(textsInAnyOrder("NCR Noida"));

    sleep(5000);
                }
}


