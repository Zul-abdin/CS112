package progAssignments.littleSearchEngine.sEngine;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        LittleSearchEngine e = new LittleSearchEngine();
        HashMap<String, Occurrence> hm = e.loadKeywordsFromDocument("/Users/zain/IdeaProjects/CS112/src/progAssignments/littleSearchEngine/WowCh1.txt");
    }
}
