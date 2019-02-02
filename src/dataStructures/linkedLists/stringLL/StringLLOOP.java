package dataStructures.linkedLists.stringLL;

public class StringLLOOP {

    private StringNode front; //reference to the first node of the LL
    private int size;

    public StringLLOOP(){
        front = null;
        size = 0;
    }

    public void addToFront(String newItem){
        StringNode node = new StringNode(newItem, front);
        front = node;
        size++;
    }

    public void addToBack(String newItem){
        StringNode node = new StringNode(newItem, null);
        StringNode pointer = front;
        for(pointer = front; pointer.next != null; pointer = pointer.next){
        }
        pointer.next = node;
        size++;

    }

    public void addAfterIndex(int index, String newItem){
        int counter = 0;
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(counter == index){
                StringNode node = new StringNode(newItem, pointer.next);
                pointer.next = node;

            }
            counter++;
        }
        size++;
    }

    public void addArrayAfterIndexBToF(int index, String[] newItems){
        int counter = 0;
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(counter == index){
                for(int i = 0; i < newItems.length; i++){
                    StringNode node = new StringNode(newItems[i], pointer.next);
                    pointer.next = node;
                }
            }
            counter++;
        }
        size += newItems.length;
    }

    public void addArrayAfterIndexFtoB(int index, String[] newItems){
        int counter = 0;
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(counter == index){
                for(int i = newItems.length - 1; i >= 0; i--){
                    StringNode node = new StringNode(newItems[i], pointer.next);
                    pointer.next = node;
                }
            }
            counter++;
        }
        size += newItems.length;
    }

    public boolean search(String target){
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(pointer.data.equals(target)){
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String list = "";
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            list += pointer.data + " --> ";
        }
        list += "\\";
        return list;
    }

    public boolean remove(String target){

        size--;

        StringNode behindPointer = null;
        StringNode pointer = front;

        while(pointer != null && !(pointer.data.equals(target))){
            behindPointer = pointer;
            pointer = pointer.next;
        }

        if(pointer == null){
            return false;
        } else if(pointer == front){
            front = front.next;
            return true;
        } else {
            behindPointer.next = pointer.next;
            return true;
        }
    }

    public int indexOf(String target){
        int counter = 0;
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(pointer.data.equals(target)){
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public String getAtIndex(int index){
        int counter = 0;
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            if(counter == index){
                return pointer.data;
            }
            counter++;
        }
        return  "-1";
    }

    public String toStringWithIndex(){
        int counter = 0;
        String list = "";
        for(StringNode pointer = front; pointer != null; pointer = pointer.next){
            list += "(" + counter + ") " + pointer.data + " --> ";
            counter++;
        }
        list += "\\";
        return list;
    }

    public int getSize() {
        return size;
    }
}
