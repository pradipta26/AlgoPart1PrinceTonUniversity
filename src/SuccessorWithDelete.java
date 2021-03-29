import java.util.List;

public class SuccessorWithDelete {
    private static class Node{
        int value;
        Node left, right, parent;
        Node (int value) {
            this.value = value;
            left = right = parent = null;
        }
    }

    Node rootNode = null;

    public SuccessorWithDelete(List<Integer> values){
        for (int value : values ) {
            Node node = new Node(value);
            if (rootNode == null)
                rootNode = node;
            else createTree(rootNode, node);
            int leftTreeHeight = getDepth(rootNode.left);
            int rightTreeHeight = getDepth(rootNode.right);
            System.out.printf("Current leftTreeHeight = %d rightTreeHeight = %d%n", leftTreeHeight, rightTreeHeight);
            if (leftTreeHeight - rightTreeHeight >1)
                balanceRight();
            else if (rightTreeHeight - leftTreeHeight >1)
                balanceLeft();
        }
    }
    private void createTree(Node parentNode, Node node){
        if(parentNode.parent !=null)
            System.out.print(parentNode.parent.value + " -> ");
        System.out.print(parentNode.value);
        if (parentNode.value <= node.value ){
            if (parentNode.right == null  && parentNode.left != null) {
                parentNode.right = node;
                node.parent = parentNode;
            }
            else if (parentNode.right == null) {
                int tempValue = parentNode.value;
                parentNode.value = node.value;
                node.value = tempValue;
                parentNode.left = node;
                node.parent = parentNode;
            }
            else {
                System.out.println("recursive call: " + parentNode.right.value + " -> " + node.value);
                createTree(parentNode.right, node);
            }
        }
        else {
            if (parentNode.left == null  && parentNode.right != null) {
                parentNode.left = node;
                node.parent = parentNode;
            }
            else if (parentNode.left == null) {
                int tempValue = parentNode.value;
                parentNode.value = node.value;
                node.value = tempValue;
                parentNode.right = node;
                node.parent = parentNode;
            }
            else
                createTree(parentNode.left, node);
        }
    }
    private void balanceLeft(){
        Node tempRoot = rootNode;
        Node tempRightNode = rootNode.right.left;
        rootNode = rootNode.right;
        rootNode.parent = null;
        tempRoot.right = tempRightNode;
        tempRightNode.parent = tempRoot;
        tempRoot.parent = rootNode;
        rootNode.left = tempRoot;
    }
    private void balanceRight(){
        Node tempRoot = rootNode;
        Node tempLeftNode = rootNode.left.right;
        rootNode = rootNode.left;
        rootNode.parent = null;
        tempRoot.left = tempLeftNode;
        tempLeftNode.parent = tempRoot;
        rootNode.right = tempRoot;
    }

    public void removeNode(int p) {
        Node foundNode = findNode(rootNode, p);
        if (foundNode.value == -1)
            System.out.println("Value not found!!!!!");
        else {
            System.out.println("\nRemoving : " + p);
            if(foundNode.left != null){
                if(foundNode.parent != null){
                    foundNode.left.parent = foundNode.parent;
                    if (foundNode.parent.left == foundNode)
                        foundNode.parent.left = foundNode.left;
                    else
                        foundNode.parent.right = foundNode.left;
                }
                if (foundNode.right != null ) {
                    Node tempRightNode = foundNode.left.right;
                    foundNode.left.right = foundNode.right;
                    foundNode.right.parent = foundNode.left;
                    if (tempRightNode != null)
                        createTree(foundNode.right, tempRightNode);
                }
                if (foundNode == rootNode) {
                    rootNode = foundNode.left;
                }

            }
            else if(foundNode.right != null){ // will execute if there is no left node
               foundNode.right.parent = foundNode.parent; //will assign null for rootNode
               if(foundNode.parent != null){
                    if (foundNode.parent.left == foundNode)
                        foundNode.parent.left = foundNode.right;
                    else
                        foundNode.parent.right = foundNode.right;
                }
               else
                   rootNode = foundNode.right;
            }
            else { // leaf node
                if(foundNode.parent != null){
                    if (foundNode.parent.left == foundNode)
                        foundNode.parent.left = null;
                    else
                        foundNode.parent.right = null;
                }
                else //leaf root node
                    rootNode = null;

            }
            System.out.println(String.format("%d node is removed successfully", foundNode.value));
        }
   }
    private Node findNode( Node parentNode, int p) {
        if ( null != parentNode && parentNode.value < p)
            return findNode(parentNode.right, p);
        else if (null != parentNode && parentNode.value > p)
            return findNode( parentNode.left, p);
        else if (null != parentNode)
             return parentNode;
        else return new Node(-1);
    }
    public void printTree(){
        printTree(rootNode);
    }
    private void printTree(Node node) {
        if (node.left != null)
            printTree(node.left);
        //else {
        //    System.out.print(node.value + " ->");
        //    return;
       // }
        System.out.print("( " +node.value + " )->");
        if (node.right != null)
            printTree(node.right);
        /*else {
            System.out.print(node.value + " ->");
            return;
        }*/
    }
    private int getDepth(Node node){
        if (node == null)
            return 0;
        else {
            int leftDepth = getDepth(node.left);
            int rightDepth = getDepth(node.right);
            if(leftDepth > rightDepth) return leftDepth + 1;
            else return rightDepth + 1;
        }
    }
    public void getSuccessor( int p){
        Node node = findNode(rootNode, p);
        int successor;
        if(node.right != null)
            successor = getLeftMostNodeValue(node.right);
        else if (node.parent == null)
            successor = node.value;
        else if (node.parent.left == node)
            successor = node.parent.value;
        else { // node.right == null and not root
            Node tempNode = node;
            while (tempNode.parent != null && tempNode.parent.left != tempNode)
                tempNode = tempNode.parent;
            if(tempNode == rootNode)
                successor = node.value;
            else
                successor = tempNode.parent.value;
        }
        System.out.print(String.format("\nSuccessor %d is %d", p, successor));
    }
    private int getLeftMostNodeValue(Node node){
        if(node.left == null)
            return node.value;
        else
           return getLeftMostNodeValue(node.left);
    }
}
