package src.tests;

import src.models.datastructures.stack.ArrayStack;
import src.models.datastructures.stack.LinkedStack;
import src.models.datastructures.stack.StackInterface;

public class TestStack implements Test {
    @Override
    public void run() {
        testArrayStack();
        testLinkedListStack();
    }

    public void testArrayStack() {
        System.out.println("\nTEST ARRAY STACK");
        StackInterface<Integer> stack = new ArrayStack<>();
        testStack(stack);
    }

    public void testLinkedListStack() {
        System.out.println("\nTEST LINKED LIST STACK");
        StackInterface<Integer> stack = new LinkedStack<>();
        testStack(stack);
    }

    public void testStack(StackInterface<Integer> stack) {
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                "Action",
                "Return",
                "Stack"
        );
        System.out.println("\t---------------------------------------------------------");
        testEmpty(stack);
        testSize(stack);
        testPush(stack, 1);
        testPush(stack, 2);
        testPop(stack);
        testEmpty(stack);
        testTop(stack);
        testPush(stack, 3);
        testTop(stack);
        testPush(stack, 4);
        testPop(stack);


    }

    public void testPush(StackInterface<Integer> stack, Integer o) {
        stack.push(o);
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                String.format("stack.push(%s)", o),
                "",
                stack
        );
    }

    public void testPop(StackInterface<Integer> stack) {
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                "stack.pop()",
                stack.pop(),
                stack
        );
    }

    public void testTop(StackInterface<Integer> stack) {
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                "stack.top()",
                stack.top(),
                stack
        );
    }

    public void testSize(StackInterface<Integer> stack) {
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                "stack.size()",
                stack.size(),
                stack
        );
    }

    public void testEmpty(StackInterface<Integer> stack) {
        System.out.printf(
                "\t%-20s %-15s %-100s\n",
                "stack.isEmpty()",
                stack.isEmpty(),
                stack
        );
    }
}
