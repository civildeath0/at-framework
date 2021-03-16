package ru.sberbank.smartoffice.at.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Synchronized;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class Specificator {

    private static RequestSpecification requestSpecification = null;

    public static RequestSpecification getBaseSpecification() {
        if (requestSpecification == null) {
            synchronizeInitConfig();
        }
        return given().spec(requestSpecification);
    }

    @Synchronized
    private static void synchronizeInitConfig() {
        if (requestSpecification == null) {
            requestSpecification = setConfig();
        }
    }

    private static RequestSpecification setConfig() {
        return new RequestSpecBuilder()
                .setConfig(newConfig()
                        .decoderConfig(decoderConfig()
                                .defaultContentCharset(UTF_8)))
                .setBaseUri("https://ift2-app2.smartoffice.sberbank.ru")
                .setPort(17443)
                .build();
    }
}
