package pl.backend.client.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue {
    private UUID id;
    private QueueAttributes attributes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QueueAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(QueueAttributes attributes) {
        this.attributes = attributes;
    }
}
