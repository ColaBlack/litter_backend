package edu.zafu.backgroud.entity;

public class word_num {
    private String word;
    private int num;

    @Override
    public String toString() {
        return "word_num{" +
                "word='" + word + '\'' +
                ", num=" + num +
                '}';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
