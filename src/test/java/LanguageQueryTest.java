import org.testng.annotations.Test;

import org.testng.annotations.Test;

import javax.lang.model.type.ArrayType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LanguageQueryTest {
    @Test
    public void shouldReturnStatus200AndSuccesMessage(){
        given().
                header("Content-Type","application/json").
                body("{\"query\":\"query {\\r\\n    language(code: \\\"pt\\\"){\\r\\n        name\\r\\n        native\\r\\n        rtl\\r\\n    }\\r\\n}\",\"variables\":{}}").
        when().
                post(Constants.BASE_URL).
        then().
                assertThat().
                statusCode(200).
                assertThat().body(containsString(Constants.MESSAGE_SUCCESS_SHOULDRETURNSTATUS200ANDSUCCESMESSAGE));

    }

    @Test
    public void shouldReturnStatus400AndErrorMessageCodeRequired(){
        given().
                header("Content-Type","application/json").
                body("{\"query\":\"query {\\r\\n    language{\\r\\n        name\\r\\n        native\\r\\n        rtl\\r\\n    }\\r\\n}\",\"variables\":{}}").
        when().
                post(Constants.BASE_URL).
        then().
                assertThat().
                statusCode(400).
                assertThat().body(containsString(Constants.MESSAGE_ERROR_SHOULDRETURNSTATUS400ANDERRORMESSAGECODEREQUIRED));

    }

    @Test
    public void shouldReturnStatus200AndMessageNotContainLanguagePt(){
        given().
                header("Content-Type","application/json").
                body("{\"query\":\"query {\\r\\n  languages(filter: { code: {nin:[\\\"PT\\\"]} }) {\\r\\n    name\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{}}").
        when().
                post(Constants.BASE_URL).
        then().
                assertThat().
                statusCode(200).
                assertThat().body(containsString(Constants.MESSAGE_SUCCESS_SHOULDRETURNSTATUS200ANDMESSAGENOTCONTAINLANGUAGEPT));

    }

    @Test
    public void shouldReturnStatus400AndErrorMessageInvalidJson(){
        given().
                header("Content-Type","application/json").
                body("{\"query\":\"query {\\r\\n  languages(filter: { code: nin:[\\\"PT\\\"] }) {\\r\\n    name\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{}}").
        when().
                post(Constants.BASE_URL).
        then().
                assertThat().
                statusCode(400).
                assertThat().body(containsString(Constants.MESSAGE_SUCCESS_SHOULDRETURNSTATUS400ANDERRORMESSAGEINVALIDJSON));

    }

}
