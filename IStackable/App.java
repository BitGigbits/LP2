import Stacks.ArrayStack;
import Stacks.LinkedStack;
import visible.IStackable;

public class App {
    public static void main(String[] args) {
        IStackable list1 = new LinkedStack();
        list1.push(5);
        list1.push(10);
        list1.push(999);
        int a = list1.pop();
        int sz = list1.size();
        System.out.format("Elemento Removido = %d | Tamanho: %d", a, sz);

        IStackable list2 = new ArrayStack();
        list2.push(7);
        list2.push(14);
        list2.push(21);
        int b = list2.pop();
        int sz2 = list2.size();
        System.out.format("\nElemento Removido = %d | Tamanho: %d", b, sz2);
    }
}
