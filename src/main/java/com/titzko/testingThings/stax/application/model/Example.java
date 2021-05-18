package com.titzko.testingThings.stax.application.model;

import javax.persistence.*;

@Entity
public class Example {

    @SequenceGenerator(
            name="note_id",
            sequenceName = "note_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_id"
    )
    @Id
    private Long id;
    private String receiver;
    private String sender;
    private String heading;
    private String message;

    public Example(String receiver, String sender, String heading, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.heading = heading;
        this.message = message;
    }

    public Example() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String to) {
        this.receiver = to;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String from) {
        this.sender = from;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }
}
