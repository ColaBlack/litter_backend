package edu.zafu.backgroud.entity;

public class type_num {
    private String type;
    private int num;

    @Override
    public String toString() {
        return "type_num{" +
                "type='" + type + '\'' +
                ", num=" + num +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
