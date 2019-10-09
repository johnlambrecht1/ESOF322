package linkedlist;
import java.util.Scanner;
public class LinkedList{
    Node current;
    Node First;
    Node Last;
    public LinkedList(){
        First = null;
    }
    void buildList(String name, int social, int salary){
        Node n = new Node(name, social, salary);
        if (First==null){
            First = n;
        }
        current = n;
        while (current.getNext()!=null){
            current = current.getNext();
        }
        current.setNext(n);
        Last = n;
    }
    void delete (String employee){
        Node n = First;
        if (First.getName()==employee){
            First = n.getNext();
        }
        else{
            while (n.getNext().getName()!=employee){
                n=n.getNext();
            }
        }
        n.setNext(n.getNext().getNext());
    }
    Node search(String employee){
        Node n = First;
        while (n.getName()!=employee){
            n = n.getNext();
        }
        return n;
    }
}
class Node{
    Node next;
    private String Name;
    private int Social;
    private int Salary;
    public Node(String name, int SSN, int Sal){
        Name = name;
        Social = SSN;
        Salary = Sal;
    }
    public void setName(String name){
        Name = name;
    }
    public void setSocial(int SSN){
        Social = SSN;
    }
    public void setSalary(int Sal){
        Salary = Sal;
    }
    public void setNext(Node temp){
        next = temp;
    }
    public Node getNext(){
        return next;
    }
    public String getName(){
        return Name;
    }
    public int getSocial(){
        return Social;
    }
    public int getSalary(){
        return Salary;
    }
}
class Client{
    public static void main(String[] args){
        LinkedList myLinkedList;
        myLinkedList = new LinkedList();
        int choice = 0;
        while (choice != -1){
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to insert an employee");
            System.out.println("Press 2 to delete an employee");
            System.out.println("Press 3 to search for an employee");
            System.out.println("Press -1 to exit");
            Scanner reader = new Scanner(System.in);
            choice = reader.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Please enter the employee name");
                    Scanner emp = new Scanner(System.in);
                    String name = emp.next();
                    System.out.println("Please enter the employee SSN without dashes");
                    Scanner SSN = new Scanner(System.in);
                    int social = SSN.nextInt();
                    System.out.println("Please enter the employee salary");
                    Scanner sal = new Scanner(System.in);
                    int salary = sal.nextInt();
                    myLinkedList.buildList(name, social, salary);
                    break;
                case 2:
                    System.out.println("Please enter the name of the employee to be deleted");
                    Scanner del = new Scanner(System.in);
                    String delete = del.next();
                    myLinkedList.delete(delete);
                    break;
                case 3:
                    System.out.println("Enter the name of the employee");
                    Scanner employee = new Scanner(System.in);
                    String empl = employee.next();
                    Node search = myLinkedList.search(empl);
                    System.out.println(search.getName()+ search.getSocial()+ search.getSalary());
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
