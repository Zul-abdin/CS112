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
		if(poly1 == null){
		    Node poly2Copy = replicate(poly2);
		    return poly2Copy;
        } else if(poly2 == null){
		    Node poly1Copy = replicate(poly1);
		    return poly1Copy;
        }
		Node front = null;
		Node p1 = poly1, p2 = poly2;
		while(p1 != null && p2 != null){
		    if(p1.term.degree > p2.term.degree){
		        front = addToTail(p2.term.coeff, p2.term.degree, front);
		        p2 = p2.next;
            } else if(p1.term.degree < p2.term.degree){
		        front = addToTail(p1.term.coeff, p1.term.degree, front);
		        p1 = p1.next;
            } else {
		        front = addToTail(p1.term.coeff + p2.term.coeff, p1.term.degree, front);
		        p1 = p1.next;
		        p2 = p2.next;
            }

            if(p1 == null){
		        for(Node ptr = p2; ptr != null; ptr = ptr.next){
                    front = addToTail(ptr.term.coeff, ptr.term.degree, front);
                }
            }
            if(p2 == null){
		        for(Node ptr = p1; ptr != null; ptr = ptr.next){
		            front = addToTail(ptr.term.coeff, ptr.term.degree, front);
                }
            }
        }
        front = removeZeroCoeff(front);
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
		        front = addInAscending(p1.term.coeff * p2.term.coeff, p1.term.degree + p2.term.degree, front);
            }
        }
        front = simplify(front);
		front = removeZeroCoeff(front);
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
		float total = 0;
		for(Node ptr = poly; ptr != null; ptr = ptr.next){
		    total += evalTerm(ptr, x);
        }

		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return total;
	}

    /**
     *  Helper method, simplifies polynomials with more than
     *  one nodes that have the same degree
     *
     * @param poly Polynomial (front of linked list) to simplify
     * @return Polynomial which has been simplified
     */
    private static Node simplify(Node poly){

        Node ptr = poly;
        while (ptr != null) {
            Node post = ptr.next;
            while (post != null) {
                if (ptr.term.degree == post.term.degree) {
                    ptr.term.coeff += post.term.coeff;

                    ptr.next = post.next;
                }
                post = post.next;
            }
            ptr = ptr.next;
        }
        return poly;
    }

    /**
     *  Helper method, removes Zero coeffs of LL
     *
     * @param front Polynomial (front of linked list) to add to
     * @return Polynomial with no zero terms
     */
    private static Node removeZeroCoeff(Node front) {
        Node prev = null;
        Node ptr = front;
        while(ptr != null){
            if(ptr.term.coeff == 0){
                if(ptr.equals(front)){
                    if(front.next == null){
                        front = null;
                        return front;
                    } else {
                        front = front.next;
                        ptr = ptr.next;
                    }
                } else {
                    prev.next = ptr.next;
                    ptr = ptr.next;
                }
            } else {
                prev = ptr;
                ptr = ptr.next;
            }
        }
        return front;
    }

    /**
     *  Helper method, adds to rear of LL
     *
     * @param front Polynomial (front of linked list) to add to
     * @return Polynomial with added node
     */
    private static Node addToTail(float coeff, int degree, Node front){
        if(front == null){
            front = new Node(coeff, degree, null);
            return front;
        }
        Node temp = front;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(coeff, degree, null);
        return front;
    }

    /**
     *  Helper method, adds to LL in order of degrees
     *
     * @param front Polynomial (front of linked list) to add to
     * @return Polynomial with added node
     */
    private static Node addInAscending(float coeff, int deg, Node front){
        if(front == null){
            front = new Node(coeff, deg, null);
            return front;
        }
        if(front.term.degree >= deg){
            Node n = new Node(coeff, deg, front);
            return n;
        }
        Node prev = front;
        Node ptr = front.next;
        while (ptr != null){
            if(prev.term.degree <= deg && ptr.term.degree >= deg){

                prev.next = new Node(coeff, deg, ptr);
                return front;
            }
            prev = ptr;
            ptr = ptr.next;
        }
        prev.next = new Node(coeff, deg, null);
        return front;
    }

    /**
     *  Helper method, evaluates one term in a Polynomial
     *
     * @param n Node (term to evaluate) may NOT be the front
     * @return float of evaluated term
     */
    private static float evalTerm(Node n, float eval){
        float total = 1;
        for(int deg = n.term.degree; deg > 0; deg--){
            total *= eval;
        }
        total *= n.term.coeff;
        return total;
    }

    /**
     *  Helper method, replicates Polynomial with all new Nodes
     *
     * @param front Node (Polynomial to replicate)
     * @return front Node of replicated linked list
     */
    private static Node replicate(Node front){
        if(front == null){
            return null;
        }
        Node f = new Node(front.term.coeff, front.term.degree, null);
        f.next = replicate(front.next);
        return f;
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
