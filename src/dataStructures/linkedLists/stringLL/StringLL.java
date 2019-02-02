package dataStructures.linkedLists.stringLL;

public class StringLL {

    public static StringNode addtoFront (StringNode f, String newItem){
        StringNode node = new StringNode(newItem, f);
        return node;
    }

    public static void traverse(StringNode f){
        for(StringNode pointer = f; pointer != null; pointer = pointer.next){
            System.out.print(pointer.data + " --> ");
        }
        System.out.println("\\");
    }

    public static boolean search (StringNode f, String target){
        for(StringNode pointer = f; pointer != null; pointer = pointer.next){
            if(pointer.data.equals(target)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        StringNode Front = null;
        Front = addtoFront(Front, "apple");
        Front = addtoFront(Front, "pear");
        Front = addtoFront(Front, "orange");
        traverse(Front);
        System.out.println(search(Front, "pear"));
        System.out.println(search(Front, "strawberry"));

    }

}
