package ktDocs.application;

import java.util.List;

public class ApplicationBody {
    private List<AdditionalContent> additionalContent;
    private int assignedEmployeeId;
    private CustomerData customerData;
    private List<FeedbackAddress> feedbackAddress;
    private int serviceLanguageId;
    private int templateVersionId;
    private int typeId;

    public ApplicationBody(List<AdditionalContent> additionalContent,
                           int assignedEmployeeId,
                           CustomerData customerData,
                           List<FeedbackAddress> feedbackAddress,
                           int serviceLanguageId,
                           int templateVersionId,
                           int typeId
    ) {
        this.additionalContent = additionalContent;
        this.assignedEmployeeId = assignedEmployeeId;
        this.customerData = customerData;
        this.feedbackAddress = feedbackAddress;
        this.serviceLanguageId = serviceLanguageId;
        this.templateVersionId = templateVersionId;
        this.typeId = typeId;
    }

    public List<AdditionalContent> getAdditionalContent() {
        return additionalContent;
    }

    public void setAdditionalContent(List<AdditionalContent> additionalContent) {
        this.additionalContent = additionalContent;
    }

    public int getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(int assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public List<FeedbackAddress> getFeedbackAddressBody() {
        return feedbackAddress;
    }

    public void setFeedbackAddressBody(List<FeedbackAddress> feedbackAddress) {
        this.feedbackAddress = feedbackAddress;
    }

    public int getServiceLanguageId() {
        return serviceLanguageId;
    }

    public void setServiceLanguageId(int serviceLanguageId) {
        this.serviceLanguageId = serviceLanguageId;
    }

    public int getTemplateVersionId() {
        return templateVersionId;
    }

    public void setTemplateVersionId(int templateVersionId) {
        this.templateVersionId = templateVersionId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


}
