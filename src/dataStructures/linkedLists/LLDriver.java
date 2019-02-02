package dataStructures.linkedLists;

public class LLDriver {

    public static void main(String[] args) {

        StringLLOOP movies = new StringLLOOP();
        movies.addToFront("Inception");
        movies.addToFront("a star is born");
        movies.addToFront("interstellar");

        System.out.println(movies.search("Star Wars"));
        System.out.println(movies.search("Inception"));
        System.out.println(movies.getSize());

        StringLLOOP colors = new StringLLOOP();
        colors.addToFront("blue");
        colors.addToFront("navy");
        System.out.println(colors.getSize());


    }

}
