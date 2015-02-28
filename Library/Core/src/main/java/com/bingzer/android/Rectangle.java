package com.bingzer.android;

@SuppressWarnings("unused")
public class Rectangle {
    public final Point leftTop = new Point();
    public final Point rightBottom = new Point();

    public Rectangle(){
        this(0, 0, 0, 0);
    }

    public Rectangle(Point leftTop, Point rightBottom){
        this.leftTop.x = leftTop.x;
        this.leftTop.y = leftTop.y;
        this.rightBottom.x = rightBottom.x;
        this.rightBottom.y = rightBottom.y;
    }

    public Rectangle(float left, float top, float right, float bottom){
        leftTop.x = left;
        leftTop.y = top;
        rightBottom.x = right;
        rightBottom.y = bottom;
    }

    public boolean contains(float x, float y){
        boolean containsX = x >= leftTop.x && x <= rightBottom.x;
        boolean containsY = y >= leftTop.y && y <= rightBottom.y;
        return containsX && containsY;
    }

    public Point getCenterPoint(){
        float centerX = (rightBottom.x - leftTop.x) / 2;
        float centerY = (rightBottom.y - leftTop.y) / 2;
        return new Point(centerX, centerY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        return leftTop.equals(rectangle.leftTop) && rightBottom.equals(rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        int result = leftTop.hashCode();
        result = 31 * result + rightBottom.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "leftTop=" + leftTop + ", rightBottom=" + rightBottom + '}';
    }
}
