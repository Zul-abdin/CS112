package dataStructures.progAssignments.expression.app;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        String expression = "(vara +    r -  t  + 43 - 62[(545 + t)]";
        ArrayList<Variable> varrs = new ArrayList<>();
        ArrayList<Array> arrs = new ArrayList<>();

        Expression.makeVariableLists(expression, varrs, arrs);


    }
}
