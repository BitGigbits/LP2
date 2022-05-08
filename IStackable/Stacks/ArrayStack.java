package Stacks;
import java.util.ArrayList;
import visible.IStackable;

public class ArrayStack implements IStackable {

    private ArrayList<Integer> list = new ArrayList<>();

    public int size(){
        return list.size();
    }

    public void push(int v){
        list.add(0, v);
    }

    public int pop(){
        int y = list.get(0);
        list.remove(0);
        return y;
    }
    
}
