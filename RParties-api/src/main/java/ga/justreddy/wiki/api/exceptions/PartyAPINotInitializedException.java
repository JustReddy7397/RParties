package ga.justreddy.wiki.api.exceptions;

public class PartyAPINotInitializedException extends RuntimeException {
    public PartyAPINotInitializedException() {
        super("PartyAPI is not initialized!");
    }
}
