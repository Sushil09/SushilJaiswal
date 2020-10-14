package subjects;

import model.Node;
import observers.IObserver;
import observers.ObserverImpl;

import java.util.ArrayList;
import java.util.List;

public class OperationImpl implements Operation {
    private Node root; //holding root node

    IObserver obersever;// reference to observer

    public Node getRoot() {
        return root;
    }

    @Override
    public synchronized void create(String path, String data) {
        String name = path.substring(path.lastIndexOf('/') + 1, path.length());// name of nodes

        if (root == null) {
            root = new Node(name, data);
            return;
        } else {
            Node parent = travelDistance(root, path);//fetching parent of node
            if (parent != null) {
                int lastNumber = Integer.parseInt(path.substring(path.length() - 1)); //subChild(2)->fetching last value
                if (lastNumber == 1)
                    parent.left = new Node(name, data);
                else
                    parent.right = new Node(name, data);
            }
        }

        //calling observer if it is called
        if (obersever != null)
            updateObserver('c', path, name);
    }

    @Override
    public synchronized void update(String path, String data) {
        String name = path.substring(path.lastIndexOf('/') + 1, path.length());// name of nodes

        Node parent = travelDistance(root, path);//fetching parent of node
        if (parent == root && !Character.isDigit(path.charAt(path.length() - 1))) {
            parent.value = data;
        } else {
            if (parent != null) {
                int lastNumber = Integer.parseInt(path.substring(path.length() - 1)); //subChild(2)->fetching last value
                if (lastNumber == 1)
                    parent.left.value = data;
                else
                    parent.right.value = data;
            }
        }

        //calling observer if registered method was called
        if (obersever != null)
            updateObserver('u', path, name);
    }

    @Override
    public synchronized void delete(String path) {
        String name = path.substring(path.lastIndexOf('/') + 1, path.length());// name of nodes

        Node parent = travelDistance(root, path);//fetching parent of node
        if (parent == root && !Character.isDigit(path.charAt(path.length() - 1))) {
            root = null;
        } else {
            int lastNumber = Integer.parseInt(path.substring(path.length() - 1)); //subChild(2)->fetching last value
            if (lastNumber == 1)
                parent.left = null;
            else
                parent.right = null;
        }

        //calling observer if registered method was called
        if (obersever != null)
            updateObserver('d', path, name);
    }

    @Override
    public String get(String path) {
        Node parent = travelDistance(root, path);//fetching parent of node
        if (parent == root && !Character.isDigit(path.charAt(path.length() - 1)))    // check if
            return root.value;
        else {
            int lastNumber = Integer.parseInt(path.substring(path.length() - 1)); //subChild(2)->fetching last value
            return lastNumber == 1 ? parent.left.value : parent.right.value;
        }
    }

    @Override
    public List<Node> list(String path) {
        List<Node> childNodes = new ArrayList<>();
        Node parent = travelDistance(root, path);

        //to check if the its root or any child of root
        if (parent != null && Character.isDigit(path.charAt(path.length() - 1))) {
            int lastNumber = Integer.parseInt(path.substring(path.length() - 1));
            parent = lastNumber == 1 ? parent.left : parent.right;
        }
        if (parent != null && parent.left != null)
            childNodes.add(parent.left);
        if (parent != null && parent.right != null)
            childNodes.add(parent.right);
        return childNodes;
    }

    public Node travelDistance(Node root, String path) {
        String[] edges = path.split("/");
        int size = edges.length - 1; // to get rid of first empty space in array
        Node temp = root;
        for (int i = 2; i < size; i++) {
            String edge = edges[i];
            int lastNumber = Integer.parseInt(edge.substring(edge.length() - 1));
            if (lastNumber == 1)
                temp = temp.left;
            else
                temp = temp.right;
        }
        return temp;
    }

    //registering the observer
    public void registerObserver() {
        obersever = new ObserverImpl();
    }

    //calling observer
    public void updateObserver(char type, String path, String name) {
        obersever.update(type, path, name);
    }
}
