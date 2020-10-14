package model;

public class Node {
    public String name;
    public String value;
    public Node left, right;

    public Node(String name, String value) {
        this.name = name;
        this.value = value;
        this.left = this.right = null;
    }
}
