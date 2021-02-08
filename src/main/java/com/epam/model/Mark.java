package com.epam.model;
/**
 * Mark model.
 *
 * @author M.Sokil
 *
 */

public class Mark {
    private long mark;
    private String dateMark;

    public Mark() {
    }

    public Mark(long mark) {
        this.mark = mark;
    }

    public Mark(long mark, String dateMark) {
        this.mark = mark;
        this.dateMark = dateMark;
    }

    public long getMark() {
        return mark;
    }
    public String getDateMark() { return dateMark; }

    public void setMark(long mark) {
        this.mark = mark;
    }
    public void setDateMark(String dateMark) { this.dateMark = dateMark; }
}
