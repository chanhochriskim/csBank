// jan 2 -- 12:15am ~ 12:25am (neetcode solution help)
/*
preorder --> first one's always the root.
using that, get the mid point from inorder. its' left side = root.left / root.right for right side.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]); // very first one on preorder.
        int mid = -1; 

        // get the mid value.
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                mid = i;
                break;
            }
        }

        // now, update root.left & root.right accordingly. 
        int[] left_preorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        int[] left_inorder = Arrays.copyOfRange(inorder, 0, mid);
        root.left = buildTree(left_preorder, left_inorder);

        int[] right_preorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
        int[] right_inorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);
        root.right = buildTree(right_preorder, right_inorder);

        return root;
    }
}
