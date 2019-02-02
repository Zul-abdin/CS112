package dataStructures.linkedLists.genericLL;

public class GenericLLDriver {

    public static void main(String[] args) {

        GenericLL<Integer> numbers = new GenericLL<Integer>();

        System.out.println(numbers.getSize());

        numbers.addToFront(1);
        numbers.addToFront(2);
        numbers.addToFront(3);
        numbers.traverse();

        GenericLL<String> animals = new GenericLL<String>();
        animals.addToFront("Cat");
        animals.addToFront("Dog");
        animals.addToFront("Bat");
        animals.addToFront("Bear");

        System.out.println(animals.getSize());
        animals.traverse();

    }

}
