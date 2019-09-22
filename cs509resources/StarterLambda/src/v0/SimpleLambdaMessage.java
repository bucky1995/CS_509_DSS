package v0;

public class SimpleLambdaMessage {
    private String mMessage;

    /**
     * @param message the message contents for the object
     */
    public void setMessage(final String message) {
        this.mMessage = message;
    }

    /**
     * @return the message string
     */
    public String getMessage() {
        return this.mMessage;
    }
    
    @Override
    public String toString() {
        return this.mMessage;
    }
}