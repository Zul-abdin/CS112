package dataStructures.progAssignments.expression.app; //revert

import java.io.*;
import java.util.*;
import java.util.regex.*;

import dataStructures.progAssignments.expression.structures.Stack; //revert

public class Expression {

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
    @SuppressWarnings("Duplicates") //remove
    public static void
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        /** COMPLETE THIS METHOD **/
        String updatedExpr = expr.replaceAll("\\s+", "");
        String token = "";
        for (int i = 0; i < updatedExpr.length(); i++) {
            char ch = updatedExpr.charAt(i);
            if (ch >= '0' && ch <= '9' || ch == '.') {
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
    @SuppressWarnings("Duplicates") //remove
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
        float[] ans;
        ans = initialParse(-1, 0, 0, updatedExpr, vars, arrays);
        return ans[2];
    }

    //NOTE: Works with decimal inputs AND integer inputs!

    //Parsing order:
    // ('+') || ('-') --> Parse the rest first starting with MultDiv --> add or subtract result
    // ('/') || ('*') --> Parse imm --> Parse again (recursively) --> multiply or divide result
    // ('(') || ('[') --> Parse expression imm
    // ParseFactor (recursive) handles functions, parenthesis ('()'), and array brackets('[]')
    // functions include variables and arrays (anything that has letters)

    private static float[] nextChar(int pos, int ch, float result, String updatedExpr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        float[] ans = new float[3];
        ans[0] = pos;
        ans[1] = ch;
        ans[2] = result;
        ans[0]++;
        ans[1] = (ans[0] < updatedExpr.length()) ? updatedExpr.charAt((int)ans[0]) : -1;
        return ans;
    }

    private static float[] initialParse(int pos, int ch, float result, String updatedExpr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        float[] ans = new float[3];
        ans[0] = pos;
        ans[1] = ch;
        ans[2] = result;
        ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
        ans = parseAddSub((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
        if (ans[0] < updatedExpr.length()) throw new RuntimeException("Unexpected: " + (char) ans[1]); //Throws exception for illegal characters
        return ans;
    }

    private static float[] parseAddSub(int pos, int ch, float result, String updatedExpr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        float[] ans = new float[3];
        ans[0] = pos;
        ans[1] = ch;
        ans[2] = result;
        ans = parseMultDiv((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
        for (; ; ) {
            if (ans[1] == '+'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                float temp = ans[2];
                ans = parseMultDiv((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                ans[2] += temp; // addition
            }
            else if (ans[1] =='-'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                float temp = ans[2];
                ans = parseMultDiv((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                ans[2] = temp - ans[2]; // subtraction
            }
            else return ans;
        }
    }

    private static float[] parseMultDiv(int pos, int ch, float result, String updatedExpr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        float[] ans = new float[3];
        ans[0] = pos;
        ans[1] = ch;
        ans[2] = result;
        ans = parseFactor((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
        for (; ; ) {
            if (ans[1] == '*'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                float temp = ans[2];
                ans = parseMultDiv((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                ans[2] *= temp; // multiplication
            }
            else if (ans[1] == '/'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                float temp = ans[2];
                ans = parseMultDiv((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                ans[2] = temp / ans[2]; // division
            }
            else return ans;
        }
    }

    private static float[] parseFactor(int pos, int ch, float result, String updatedExpr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        float[] ans = new float[3];
        ans[0] = pos;
        ans[1] = ch;
        ans[2] = result;

        float x = 0;
        int startPos = (int)ans[0];
        if (ans[1] == '(') { // parentheses
            ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            ans = parseAddSub((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            if(ans[1] == ')'){
                ans[0]++;
                ans[1] = (ans[0] < updatedExpr.length()) ? updatedExpr.charAt((int)ans[0]) : -1;
            }
        } else if (ans[1] == '['){ // array brackets
            ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            ans = parseAddSub((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            if(ans[1] == ']'){
                ans[0]++;
                ans[1] = (ans[0] < updatedExpr.length()) ? updatedExpr.charAt((int)ans[0]) : -1;
            }
        } else if ((ans[1] >= '0' && ans[1] <= '9') || ans[1] == '.') { // numbers
            while ((ans[1] >= '0' && ans[1] <= '9') || ans[1] == '.'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            }
            ans[2] = Float.parseFloat(updatedExpr.substring(startPos, (int)ans[0]));
        } else if (ans[1] >= 'A' && ans[1] <= 'Z' || ans[1] >= 'a' && ans[1] <= 'z') { // functions
            while (ans[1] >= 'A' && ans[1] <= 'Z' || ans[1] >= 'a' && ans[1] <= 'z'){
                ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
            }
            String func = updatedExpr.substring(startPos, (int)ans[0]);
            for (Variable var : vars) {
                if (var.name.equals(func)) {
                    x = var.value;
                    ans[2] = x;
                }
            }
            if((int)ans[0] != updatedExpr.length() - 1){
                if(ans[1] == '['){
                    ans = nextChar((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                    for(int i = 0; i < arrays.size(); i++){
                        if(arrays.get(i).name.equals(func)){
                            ans = parseAddSub((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);
                            ans[2] = arrays.get(i).values[(int)ans[2]];
                            if(ans[1] == ']'){
                                ans[0]++;
                                ans[1] = (ans[0] < updatedExpr.length()) ? updatedExpr.charAt((int)ans[0]) : -1;
                            }
                            return ans;
                        }
                    }
                }
            }
            ans = parseFactor((int)ans[0], (int)ans[1], ans[2], updatedExpr, vars, arrays);

        }
        return ans;
    }
}
