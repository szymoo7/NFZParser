module backend {
    exports pl.backend.client.enums;
    exports pl.backend.client;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires jdk.jdi;
    requires java.logging;

    exports pl.backend.client.pojos;
}