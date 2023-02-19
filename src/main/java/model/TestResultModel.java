package model;

import java.time.OffsetDateTime;

public class TestResultModel {

    String result;
    String reason;
    String endedAt;

    public TestResultModel(String result, String reason, OffsetDateTime endedAt){
        this.result = result;
        this.reason = reason;
        this.endedAt = endedAt.toString();
    }

    public TestResultModel(String result, OffsetDateTime endedAt){
        this.result = result;
        this.endedAt = endedAt.toString();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
