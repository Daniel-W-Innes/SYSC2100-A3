import java.util.LinkedList;

class StackListBased<E> {
    private LinkedList<E> items;
    //Notice top Variable removed from UML to improve performance and because it is useless.

    /**
     * Construct StackListBased and instance items to a new linkList
     */
    StackListBased() {
        items = new LinkedList<>();
    }

    /**
     * Useless method required by UML
     */
    void createStack() {
        items = new LinkedList<>();
    }

    /**
     * Remove all items from items
     */
    void popAll() {
        items.clear();
    }

    /**
     * Determine if items is empty
     * @return Return true if items is empty
     */
    boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Add a new item, obj, to items
     * @param obj The item to add
     */
    void push(E obj) {
        items.add(0, obj);
    }

    /**
     * Remove and return the top item from items
     * @return the top items
     */
    E pop() {
        return items.pop();
    }

    /**
     * Return the top item from items
     * @return the top items
     */
    E peek() {
        return items.peek();
    }
}
