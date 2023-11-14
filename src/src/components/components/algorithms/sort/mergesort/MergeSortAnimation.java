package src.components.components.algorithms.sort.mergesort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSortAnimation extends AbstractSortAlgorithmAnimation {
    private final Queue<int[]>[] queues;
    private int indexQueue;
    private int[] range1; // [left, mid]
    private int[] range2; // [mid+1, right]
    private int i1; // index for range1
    private int i2; // index for range2
    private int i; // run in range [left, right] to merge range1 and range2
    private Bar[] subBars;
    private final Bar[] bars;

    public MergeSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        bars = rootScreen.getViewAction().bars;
        Queue<int[]> queue;
        Queue<int[]> nextQueue = new LinkedList<>();
        Queue<int[]> lastQueue = new LinkedList<>();
        int size = 0;
        nextQueue.add(new int[] {0, bars.length - 1});
        while (!nextQueue.isEmpty()) {
            size++;
            lastQueue = new LinkedList<>(nextQueue);
            queue = new LinkedList<>(nextQueue);
            nextQueue.clear();
            while (!queue.isEmpty()) {
                int[] range = queue.poll();
                int left = range[0];
                int right = range[1];
                int mid = (left + right) / 2;
                if (left == right) {
                    if (!nextQueue.isEmpty()) {
                        nextQueue.add(new int[]{left, left});
                        nextQueue.add(new int[]{right, right});
                    }
                } else {
                    nextQueue.add(new int[]{left, mid});
                    nextQueue.add(new int[]{mid + 1, right});
                }
            }
        }
        queues = new Queue[size];
        for (int i = 0; i < size; i++) {
            queues[i] = new LinkedList<>();
        }
        queues[size-1] = lastQueue;
        indexQueue = size - 1;
        subBars = null;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (subBars == null) {
                while (indexQueue < queues.length && queues[indexQueue].size() < 2) {
                    indexQueue++;
                }
                if (indexQueue >= queues.length) {
                    end();
                } else {
                    range1 = queues[indexQueue].poll();
                    range2 = queues[indexQueue].poll();
                    getRootScreen().getViewAction().setColorAndLocationBars(
                            range1[0],
                            range2[1],
                            Config.COLOR_BAR_FLAG,
                            ViewSortAlgorithmAction.initialY0
                    );
                    subBars = new Bar[range2[1] - range1[0] + 1];
                    i = range1[0];
                    i1 = range1[0];
                    i2 = range2[0];
                }
            } else {
                if (i > range2[1]) {
                    if (animationStep < subBars.length) {
                        int j = range1[0] + animationStep;
                        getRootScreen().getViewAction().setBar(j, subBars[animationStep]);
                        getRootScreen().getViewAction().pickDownBar(
                                j, ViewSortAlgorithmAction.initialY1
                        );
                        getRootScreen().getViewAction().checkBar(
                                j, Config.COLOR_BAR_TEMP_SORTED
                        );
                        animationStep++;
                    } else {
                        animationStep = 0;
                        subBars = null;
                        indexQueue--;
                        queues[indexQueue].add(new int[] {range1[0], range2[1]});
                    }
                } else {
                    if (i1 <= range1[1] && i2 <= range2[1]) {
                        if (bars[i1].compareTo(bars[i2]) <= 0) {
                            subBars[-range1[0] + i] = bars[i1];
                            getRootScreen().getViewAction().moveBar(
                                    i1,
                                    ViewSortAlgorithmAction.initialY0,
                                    i,
                                    ViewSortAlgorithmAction.initialY1
                            );
                            i1++;
                            i++;
                        } else {
                            subBars[-range1[0] + i] = bars[i2];
                            getRootScreen().getViewAction().moveBar(
                                    i2,
                                    ViewSortAlgorithmAction.initialY0,
                                    i,
                                    ViewSortAlgorithmAction.initialY1
                            );
                            i2++;
                            i++;
                        }
                    } else if (i1 <= range1[1]) {
                        subBars[-range1[0] + i] = bars[i1];
                        getRootScreen().getViewAction().moveBar(
                                i1,
                                ViewSortAlgorithmAction.initialY0,
                                i,
                                ViewSortAlgorithmAction.initialY1
                        );
                        i1++;
                        i++;
                    } else {
                        subBars[-range1[0] + i] = bars[i2];
                        getRootScreen().getViewAction().moveBar(
                                i2,
                                ViewSortAlgorithmAction.initialY0,
                                i,
                                ViewSortAlgorithmAction.initialY1
                        );
                        i2++;
                        i++;
                    }
                }
            }
        }
    }
}
