package com.example.cmamapplication;

public class Test {
    String biodata_id, test, metric, metric_score, result, date_created;

    public Test() {
    }

    public String getBiodata_id() {
        return biodata_id;
    }

    public void setBiodata_id(String biodata_id) {
        this.biodata_id = biodata_id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getMetric_score() {
        return metric_score;
    }

    public void setMetric_score(String metric_score) {
        this.metric_score = metric_score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Test(String biodata_id, String test, String metric, String metric_score, String result, String date_created) {
        this.biodata_id = biodata_id;
        this.test = test;
        this.metric = metric;
        this.metric_score = metric_score;
        this.result = result;
        this.date_created = date_created;
    }
}
