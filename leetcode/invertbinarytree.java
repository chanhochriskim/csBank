// sep 27 
/*
- for tree problem, basically using temp value, to flip left and right.
- then, recursively call the function, with func(root.left) and func(root.right)


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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left; // 2
        root.left = root.right; // root.left = 7
        root.right = temp; // root.right = 2

        invertTree(root.left);
        invertTree(root.right); 

        return root;
    }
}
