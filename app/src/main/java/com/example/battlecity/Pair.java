package com.example.battlecity;

public class Pair <E,T>{
    private E e1;
    private T e2;
    public Pair (E e1,T e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public E getFirstElement() {
        return e1;
    }

    public void setFirstElement(E e1) {
        this.e1 = e1;
    }

    public T getSecondElement() {
        return e2;
    }

    public void setSecondElement(T e2) {
        this.e2 = e2;
    }
}
