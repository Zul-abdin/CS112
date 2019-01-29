package dataStructures.linkedLists;

public class StringLLOOP {

    private StringNode front; //reference to the first node of the LL

    public StringLLOOP(){
        front = null;
    }

    public void addToFront(String newItem){
        StringNode node = new StringNode(newItem, front);
        front = node;
    }

    public boolean search(String target){
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(pointer.data.equals(target)){
                return true;
            }
        }
        return false;
    }

}
