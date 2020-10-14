import model.Node;
import subjects.Operation;
import subjects.OperationImpl;
import traverslstrategy.LevelOrderTraversal;
import traverslstrategy.TreeTraversal;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        OperationImpl root = new OperationImpl();

        // 1)creating root node with data=nothing
        root.create("/root", "nothing");
        System.out.println("Root node created");

        //2)adding a listener to root
        root.registerObserver();

        //3)creating two childNodes
        root.create("/root/child1", "childdata 1");
        root.create("/root/child2", "childdata 2");

        //4)adding one child node to child one
        root.create("/root/child1/subchild1", "subchild1");
        System.out.println();

        //5)getting data for all nodes and printing it
        System.out.println("The values of the hierarchical data structures are:");
        System.out.println(root.get("/root"));
        System.out.println(root.get("/root/child1"));
        System.out.println(root.get("/root/child2"));
        System.out.println(root.get("/root/child1/subchild1"));
        System.out.println();

//        5.1)Printing data for all nodes using levelOrderTraversal;
//        TreeTraversal treeTraversal=new LevelOrderTraversal();
//        treeTraversal.print(root.getRoot());

        //6)listing all child nodes for root
        System.out.println("Listing all chilNodes for root");
        List<Node> list = root.list("/root");
        for (Node x : list)
            System.out.print(x.value + " ");
        System.out.println();
        System.out.println();

        //deleting the node child2
        root.delete("/root/child2");
        System.out.println();
        System.out.println("Thank You..!!");

    }
}
