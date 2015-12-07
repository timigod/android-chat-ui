package co.devcenter.androiduilibrary.models;

import java.util.Date;

/**
 * Created by timi on 27/11/2015.
 */
public class ChatMessage {
    private String message;
    private long timestamp;
    private Status status;

    public enum Status{
        SENT, RECEIVED
    }

    public ChatMessage(String message, long timestamp, Status status){
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
