package ru.sberbank.smartoffice.at.steps.types;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;

import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.utilities.Stationery.attachText;

@NoArgsConstructor
public final class SchemaType {

    @DocStringType @SneakyThrows
    public JsonNode jsonSchema(String jsonSchema) {
        return validateSchema(JsonLoader.fromString(jsonSchema));
    }

    @ParameterType("\"(\\w+)\"") @SneakyThrows
    public JsonNode jsonSchemaFile(String fileName) {
        File file = new File("src/test/resources/schemas",
                format("%s.json", fileName));
        return validateSchema(JsonLoader.fromFile(file));
    }

    private JsonNode validateSchema(JsonNode jsonSchema) {
        ProcessingReport report = JsonSchemaFactory.byDefault()
                .getSyntaxValidator().validateSchema(jsonSchema);
        attachText("Schema", jsonSchema.toPrettyString());
        if (!report.isSuccess()) {
            attachText("Report", report.toString());
            throw new IllegalArgumentException("Невалидная JsonSchema! Проверка невозможна!");
        }
        return jsonSchema;
    }
}
