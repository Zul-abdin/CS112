package dataStructures.progAssignments.poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node front = null;
		Node p1 = poly1, p2 = poly2;
		while(p1 != null && p2 != null){
		    if(p1 == null || p1.term.degree > p2.term.degree){
		        front = new Node(p2.term.coeff, p2.term.degree, front);
		        p2 = p2.next;
            } else if(p2 == null || p1.term.degree < p2.term.degree){
		        front = new Node(p1.term.coeff, p1.term.degree, front);
		        p1 = p1.next;
            } else {
		        front = new Node(p1.term.coeff + p2.term.coeff, p1.term.degree, front);
		        p1 = p1.next;
		        p2 = p2.next;
            }

            if(p1 == null){
		        for(Node ptr = p2; ptr != null; ptr = ptr.next){
                    front = new Node(p2.term.coeff, p2.term.degree, front);
                }
            }
            if(p2 == null){
		        for(Node ptr = p1; ptr != null; ptr = ptr.next){
		            front = new Node(p1.term.coeff, p1.term.degree, front);
                }
            }
        }
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return front;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node front = null;

		for(Node p1 = poly1; p1 != null; p1 = p1.next){
		    for(Node p2 = poly2; p2 != null; p2 = p2.next){
		        front = new Node(p1.term.coeff * p2.term.coeff, p1.term.degree + p2.term.degree, front);
            }
        }
        front = simplify(front);
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return front;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return 0;
	}

    /**
     *  Helper method, simplifies polynomials with more than
     *  one nodes that have the same degree
     *
     * @param poly Polynomial (front of linked list) to simplify
     * @return Polynomial which has been simplified
     */
    public static Node simplify(Node poly){

        Node ptr = poly;
        while (ptr != null) {
            Node post = ptr.next;
            Node prev = ptr;
            while (post != null) {
                if (ptr.term.degree == post.term.degree) {
                    ptr.term.coeff += post.term.coeff;

                    prev.next = post.next;
                }
                prev = post;
                post = post.next;
            }
            ptr = ptr.next;
        }
        return poly;
    }

    /**
     *  Helper method, puts degrees in the proper order
     *
     * @param poly Polynomial (front of linked list) to simplify
     * @return Polynomial which has been ordered
     */
    public static Node order(Node poly){
        Node front = null;
        for(Node ptr = poly; ptr != null; ptr = ptr.next){
            int max = ptr.term.degree;
            for(Node post = poly.next; post != null; post = post.next){
            }
        }
        return front;
    }

    /**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
