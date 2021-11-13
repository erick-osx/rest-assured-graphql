import org.testng.annotations.Test;

import javax.lang.model.type.ArrayType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CountryQueryTest {

    @Test
    public void shouldReturnStatus200(){
        given().
                header("Content-Type","application/json").
                body("{\"query\":\"query {\\n  country(code: \\\"BR\\\") {\\n    name\\n    native\\n    capital\\n    emoji\\n    currency\\n    languages {\\n        code\\n        name\\n    }\\n  }\\n}\",\"variables\":{}}").

        when().
                post(Constants.BASE_URL).
        then().
                assertThat().
                statusCode(200).
                assertThat().body(containsString("{\"data\":{\"country\":{\"name\":\"Brazil\",\"native\":\"Brasil\",\"capital\":\"Brasília\",\"emoji\":\"\uD83C\uDDE7\uD83C\uDDF7\",\"currency\":\"BRL\",\"languages\":[{\"code\":\"pt\",\"name\":\"Portuguese\"}]}}}"));
                //assertThat().body("languages", );

    }
}

//curl --location --request POST 'https://countries.trevorblades.com/' \
//        --header 'Accept-Encoding: gzip, deflate, br' \
//        --header 'Content-Type: application/json' \
//        --header 'Accept: application/json' \
//        --header 'Connection: keep-alive' \
//        --header 'DNT: 1' \
//        --header 'Origin: https://countries.trevorblades.com' \
//        --data-raw '{"query":"query {\n  country(code: \"BR\") {\n    name\n    native\n    capital\n    emoji\n    currency\n    languages {\n        code\n        name\n    }\n  }\n}","variables":{}}'
//
