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
        // use dfs to check if it's true.
        // if not triggered, recursive check the left and right side.
        // if either side is clicked, then return true.
        if (root == null) return false;
        if (dfs(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean dfs(TreeNode root, TreeNode subRoot) {
        // if both values are null, return true
        // if either one is null, meaning they aren't equivalent. return false
        // if values are not the same, return false
        // other than that, check if both left and right match each other.
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;

        return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
    }
}
