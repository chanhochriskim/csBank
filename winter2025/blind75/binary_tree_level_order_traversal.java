// dec 31 -- 8:20pm ~ 8:35pm 
/*
- BFS (queue).
- level (which determine which i-th array list)
- currLength (to determine how long inner loop will be each time)
- if curr.left or right not null --> add them to the queue for next round.
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        List<List<Integer>> ans = new ArrayList();
        if (root == null) return ans;

        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int currLength = queue.size(); // 1, 2, 4, 8..
            ans.add(new ArrayList());
            
            for (int i = 0; i < currLength; i++) { // BFS
                TreeNode curr = queue.poll();
                ans.get(level).add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            level++;
        }

        return ans;
    }
}
