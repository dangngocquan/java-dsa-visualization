package src.components.components.datastructures;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractPanelDataStructureNode
        extends Panel {
    protected int index;
    protected int value;
    protected int widthNode;
    protected int heightNode;
    public int[] prevArrow = null; // [xStart, yStart, xEnd, yEnd]
    public int[] nextArrow = null; // [xStart, yStart, xEnd, yEnd]

    public AbstractPanelDataStructureNode(int index, int value, int width, int height) {
        super(0, 0, width, height, Config.COLOR_BAR_PLAIN, null, "", 0);
        this.index = index;
        this.value = value;
        this.widthNode = width;
        this.heightNode = height;
        setXY(createX(), createY());
    }

    public abstract int createX();
    public abstract int createY();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPrevArrow(int xStart, int yStart, int xEnd, int yEnd) {
        if (prevArrow == null) prevArrow = new int[4];
        prevArrow[0] = xStart;
        prevArrow[1] = yStart;
        prevArrow[2] = xEnd;
        prevArrow[3] = yEnd;
    }

    public void setPrevArrow(int[] data) {
        if (prevArrow == null) prevArrow = new int[4];
        prevArrow[0] = data[0];
        prevArrow[1] = data[1];
        prevArrow[2] = data[2];
        prevArrow[3] = data[3];
    }

    public void setNextArrow(int xStart, int yStart, int xEnd, int yEnd) {
        if (nextArrow == null) nextArrow = new int[4];
        nextArrow[0] = xStart;
        nextArrow[1] = yStart;
        nextArrow[2] = xEnd;
        nextArrow[3] = yEnd;
    }

    public void setNextArrow(int[] data) {
        if (nextArrow == null) nextArrow = new int[4];
        nextArrow[0] = data[0];
        nextArrow[1] = data[1];
        nextArrow[2] = data[2];
        nextArrow[3] = data[3];
    }

    public abstract int[] getDefaultPrevArrow();
    public abstract int[] getDefaultNextArrow();
}