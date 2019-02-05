package dataStructures.linkedLists.genericLL;

public class CLLDriver {

    public static void main(String[] args) {

        CLL<String> players = new CLL<String>();
        players.addToFront("Tom");
        players.addToFront("Sam");
        players.addToFront("Kerry");

        players.traverse();

        players.deleteFront();
        System.out.println();

        players.traverse();
    }

}
