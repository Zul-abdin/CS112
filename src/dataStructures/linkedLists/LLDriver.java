package dataStructures.linkedLists;

public class LLDriver {

    public static void main(String[] args) {

        StringLLOOP movies = new StringLLOOP();
        movies.addToFront("Inception");
        movies.addToFront("Star");
        movies.addToBack("Familee");
        System.out.println(movies.toString());
        String[] movieArray = {"T1", "T2", "T3"};
        movies.addArrayAfterIndexFtoB(1, movieArray);
        System.out.println(movies.toString());
        System.out.println(movies.size());


    }

}
