package ch04.ex05;

public abstract class Tree implements Drawable{

    TreeNode top;

    public void add(Object value) {
        if (top == null) {
            top = new TreeNode(value);
        }


    }

    @Override
    public void draw() {

    }

    public abstract void walk();

}
