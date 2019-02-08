package dataStructures.stack;

import java.util.NoSuchElementException;

public class parenMatch {

    public static boolean isMatching(String expression){
        Stack<Character> stack = new Stack<Character>();

        //Traverse the expression looking for '(' to push ')' to pop
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if(ch == '('){
                stack.push(ch);
            } else if(ch == ')'){
                try{
                    stack.pop();
                } catch (NoSuchElementException e) {
                    return false; // expression is not matched, return false
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String expr = "A + B) * C";

        if(isMatching(expr)){
            System.out.println(expr + ": is Matched");
        } else {
            System.out.println(expr + ": is not Matched");
        }

    }

}
