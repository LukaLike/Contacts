package contacts;


public class Organization extends Record {
    private String address;

    public Organization() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getInfo() {
        return String.format("%s %s", getName(), address);
    }

    @Override
    public String getDetailedInfo() {
        return getName() + " " + getAddress() + " " + getNumber();
    }

    @Override
    public String getFields() {
        return "name, address, number";
    }

    @Override
    void changeField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "address":
                setAddress(value);
                break;
            case "number":
                setNumber(value);
                break;
        }
    }

    @Override
    String getField(String field) {
        switch (field) {
            case "name":
                return getName();
            case "address":
                return getAddress();
            case "number":
                return getNumber();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                getName(), address, getNumber(), getLastCreated(), getLastEdited());
    }
}
