package edu.zafu.backgroud.entity;

public class department_num {
    private String department;
    private int num;

    @Override
    public String toString() {
        return "department_num{" +
                "department='" + department + '\'' +
                ", num=" + num +
                '}';
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
