// jan 23 - took about an hour (morning sesh cuz why not) ~ 
// dp... AND a Graph???!?!?!? i aint solving allat LMAO
// nick white's solution at around 14:00 
/* 
- have int max_val as global variable.
- recursively go over them, left right, then continue update the max sum.
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
    int maxsum;
    public int maxPathSum(TreeNode root) {
        maxsum = Integer.MIN_VALUE;
        helper(root);
        return maxsum;
    }

    public int helper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));
        maxsum = Math.max(maxsum, left + right + node.val);

        return Math.max(left, right) + node.val;
        // for this return value part, think of it as this way. for neetcode's 1 2 3 4 5 tree for example, the root node 1, would have 2 options, either sum up with 3 4 pair, or 3 5 pair. (cuz only 1 path)
    }
}
