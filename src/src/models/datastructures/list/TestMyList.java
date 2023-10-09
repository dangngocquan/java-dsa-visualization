package src.models.datastructures.list;

public class TestMyList {
    public void run() {
        testArrayList();
        testSinglyLinkedList();
    }

    public void testArrayList() {
        System.out.println("\nTEST ARRAY LIST");
        MyList<Integer> list = new MyArrayList();
        testMyList(list);
    }

    public void testSinglyLinkedList() {
        System.out.println("\nTEST SINGLY LINKED LIST");
        MyList<Integer> list = new MySinglyLinkedList<>();
        testMyList(list);
    }

    public void testMyList(MyList<Integer> list) {
        System.out.println("\t" + header());
        System.out.println("\t" + dashes(104));
        testIsEmpty(list);
        testSize(list);
        testAdd(list, 1);
        testAdd(list, 2);
        testAdd(list, 0, 3);
        testAdd(list, 3, 4);
        testAdd(list, 1, 5);
        testAdd(list, 4, 6);
        testIsEmpty(list);
        testSize(list);
        testGet(list, 0);
        testGet(list, 5);
        testGet(list, 3);
        testSet(list, 0, 7);
        testSet(list, 5, 8);
        testSet(list, 2, 9);
        testRemove(list, 0);
        testRemove(list, 4);
        testRemove(list, 1);
        testRemove(list, Integer.valueOf(4));
        testRemove(list, Integer.valueOf(5));
        testRemove(list, Integer.valueOf(2));
        testRemove(list, Integer.valueOf(6));
        testAdd(list, 10);
        testRemove(list, Integer.valueOf(10));
        testAdd(list, 0, 11);
        testRemove(list, 0);

    }

    public String dashes(int length) {
        StringBuilder sb = new StringBuilder("");
        while (length-- > 0) sb.append("-");
        return sb.toString();
    }

    public String header() {
        return String.format(
                "%-25s  %-15s  %-60s",
                "Method executed",
                "Return",
                "List after execute method"
        );
    }




    // Test methods
    public void testSize(MyList<Integer> list) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                "size()",
                list.size(),
                list
        );
    }

    public void testAdd(MyList<Integer> list, Integer value) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("add(value=%s)", value),
                list.add(value),
                list
        );
    }

    public void testAdd(MyList<Integer> list, int index, Integer value) {
        list.add(index, value);
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("add(index=%d,value=%s)", index, value),
                "",
                list
        );
    }

    public void testGet(MyList<Integer> list, int index) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("get(index=%d)", index),
                list.get(index),
                list
        );
    }

    public void testSet(MyList<Integer> list, int index, Integer value) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("set(index=%d,value=%s)", index, value),
                list.set(index, value),
                list
        );
    }

    public void testRemove(MyList<Integer> list, int index) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("remove(index=%d)", index),
                list.remove(index),
                list
        );
    }

    public void testRemove(MyList<Integer> list, Integer value) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                String.format("remove(value=%s)", value),
                list.remove(value),
                list
        );
    }

    public void testIsEmpty(MyList<Integer> list) {
        System.out.printf(
                "\t%-25s  %-15s  %-60s\n",
                "isEmpty()",
                list.isEmpty(),
                list
        );
    }
}
