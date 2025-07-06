// took me about 32 min.. had to take a look at geekforgeeks for how to use bfs.

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
        // queue (for node adder) and arrayList (result)
        Queue<TreeNode> queue = new LinkedList();
        List<List<Integer>> ans = new ArrayList();

        if (root == null) {
            // System.out.println("oops. null case.");
            return ans;
        }

        queue.add(root); // start with adding the root head!
        int currLevel = 0; // 0th level. (root)

        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            ans.add(new ArrayList()); // making new array list for each level.

            for (int i = 0; i < levelLength; i++) {
                TreeNode node = queue.poll(); // BFS order (root, left, right)
                ans.get(currLevel).add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            currLevel++;
        }

        return ans;
    }
}
