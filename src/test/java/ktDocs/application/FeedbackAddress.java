package ktDocs.application;

public class FeedbackAddress {
    private String address;
    private int feedbackTypeId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(int feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public FeedbackAddress(String address, int feedbackTypeId) {
        this.address = address;
        this.feedbackTypeId = feedbackTypeId;
    }

    public FeedbackAddress(String address) {
        this.address = address;
    }
}
