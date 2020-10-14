package traverslstrategy;

import model.Node;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal implements TreeTraversal {
    @Override
    public void print(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node poped = queue.poll();
                System.out.print(poped.value + " ");
                if (poped.left != null)
                    queue.add(poped.left);
                if (poped.right != null)
                    queue.add(poped.right);
            }
        }
        System.out.println();
    }
}
