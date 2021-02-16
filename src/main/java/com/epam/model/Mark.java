package com.epam.model;

import java.time.LocalDate;

/**
 * Mark model.
 *
 * @author M.Sokil
 *
 */

public class Mark {
    private int mark;
    private LocalDate dateMark;

    public Mark() {
    }

    /*public Mark(int mark) {
        this.mark = mark;
    }

    public Mark(int mark, String dateMark) {
        this.mark = mark;
        this.dateMark = dateMark;
    }

    public int getMark(int mark) {
        switch (mark>0 && mark<=5){
            case 1: return "Course completed unsuccessfully";
            case 2: return "Course completed unsuccessfully";
            case 3: return "Course completed unsuccessfully";
            case 4: return "Course completed successfully";
            case 5: return "Course completed successfully";
            default: return "";

        }
        return mark;
    }
    public String getDateMark() { return dateMark; }

    public void setMark(long mark) {
        this.mark = mark;
    }
    public void setDateMark(String dateMark) { this.dateMark = dateMark; }*/
}
