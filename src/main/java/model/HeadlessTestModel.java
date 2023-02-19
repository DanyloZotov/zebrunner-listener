package model;

import java.time.OffsetDateTime;

public class HeadlessTestModel {
    private String startedAt;

    public HeadlessTestModel(OffsetDateTime startedAt){
        this.startedAt = startedAt.toString();
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }
}
