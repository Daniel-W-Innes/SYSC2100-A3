import java.util.LinkedList;

class StackListBased<E> {
    private LinkedList<E> items;

    StackListBased(){
        items = new LinkedList<>();
    }

    void createStack() {
        items = new LinkedList<>();
    }


    void popAll() {
        items.clear();
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    void push(E obj) {
        items.add(0,obj);
    }

    E pop() {
        return items.pop();
    }

    E peek() {
        return items.peek();
    }
}
