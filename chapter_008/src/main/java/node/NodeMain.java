package node;

public class NodeMain {

    public static void main(String[] args) {
        Node<String> node = new Node<>(null, "Value");
        System.out.println("node = " + node);
        System.out.println("    node.getNext() = " + node.getNext());
        System.out.println("    node.getValue() = " + node.getValue());
        Node<String> node1 = new Node<>(node, "Value1");
        System.out.println("node1 = " + node1);
        System.out.println("    node1.getNext() = " + node1.getNext());
        System.out.println("    node1.getValue() = " + node1.getValue());
    }
}
