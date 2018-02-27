package com.petertailor.belsokonyveles.service;

public class QueryString {

    private String typeQuery;
    private String parterQuery;
    private String dateQuery;

    public QueryString() {
    }

    public QueryString(String typeQuery, String parterQuery, String dateQuery) {
        this.typeQuery = typeQuery;
        this.parterQuery = parterQuery;
        this.dateQuery = dateQuery;
    }

    public String getTypeQuery() {
        return typeQuery;
    }

    public void setTypeQuery(String typeQuery) {
        this.typeQuery = typeQuery;
    }

    public String getParterQuery() {
        return parterQuery;
    }

    public void setParterQuery(String parterQuery) {
        this.parterQuery = parterQuery;
    }

    public String getDateQuery() {
        return dateQuery;
    }

    public void setDateQuery(String dateQuery) {
        this.dateQuery = dateQuery;
    }

    @Override
    public String toString() {
        return "QueryString{" +
                "typeQuery='" + typeQuery + '\'' +
                ", parterQuery='" + parterQuery + '\'' +
                ", dateQuery='" + dateQuery + '\'' +
                '}';
    }
}
