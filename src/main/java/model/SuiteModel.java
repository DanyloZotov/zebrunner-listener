package model;

import java.time.OffsetDateTime;

public class SuiteModel {
    private String name;
    private String startedAt;
    private String framework;

    public SuiteModel(String name, OffsetDateTime startedAt, String framework){
        this.name = name;
        this.startedAt = startedAt.toString();
        this.framework = framework;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }


}
