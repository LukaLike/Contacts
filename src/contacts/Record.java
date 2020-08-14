package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

abstract class Record implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String number;
    private final LocalDateTime lastCreated;
    private LocalDateTime lastEdited;

    Record() {
        lastCreated = LocalDateTime.now().withSecond(0).withNano(0);
        lastEdited = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (!isPhoneValid(number)) {
            this.number = "[no number]";
            System.out.println("Wrong number format!");
        } else {
            this.number = number;
        }
    }

    static boolean isPhoneValid(String number) {
        return number.matches("\\+?(\\w+)?(\\s|\\w{2,}|-|)+|" +
                "\\+?\\((\\w+|-)\\)((\\s|-|\\w{2,})*)?|" +
                "\\+?\\w+(\\s|-)?\\((-|\\w{2,})\\)((\\s|-|\\w{2,})*)?");
    }

    public LocalDateTime getLastCreated() {
        return lastCreated;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    abstract String getInfo();

    abstract String getDetailedInfo();

    abstract String getFields();

    abstract void changeField(String field, String value);

    abstract String getField(String field);
}
