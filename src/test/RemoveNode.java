package test;

public class RemoveNode {


    public static void main(String[] args) {
        Integer [] nums = {1,2,3,4,5,6,7,8,6,9};
        Node<Integer> node = new Node<>(nums);
        System.out.println(node);
        Node remove = remove(node, 6);
        System.out.println(remove);


    }

    /**
     * 删除节点
     *
     * @param list
     * @param remove
     * @return
     */
    public static Node remove(Node<Integer> list, Integer remove) {
        if (list == null)
            return null;


        Node remove1 = remove(list.next, remove);
        if (list.e == remove)
            return remove1;
        else {
            list.next = remove1;
            return list;
        }

    }

    private static class Node<E> {
        public E e;

        public Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        public Node(E[] arr) {
            this.e = arr[0];
            Node<E> cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new Node<>(arr[i]);
                cur = cur.next;
            }
        }

        public Node() {
        }

        @Override
        public String toString() {

            StringBuilder res = new StringBuilder();
            res.append("[");
            for (Node<E> cur = this; cur != null; cur = cur.next) {
                res.append(cur.e + ", ");
            }
            res.replace(res.length()-2,res.length()-1,"");
            res.append("]");
            return res.toString();
        }
    }

}
