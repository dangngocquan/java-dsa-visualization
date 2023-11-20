package src.tests;

import src.models.datastructures.priorityqueue.*;

public class TestPriorityQueue implements Test {
    @Override
    public void run() {
        System.out.println("\n\nTEST UNSORTED ARRAY PRIORITY QUEUE");
        testPQ(new UnsortedArrayPriorityQueue<>());

        System.out.println("\n\nTEST SORTED ARRAY PRIORITY QUEUE");
        testPQ(new SortedArrayPriorityQueue<>());

        System.out.println("\n\nTEST UNSORTED LINKED PRIORITY QUEUE");
        testPQ(new UnsortedLinkedPriorityQueue<>());

        System.out.println("\n\nTEST SORTED LINKED PRIORITY QUEUE");
        testPQ(new SortedLinkedPriorityQueue<>());
    }
    public void testPQ(PriorityQueueInterface<Integer, Integer> queue) {
        header();
        testSize(queue);
        testIsEmpty(queue);
        testInsert1(queue, new AbstractPriorityQueue.Entry<>(10, 10));
        testInsert2(queue, 4, 4);
        testInsert2(queue, 7, 7);
        testMin(queue);
        testRemoveMin(queue);
        testInsert2(queue, 1, 1);
        testInsert2(queue, 5, 5);
        testInsert2(queue, 3, 3);
        testSize(queue);
        testIsEmpty(queue);
        testMin(queue);
        testRemoveMin(queue);
        testRemoveMin(queue);
        testRemoveMin(queue);
        testRemoveMin(queue);
    }

    public void printNameTest() {
        System.out.println("\nTEST 1");
    }

    public void header() {
        this.printNameTest();
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                "Action",
                "Return",
                "Priority queue after action"
        );
        System.out.println("-".repeat(134));
    }

    public void testSize(PriorityQueueInterface<Integer, Integer> queue) {
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                "size()",
                queue.size(),
                queue
        );
    }

    public void testIsEmpty(PriorityQueueInterface<Integer, Integer> queue) {
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                "isEmpty()",
                queue.isEmpty(),
                queue
        );
    }

    public void testInsert1(
            PriorityQueueInterface<Integer, Integer> queue,
            EntryInterface<Integer, Integer> entry) {
        queue.insert(entry);
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                String.format("insert(%s)", entry),
                "",
                queue
        );
    }

    public void testInsert2(
            PriorityQueueInterface<Integer, Integer> queue,
            Integer key, Integer value) {
        queue.insert(key, value);
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                String.format("insert(key=%s, value=%s)", key, value),
                "",
                queue
        );
    }

    public void testMin(PriorityQueueInterface<Integer, Integer> queue) {
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                "min()",
                queue.min(),
                queue
        );
    }

    public void testRemoveMin(PriorityQueueInterface<Integer, Integer> queue) {
        System.out.printf(
                "%-40s  %-30s  %-60s\n",
                "removeMin()",
                queue.removeMin(),
                queue
        );
    }
}
