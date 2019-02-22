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
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	String updatedExpr = removeSpaces(expr);
        System.out.println("No Spaces: " + updatedExpr);
    	StringTokenizer st = new StringTokenizer(updatedExpr,  delims);
    	while(st.hasMoreTokens()){
            String token = st.nextToken();
            int postIndex = updatedExpr.indexOf(token) + token.length();
            if(postIndex > updatedExpr.length() - 1 && !(vars.contains(new Variable(token)))){
                vars.add(new Variable(token));
                System.out.println("Added " + token + " To vars");
                break;
            }
            switch (updatedExpr.charAt(postIndex)) {
                case '[':
                    Array newArr = new Array(token);
                    if(!arrays.contains(newArr)) {
                        arrays.add(newArr);
                        System.out.println("Added " + token + " To arrays");
                    }
                    break;
                case '+':
                case '*':
                case '-':
                case '/':
                case ')':
                case '(':
                case ']':
                    Variable newVar = new Variable(token);
                    if(!vars.contains(newVar)) {
                        vars.add(newVar);
                        System.out.println("Added " + token + " To vars");
                    }
                    break;
                default:
                    break;
            }

        }
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 **/
    }
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
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
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
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
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	// following line just a placeholder for compilation
    	return 0;
    }

    private static String removeSpaces(String str){
        String result = "";
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch != ' '){
                result += ch;
            }
        }
        return result;
    }
}
