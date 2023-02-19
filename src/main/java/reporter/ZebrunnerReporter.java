package reporter;

import context.RunContext;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import model.HeadlessTestModel;
import model.SuiteModel;
import model.TestModel;
import model.TestResultModel;
import util.Configuration;

public class ZebrunnerReporter {

    Configuration configuration = new Configuration("src/main/resources/config.json");
    String testRunId="", testId="";

    public ZebrunnerReporter(){
        Unirest.config().defaultBaseUrl(configuration.getParam("host"));
        Unirest.config().addDefaultHeader("Connection", "close");
        Unirest.config().addDefaultHeader("Content-Type", "application/json");
        Unirest.config().addDefaultHeader("Accept", "application/json");
        Unirest.config().addDefaultHeader("Authorization", getToken());
    }
    private String getToken(){
        HttpResponse<String> response = Unirest.post("/api/iam/v1/auth/refresh").body("{\"refreshToken\": \""+configuration.getParam("token")+"\"}").asString();
        String token = getParam(response.getBody(), "authToken", false);
        return "Bearer "+token;
    }

    public void registerRunStart(SuiteModel model){
        HttpResponse<String> response = Unirest.post("/api/reporting/v1/test-runs?projectKey={projectKey}").
                routeParam("projectKey", configuration.getParam("projectKey")).
                body(model).asString();
        String zebrunnerTestRunId = getParam(response.getBody(), "id", true);
        RunContext.setTestRun(zebrunnerTestRunId);
    }

    public void registerRunFinish(String finishTime){
        testRunId = RunContext.getRunId();
        if(RunContext.hasTests()){
            System.out.println("!!!!!!!!!!!!!!!!!!!!! PIZDEC");
        }
        HttpResponse<String> response = Unirest.put("/api/reporting/v1/test-runs/"+testRunId).
                body("{\"endedAt\": \""+finishTime+"\"}").asString();
    }

//    public void registerHeadlessTestStart(String id, HeadlessTestModel testModel){
//        testRunId = RunContext.getRunId();
//        HttpResponse<String> response = Unirest.post("/api/reporting/v1/test-runs/{testRunId}/tests?headless=true").
//                routeParam("testRunId", testRunId).
//                body(testModel).asString();
//        String zebrunnerTestId = getParam(response.getBody(), "id", true);
//        RunContext.addTest(testId, zebrunnerTestId);
//        System.out.println("===========Oh Hello There=============");
//        System.out.println(response.getStatus());
//        System.out.println(response.getBody());
//        System.out.println(response.getRequestSummary().asString());
//        System.out.println(RunContext.getTestId(testId+":"+zebrunnerTestId));
//
//    }

    public void registerTestStart(String testId, TestModel model){
        testRunId = RunContext.getRunId();
//        if(RunContext.getCurrentTest().isPresent()){
//            System.out.println("===========Oh Hi There=============");
//            System.out.println(RunContext.getTestId(testId+":"+RunContext.getTestId(testId)));
//            HttpResponse<String> response = Unirest.put("/api/reporting/v1/test-runs/{testRunId}/tests/{testId}?headless=true").
//                    routeParam("testRunId", testRunId).routeParam("testId", RunContext.getTestId(testId)).
//                    body(model).asString();
//            System.out.println(response.getStatus());
//            System.out.println(response.getBody());
//            System.out.println(response.getRequestSummary().asString());
//
//        }
//        else{
            HttpResponse<String> response = Unirest.post("/api/reporting/v1/test-runs/{testRunId}/tests").
                    routeParam("testRunId", testRunId).
                    body(model).asString();
            String zebrunnerTestId = getParam(response.getBody(), "id", true);
            RunContext.addTest(testId, zebrunnerTestId);

//        }
    }

    public void registerTestFinish(String id, TestResultModel model){
        testRunId = RunContext.getRunId();
        HttpResponse<String> response = Unirest.put("/api/reporting/v1/test-runs/{testRunId}/tests/{testId}").
                routeParam("testRunId", testRunId).routeParam("testId", RunContext.getTestId(id)).
                body(model).asString();
        RunContext.removeTest(id);
    }

    private String getParam(String body, String paramName, boolean isInt){
        String tmp = "\""+paramName+"\"";
        int startIndex = body.indexOf(tmp)+(tmp+":\"").length();
        char delim = '\"';
        if(isInt) {startIndex-=1; delim = ',';}
        String result = body.substring(startIndex, body.indexOf(delim, startIndex));
        return result;
    }
}
