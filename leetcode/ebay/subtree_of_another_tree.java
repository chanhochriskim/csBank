// dec 8 -- ebay prep 11:00pm ~ 11:05pm
/*
- dfs to check if it's true. 
- if not triggered, recursive check the left and right sides.
- on dfs, if both are null --> they are equivalent. 
    - other than that, check if both left & right match each other

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (dfs(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if ((root == null || subRoot == null) || (root.val != subRoot.val)) return false;
        return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
    }
}
