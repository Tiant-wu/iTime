package com.example.itime;

public class Timer {
    private String title;
    private String ddl;
    private int coverResourceId;

    public Timer(String title, String ddl, int coverResourceId) {
        this.setTitle(title);
        this.setDdl(ddl);
        this.setCoverResourceId(coverResourceId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        this.coverResourceId = coverResourceId;
    }
}
