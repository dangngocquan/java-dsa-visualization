package src.models.datastructures.queue.queue;

public class TestQueue {
    public void run() {
        QueueInterface<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue);
    }
}
