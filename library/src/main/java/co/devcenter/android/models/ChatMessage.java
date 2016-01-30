package co.devcenter.android.models;


import android.text.format.DateFormat;

/**
 * Created by timi on 27/11/2015.
 */
public class ChatMessage {
    private String message;
    private long timestamp;
    private Status status;

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

    public String getFormattedTime(){

        long oneDayInMillis = 24 * 60 * 60 * 1000;
        long timeDifference = System.currentTimeMillis() - timestamp;


        if( timeDifference < oneDayInMillis ) {
            return DateFormat.format("hh:mm a", timestamp).toString();
        }else{
            return DateFormat.format("dd MMM - hh:mm a", timestamp).toString();
        }
    }

    public enum Status {
        SENT, RECEIVED
    }
}
