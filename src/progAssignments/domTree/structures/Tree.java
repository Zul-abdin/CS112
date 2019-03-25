package progAssignments.domTree.structures; //revert

import javax.swing.text.html.HTML;
import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}

	private void add(TagNode node, TagNode parent){
	    if(root == null){
	        root = node;
	        return;
        }
        TagNode firstChild = parent.firstChild;
        if(firstChild == null){
            parent.firstChild = node;
            return;
        }
        TagNode ptr = firstChild;
        while(ptr.sibling != null){
            ptr = ptr.sibling;
        }
        ptr.sibling = node;
    }
	
	/**
	 * Builds the DOM tree from input HTML file, through scanner passed
	 * in to the constructor and stored in the sc field of this object. 
	 * 
	 * The root of the tree that is built is referenced by the root field of this object.
	 */
	public void build() {
		/** COMPLETE THIS METHOD **/
		TagNode curr = root;
		TagNode prev = null;
		TagNode parent;
		Stack<TagNode> stk = new Stack<>();
		while(sc.hasNextLine()){
		    String currLine = sc.nextLine();
		    if(currLine.charAt(0) == '<' && currLine.charAt(1) != '/'){
		    	TagNode node = new TagNode(currLine.substring(1, currLine.length() - 1), null, null);
		    	try { parent = stk.peek(); } catch (Exception e){
		    	    parent = null;
                }
		    	add(node, parent);
		        stk.push(node);
            } else if(currLine.charAt(0) == '<' && currLine.charAt(1) == '/'){
		        stk.pop();
            } else {
                TagNode node = new TagNode(currLine, null, null);
                parent = stk.peek();
                add(node, parent);

            }
        }
	}
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */

	private void replaceTagRec(TagNode node, String oldTag, String newTag){
	    if(node == null) return;
	    replaceTagRec(node.firstChild, oldTag, newTag);
	    if(node.tag.equals(oldTag)){
	        node.tag = newTag;
        }
        replaceTagRec(node.sibling, oldTag, newTag);
    }

	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
        replaceTagRec(root, oldTag, newTag);
	}

    private void boldRowRec(TagNode node, int row){
        if(node == null) return;
        boldRowRec(node.firstChild, row);
        if(node.tag.equals("table")){
            node = node.firstChild;
            int currRow = 1;
            while(currRow != row){
                node = node.sibling;
                currRow++;
            }
            node = node.firstChild;
            for(TagNode ptr = node; ptr != null; ptr = ptr.sibling){
                TagNode temp = ptr.firstChild;
                ptr.firstChild = new TagNode("b", temp, null);
            }
        }
        boldRowRec(node.sibling, row);
    }
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */

	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		boldRowRec(root, row);
	}

	private void removeTagRec(TagNode node, TagNode parent, String tag){
        if(node == null) return;
        removeTagRec(node.firstChild, node, tag);
        if(node.tag.equals(tag)) {
        	if(tag.equals("ol") || tag.equals("ul")){
                if (node == parent.firstChild) {
                    TagNode ptr = node.firstChild;
                    for(TagNode liPtr = ptr; liPtr != null; liPtr = liPtr.sibling){
                        if(liPtr.tag.equals("li")){
                            liPtr.tag = "p";
                        }
                    }
                    while (ptr.sibling != null) {
                        ptr = ptr.sibling;
                    }
                    ptr.sibling = node.sibling;
                    parent.firstChild = node.firstChild;
                } else {
                    TagNode ptr = node.firstChild;
                    for(TagNode liPtr = ptr; liPtr != null; liPtr = liPtr.sibling){
                        if(liPtr.tag.equals("li")){
                            liPtr.tag = "p";
                        }
                    }
                    while (ptr.sibling != null) {
                        ptr = ptr.sibling;
                    }
                    ptr.sibling = node.sibling;
                    TagNode prevSib = parent.firstChild;
                    while (prevSib.sibling != node) {
                        prevSib = prevSib.sibling;
                    }
                    prevSib.sibling = node.firstChild;
                }
            } else {
                if (node == parent.firstChild) {
                    TagNode ptr = node.firstChild;
                    while (ptr.sibling != null) {
                        ptr = ptr.sibling;
                    }
                    ptr.sibling = node.sibling;
                    parent.firstChild = node.firstChild;
                } else {
                    TagNode ptr = node.firstChild;
                    while (ptr.sibling != null) {
                        ptr = ptr.sibling;
                    }
                    ptr.sibling = node.sibling;
                    TagNode prevSib = parent.firstChild;
                    while (prevSib.sibling != node) {
                        prevSib = prevSib.sibling;
                    }
                    prevSib.sibling = node.firstChild;
                }
            }
        }
        removeTagRec(node.sibling, parent, tag);
    }
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		removeTagRec(root, null, tag);
	}

	private void addTagRec(TagNode node, String word, String tag){
        if(node == null) return;
        addTagRec(node.firstChild, word, tag);
        addTagRec(node.sibling, word, tag);

        String lowWord = word.toLowerCase();
        String lowNode = node.tag.toLowerCase();
        if(lowNode.contains(lowWord)){
            TagNode tagTemp = node.sibling;
            String strTemp = node.tag;
            int ind = lowNode.indexOf(lowWord);
            //Word is last
            if(ind + word.length() == lowNode.length()){
                node.tag = strTemp.substring(0, ind);
                TagNode newWordPunc = new TagNode(strTemp.substring(ind, ind + word.length()), null, null);
                node.sibling = new TagNode(tag, newWordPunc, tagTemp);
            } else {
                char ch = lowNode.charAt(ind + word.length());
                if (ind == 0 || lowNode.charAt(ind - 1) == ' ') {
                    switch (ch) {
                        case '.':
                        case ',':
                        case '?':
                        case '!':
                        case ':':
                        case ';':
                            //Node is only word
                            if (ind == 0 && word.length() + 1 == lowNode.length()) {
                                node.tag = tag;
                                node.firstChild = new TagNode(strTemp.substring(0, lowNode.length()), null, null);
                                node.sibling = tagTemp;
                                //Node has something after punc
                            } else if (lowNode.charAt(ind + word.length() + 1) != ' ') {
                                break;
                                //Word is last
                            } else if (ind + word.length() + 1 == lowNode.length()) {
                                node.tag = strTemp.substring(0, ind);
                                TagNode newWordPunc = new TagNode(strTemp.substring(ind, ind + word.length() + 1), null, null);
                                node.sibling = new TagNode(tag, newWordPunc, tagTemp);
                                //Node is first word
                            } else if (ind == 0) {
                                node.tag = tag;
                                TagNode newWordPunc = new TagNode(strTemp.substring(0, word.length() + 1), null, null);
                                TagNode postWordPunc = new TagNode(strTemp.substring(word.length() + 1), null, tagTemp);
                                node.firstChild = newWordPunc;
                                node.sibling = postWordPunc;
                                //Node is in middle
                            } else {
                                node.tag = strTemp.substring(0, ind);
                                TagNode newWordPunc = new TagNode(strTemp.substring(ind, ind + word.length() + 1), null, null);
                                TagNode postWordPunc = new TagNode(strTemp.substring(ind + word.length() + 1), null, tagTemp);
                                node.sibling = new TagNode(tag, newWordPunc, postWordPunc);
                            }
                            break;
                        case ' ':
                            //Node is first word
                            if (ind == 0) {
                                node.tag = tag;
                                TagNode newWordPunc = new TagNode(strTemp.substring(0, word.length()), null, null);
                                TagNode postWordPunc = new TagNode(strTemp.substring(word.length()), null, tagTemp);
                                node.firstChild = newWordPunc;
                                node.sibling = postWordPunc;
                                //Node is in the middle
                            } else {
                                node.tag = strTemp.substring(0, ind);
                                TagNode newWord = new TagNode(strTemp.substring(ind, ind + word.length()), null, null);
                                TagNode postWord = new TagNode(strTemp.substring(ind + word.length()), null, tagTemp);
                                node.sibling = new TagNode(tag, newWord, postWord);
                            }
                            break;
                        default:
                    }

                }
            }
        }
    }
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
		addTagRec(root, word, tag);
	}
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	/**
	 * Prints the DOM tree. 
	 *
	 */
	public void print() {
		print(root, 1);
	}
	
	private void print(TagNode root, int level) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			for (int i=0; i < level-1; i++) {
				System.out.print("      ");
			};
			if (root != this.root) {
				System.out.print("|----");
			} else {
				System.out.print("     ");
			}
			System.out.println(ptr.tag);
			if (ptr.firstChild != null) {
				print(ptr.firstChild, level+1);
			}
		}
	}
}
