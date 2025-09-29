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


// sep 28 -- basic BFS algorithm. 

/*
analysis 
-> BFS (level by level)
-> queue / ans (queue --> root header part / ans <List Int>)
1. queue.add(root); // head. 
2. while (until queue is empty)
2.1 levelLength = queue.size() // based on level, it goes 1, 2, 4, 8, etc.
2.2 ans.add(new ArrayList()) // we need a list[] per level.
2.2 for (loop for each root within the level)
2.2.1 TreeNode curr = queue.poll(); // go each node by node.
2.2.2 if (crr.left != null) add, same for right.
2.4 currLevel++;

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
        List<List<Integer>> ans= new ArrayList();
        if (root == null) return ans; 

        queue.add(root); 
        int level = 0;
        while (!queue.isEmpty()) {
            int currLength = queue.size(); // start with 1.. 2.. 4.. 8.. and so on.
            ans.add(new ArrayList()); // for each level.

            for (int i = 0; i < currLength; i++) { // BFS each level by level.
                // go over each root, and add the below ones.
                TreeNode curr = queue.poll(); 
                ans.get(level).add(curr.val); // first, add that into the answer list. 
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            level++; // level it up to the next
        }

        return ans;
    }
}
