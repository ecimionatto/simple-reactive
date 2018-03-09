package domain;

public class Request {

    private String UUID;

    public Request(final String uuid) {
        UUID = uuid;
    }

    public String getUUID() {
        return UUID;
    }
}
