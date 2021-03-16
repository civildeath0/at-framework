package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public final class FileSteps {

    @И("загружаю в поле {string} валидную картинку {string}")
    public void uploadFile(String fieldName, String fileName) {
        getUploadField(fieldName)
                .uploadFile(new File(format("src/test/resources/files/valid/images/%s", fileName)));
    }

    @И("картинка успешно загрузилась в поле {string}")
    public void imageUploaded(String fieldName) {
        getUploadField(fieldName).should(Condition.image);
    }

    private SelenideElement getUploadField(String fieldName) {
        return $x(format("//span[contains(text(), '%s')]/following-sibling::div/descendant::input", fieldName));
    }

    @И("загружаю валидную картинку {string}")
    public void uploadFile(String fileName) {
        $x("//input[@accept='image/*']").
                uploadFile(new File(format("src/test/resources/files/valid/images/%s", fileName)));
    }

    @И("выбираю для загрузки материал {string}")
    public void uploadFileMaterial(String filePath){
        $x("//input[@id='fileUpload']").
                uploadFile(new File(format("src/test/resources/files/valid/%s", filePath)));
    }
}
