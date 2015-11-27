package co.devcenter.androiduilibrary.models;

import java.util.Date;

/**
 * Created by timi on 27/11/2015.
 */
public class ChatMessage {
    private String message;
    private long timestamp;

    public ChatMessage(String message, long timestamp){
        this.message = message;
        this.timestamp = timestamp;
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

}
