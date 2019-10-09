package program;

import java.util.Scanner;
import java.util.Collections;

public class Program {
    public static void main(String[] args){
        tree mytree;
        
        mytree = new tree();
        int choice = 0;
        while (choice != -1){
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to insert a node");
            System.out.println("Press 2 to delete a node");
            System.out.println("Press 3 to search for a node");
            System.out.println("Press -1 to exit");
            Scanner reader = new Scanner(System.in);
            choice = reader.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Please enter the number to enter");
                    Scanner in = new Scanner(System.in);
                    int input = in.nextInt();
                    mytree.buildTree(input);
                    break;
                case 2:
                    System.out.println("Enter the node you want to delete");
                    Scanner del = new Scanner(System.in);
                    Node delroot = mytree.getRoot();
                    Node delete = mytree.searchTree(delroot, del.nextInt());
                    mytree.delete(delete);
                    break;
                case 3:
                    System.out.println("Please enter the number to search");
                    Scanner searchit = new Scanner(System.in);
                    int searchint = searchit.nextInt();
                    Node root = mytree.getRoot();
                    Node search = mytree.searchTree(root,searchint);
                    if (search == null){
                        System.out.println("The node is not in the tree");
                    }else{
                        System.out.println("The node is in the tree");
                    }
                    break;
                case -1:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Invalid choice");
                    choice = 0;
                    break;
            }
        }
    }
}

class Node {
    Node parent;
    Node leftChild;
    Node rightChild;
    private int key;
    public Node(int input){
        key = input;
    }
    public void setRightChild (Node temp){
        rightChild = temp;
    }
    public void setLeftChild (Node temp){
        leftChild = temp;
    }
    public void setParent (Node temp){
        parent = temp;
    }
    public void setKey(int temp){
        key = temp;
    }
    public Node getParent (){
        return parent;
    }
    public Node getLeftChild(){
        return leftChild;
    }
    public Node getRightChild(){
        return rightChild;
    }
    public int getKey(){
        return key;
    }
}

class tree {
    Node root;
    tree(){
        parent = null;
    }

    void buildTree(int key){
        Node n = new Node(key);
        if (root == null ){
            root = n;
            return;
        }
        while (root.getParent()!=null){
            root=root.getParent();
        }
        while(n.getParent()==null){
            if (root.getKey()>n.getKey()&&root.getLeftChild()==null){
                root.setLeftChild(n);
                n.setParent(root);
            }else if (root.getKey()>n.getKey()&&root.getLeftChild()!=null){
                root = root.getLeftChild();
            }else if(root.getKey()<n.getKey()&&root.getRightChild()==null){
                root.setRightChild(n);
                n.setParent(root);
            }else if (root.getKey()<n.getKey()&&root.getRightChild()!=null){
                root = root.getRightChild();
            }
        }

    }
    Node searchTree(Node current, int value){
        if (current.getKey()==value){
            return current;
        }
        else if (current.getKey()>value){
            return searchTree(current.getLeftChild(), value);
        }
        else if (current.getKey()<value){
            return searchTree(current.getRightChild(), value);
        }
        else {
            return null;
        }
    }
    Node getRoot(){
        while (root.getParent()!=null){
            root = root.getParent();
        }
        return root;
    }
    void delete(Node delNode){
        Node delRoot = getRoot();
        int rootkey = delRoot.getKey();
        int children = delNode.howManyChilren();
        if (children == 2){//If deleted node has 2 children
            Node Successor = inOrderSuccessor(delNode);//swaps keys of del and Successor, then deletes successor
            int Temp = Successor.getKey();
            delNode.setKey(Successor.getKey());
            Successor.setKey(Temp);
            delete(Successor);
        }else if (children==1){//If deleted node has 1 child
            if(delNode.getLeftChild()!=null){//If child of deleted node is left child
                delNode.getLeftChild().setParent(delNode.getParent());
                if (delNode.getParent().getLeftChild()==delNode){//If deleted node is a left child
                    delNode.getParent().setLeftChild(delNode.getLeftChild());
                }else if(delNode.getParent().getRightChild()==delNode){//If deleted node is a left child
                    delNode.getParent().setRightChild(delNode.getLeftChild());
                }
            }else if (delNode.getRightChild()!= null){//If child of deleted node is right child
                delNode.getRightChild().setParent(delNode.getParent());
                if (delNode.getParent().getLeftChild()==delNode){//If deleted node is a left child
                    delNode.getParent().setLeftChild(delNode.getRightChild());
                }else if(delNode.getParent().getRightChild()==delNode){//If deleted node is a right child
                    delNode.getParent().setRightChild(delNode.getRightChild());
                }
            }
        }else{//If node has no children
            if(delNode.getParent().getLeftChild()==delNode){//if node is a left child
                delNode.getParent().setLeftChild(null);
            }else if(delNode.getParent().getRightChild()==delNode){//if node is a right child
                delNode.getParent().setRightChild(null);
            }
            delNode.setParent(null);
        }
    }
    Node inOrderSuccessor(Node node) {
        Node succ;
        if (node.getRightChild() != null) {
            succ = node.getRightChild();
            while (succ.getLeftChild() != null) {
                succ = succ.getLeftChild();
            }
        } else {
            succ = node;
        }
        return succ;
    }
}

