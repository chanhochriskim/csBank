// ~15 min but still getting used to the recursive.
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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return dfs(root, 1);
    }

    public int dfs(TreeNode root, int count) {
        if (root == null) return count - 1; // because we count up to that node.

        int left = dfs(root.left, count++);
        int right = dfs(root.right, count++);
        int highest = Math.max(left, right);


        return highest;
    }
}

// sep 27
/*
dfs(root, 1); // root, starts at count 1.
int left, right, each increment recursively -- dfs(root.left/root.right, count++);
--> get the maxvalue between them. math.max(left, right) for the deepest.

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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return dfs(root, 1);
    }

    public int dfs(TreeNode root, int count) {
        // left and right i guess.
        if (root == null) {
            return count - 1;
        }

        int left = dfs(root.left, count++);
        int right = dfs(root.right, count++);
        
        return Math.max(left, right);
    }
}
