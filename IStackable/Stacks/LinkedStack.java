package Stacks;
import java.util.LinkedList;

import visible.IStackable;

public class LinkedStack implements IStackable {

    private LinkedList<Integer> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public void push(int v){
        list.addFirst(v);
    }

    public int pop(){
        return list.pop();
    }
}
