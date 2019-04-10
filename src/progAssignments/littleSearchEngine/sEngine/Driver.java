package progAssignments.littleSearchEngine.sEngine;

import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        LittleSearchEngine e = new LittleSearchEngine();
        e.makeIndex("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\littleSearchEngine\\docs.txt", "C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\littleSearchEngine\\noisewords.txt");
        System.out.println(e.top5search("deep", "world"));
    }
}
