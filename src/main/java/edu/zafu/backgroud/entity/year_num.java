package edu.zafu.backgroud.entity;

public class year_num {
    private int year;
    private int num;

    @Override
    public String toString() {
        return "year_num{" +
                "year=" + year +
                ", num=" + num +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
