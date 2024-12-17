package com.miu.main.java.ai;

public class MyStack {
    Object[] stack;
    int i;

    public MyStack() {
        this.stack = new Object[10];
        i=0;
    }

    public void push(int value){
        if(i<10){
            stack[i]=value;
            i++;
        }

    }
    public void pop(){
        if(i>=0){
            stack[i]= null;
            i--;
        }

    }

    public Object peek(){
        if(i>=0){
            return stack[i];
        }
        return null;
    }


}
