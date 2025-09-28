/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                // root val bigger than both of the nodes. move it to the left.
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                // root val smaller than both. gotta move it to the right.
                root = root.right;
            } else {
                // perfectly balanced. return it
                return root;
            }
        }
        return root;
    }
}


// sep 28:
/*
analysis:
if perfectly balanced, return that node. (lowest common ancesotr)
if root.val is smaller than both p and q, move to the right.
if root.val is bigger than both p and q, move to the left. 

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                // 6, p = 3, q = 5. move to the left.
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }   

        return null;    
    }
}
