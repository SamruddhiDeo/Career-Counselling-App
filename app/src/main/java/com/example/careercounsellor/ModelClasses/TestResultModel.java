package com.example.careercounsellor.ModelClasses;

public class TestResultModel {
    String col1,col2,col3,col4,suggestion;
    Double percentCol1, percentCol2, percentCol3, percentCol4;

    public TestResultModel(String col1, String col2, String col3, String col4, Double percentCol1, Double percentCol2, Double percentCol3, Double percentCol4, String suggestion) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.suggestion = suggestion;
        this.percentCol1 = percentCol1;
        this.percentCol2 = percentCol2;
        this.percentCol3 = percentCol3;
        this.percentCol4 = percentCol4;
    }

    public TestResultModel() {

    }

    public Double getPercentCol1() {
        return percentCol1;
    }

    public void setPercentCol1(Double percentCol1) {
        this.percentCol1 = percentCol1;
    }

    public Double getPercentCol2() {
        return percentCol2;
    }

    public void setPercentCol2(Double percentCol2) {
        this.percentCol2 = percentCol2;
    }

    public Double getPercentCol3() {
        return percentCol3;
    }

    public void setPercentCol3(Double percentCol3) {
        this.percentCol3 = percentCol3;
    }

    public Double getPercentCol4() {
        return percentCol4;
    }

    public void setPercentCol4(Double percentCol4) {
        this.percentCol4 = percentCol4;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
