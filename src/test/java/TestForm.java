import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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

            sleep(500);

}
}
