package dataStructures.progAssignments.expression.app; //revert

import java.io.*;
import java.util.*;
import java.util.regex.*;

import dataStructures.progAssignments.expression.structures.Stack; //revert

public class ExpressionParser {

    public static String delims = " \t*+-/()[]";

    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     *
     * @param expr   The expression
     * @param vars   The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    @SuppressWarnings("Duplicates")
    public static void
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        /** COMPLETE THIS METHOD **/
        String updatedExpr = expr.replaceAll("\\s+", "");
        System.out.println("No Spaces: " + updatedExpr);
        String token = "";
        for (int i = 0; i < updatedExpr.length(); i++) {
            char ch = updatedExpr.charAt(i);
            if (ch >= '0' && ch <= '9') {
                continue;
            }
            switch (ch) {
                case '[':
                    Array newArr = new Array(token);
                    if (arrays.contains(newArr)) {
                        token = "";
                        break;
                    }
                    if (!token.equals("")) {
                        arrays.add(newArr);
                        System.out.println("Added " + token + " To arrays");
                        token = "";
                        break;
                    }
                case '+':
                case '*':
                case '-':
                case '/':
                case ')':
                case '(':
                case ']':
                    Variable newVar = new Variable(token);
                    if (vars.contains(newVar)) {
                        token = "";
                        break;
                    }
                    if (!token.equals("")) {
                        vars.add(newVar);
                        System.out.println("Added " + token + " To vars");
                        token = "";
                    }
                    break;
                default:
                    token += ch;
                    break;
            }
        }
        Variable newVar = new Variable(token);
        if (!token.equals("") && !vars.contains(newVar)) {
            vars.add(newVar);
            System.out.println("Added " + token + " To vars");
        }
        /** DO NOT create new vars and arrays - they are already created before being sent in
         ** to this method - you just need to fill them in.
         **/
    }

    /**
     * Loads values for variables and arrays in the expression
     *
     * @param sc     Scanner for values input
     * @param vars   The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     * @throws IOException If there is a problem with the input
     */
    @SuppressWarnings("Duplicates")
    public static void
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays)
            throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
                continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
                arr = arrays.get(arri);
                arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok, " (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;
                }
            }
        }
    }

    /**
     * Evaluates the expression.
     *
     * @param vars   The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        /** COMPLETE THIS METHOD **/
        // following line just a placeholder for compilation
        String updatedExpr = expr.replaceAll("\\s+", "");
        return new Object() {
            int pos = -1, ch;

            private void nextChar() {
                ch = (++pos < updatedExpr.length()) ? updatedExpr.charAt(pos) : -1;
            }

            private boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            private float parse() {
                nextChar();
                float x = parseExpression();
                if (pos < updatedExpr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            private float parseExpression() {
                float x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            private float parseTerm() {
                float x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            private float parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                float x = 0;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if (eat('[')){ // Array brackets
                    x = parseExpression();
                    eat(']');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Float.parseFloat(updatedExpr.substring(startPos, this.pos));
                } else if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') nextChar();
                    String func = updatedExpr.substring(startPos, this.pos);
                    for(int i = 0; i < vars.size(); i++){
                        if(vars.get(i).name.equals(func)){
                            x = vars.get(i).value;
                            return x;
                        }
                    }
                    if(this.pos != updatedExpr.length() - 1){
                        if(eat('[')){
                            for(int i = 0; i < arrays.size(); i++){
                                if(arrays.get(i).name.equals(func)){
                                    x = arrays.get(i).values[(int)parseExpression()];
                                    eat(']');
                                    return x;
                                }
                            }
                        }
                    }
                    x = parseFactor();

                }
                return x;
            }
        }.parse();

    }
}