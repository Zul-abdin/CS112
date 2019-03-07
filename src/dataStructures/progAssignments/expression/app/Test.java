package dataStructures.progAssignments.expression.app;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        String expression = "1 - 1";
        ArrayList<Variable> varrs = new ArrayList<>();
        ArrayList<Array> arrs = new ArrayList<>();

        System.out.println(Expression.evaluate(expression, varrs, arrs));


    }
}
