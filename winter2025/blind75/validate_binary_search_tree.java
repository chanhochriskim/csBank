// jan 1 - 12:15pm ~ 12:25pm 
/*
basically, use helper function (root, min, max). for root.left, max should be capped to root.val / and min for root.right. here, return false if root.val is out of bound. if root == null,return true (means everything's checked without throwing an error)

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
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // if root == null, true. if root.val = out of bound false.
    public boolean helper(TreeNode root, long min, long max) {
        if (root == null) return true;
        if ((long) root.val <= min || (long) root.val >= max) return false;
        return helper(root.left, min, (long) root.val) && helper(root.right, (long) root.val, max);
    }
}
