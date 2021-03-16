package ru.sberbank.smartoffice.at.users;

import lombok.NonNull;
import lombok.Synchronized;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.users.csvreader.CSVReader;
import ru.sberbank.smartoffice.at.users.userparsers.AdminParser;
import ru.sberbank.smartoffice.at.users.userparsers.AssistantParser;
import ru.sberbank.smartoffice.at.users.userparsers.ChiefParser;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

/**
 * <h1>Центральная точка хранения и получения пользователей</h1>
 */
public final class SmartUsers {

    private static SmartUsers smartUsers;

    private final List<SmartAdmin> smartAdmins;
    private final List<SmartAssistant> smartAssistants;
    private final List<SmartChief> smartChiefs;

    /**
     * Конструктор класса {@link SmartUsers}.
     * <p>
     * Во время инициализации происходит чтение файлов, хранящих
     * данные пользователей системы.
     */
    private SmartUsers() {
        String basePath = "src/test/resources/users";
        smartAdmins = new CSVReader(new File(basePath, "admins.csv"))
                .parseWith(new AdminParser());
        smartAssistants = new CSVReader(new File(basePath, "assistants.csv"))
                .parseWith(new AssistantParser());
        smartChiefs = new CSVReader(new File(basePath, "chiefs.csv"))
                .parseWith(new ChiefParser());
        smartAssistants.forEach(this::linkAssistant);
    }

    /**
     * Метод, реализующий
     * <a href=https://habr.com/ru/post/116577/>singleton</a>, для класса {@link SmartUsers}.
     *
     * @return экземпляр класса {@link SmartUsers}
     */
    private static SmartUsers getInstance() {
        if (smartUsers == null) {
            init();
        }
        return smartUsers;
    }

    /**
     * {@link Synchronized} метод, создающий
     * экземпляр класса {@link SmartUsers}  и присваивающий
     * его <code>static</code> переменной.
     */
    @Synchronized
    private static void init() {
        if (smartUsers == null) {
            smartUsers = new SmartUsers();
        }
    }

    /**
     * Возвращает {@link Stream} руководителей.
     *
     * @return {@link Stream} руководителей.
     */
    @NotNull
    public static Stream<SmartChief> chiefsStream() {
        return getInstance().smartChiefs.stream();
    }

    /**
     * Возвращает {@link Stream} помощников.
     *
     * @return {@link Stream} помощников.
     */
    @NonNull
    public static Stream<SmartAssistant> assistantsStream() {
        return getInstance().smartAssistants.stream();
    }

    /**
     * Возвращает {@link Stream} администраторов.
     *
     * @return {@link Stream} администраторов.
     */
    @NonNull
    public static Stream<SmartAdmin> administratorsStream() {
        return getInstance().smartAdmins.stream();
    }

    /**
     * Возвращает {@link Stream} пользователей.
     *
     * @return {@link Stream} пользователей.
     */
    public static Stream<SmartUser> userStream() {
        SmartUsers smartUsers = getInstance();
        return of(smartUsers.smartAdmins, smartUsers.smartAssistants, smartUsers.smartChiefs)
                .flatMap(Collection::stream);
    }

    /**
     * Возвращает первого найденого шефа с
     * переданным <code>id</code>.
     *
     * @param id который должен быть у
     *           искомого {@link SmartChief}
     *
     * @return первый найденный {@link SmartChief} с
     * искомым <code>id</code>
     */
    private SmartChief getChief(String id) {
        return smartChiefs.stream().filter(smartChief ->
                smartChief.getId().equals(id)).findFirst().orElseThrow(() -> {
            throw new NoSuchElementException(format("Руководитель с id \"%s\" не найден", id));
        });
    }

    /**
     * Соединяет переданного помощника с его руководителем в соответствии
     * со значением полученном при вызове метода {@link SmartAssistant#getChiefId()}.
     *
     * @param smartAssistant {@link SmartAssistant}, которого
     *                       нужно соединить с его шефом
     */
    private void linkAssistant(SmartAssistant smartAssistant) {
        getChief(smartAssistant.getChiefId()).addAssistant(smartAssistant);
    }
}
