Deloitte 101
2 DSA & 2 SQLs / day
------------------

Days
--
1. Sliding Window & Hash (Arrays / Strings)
` longest substring without repeating characters (reported!)
` subarray sum equals k (prefix sum & hashmap)

3. Intervals / Sorting / Heaps
` merge intervals (sort & sweep)
` meeting rooms ii (min-heap of end times)

5. Stacks & Deques
` daily temperatures (monotonic stack)
` largest rectangle in histogram (monotinic stack)

7. Linked Lists
` reverse linkedlist (reported!)
` linked list cycle (detect start)

9. Trees & BSTs
` validate binary search tree (dfs with bounds)
` lowest common ancestor (binary tree + know BST shortcut)

11. Graphs & DSU & Binary Search
` roads to rome style traversal / shortest path (reported!)
` some graph adjacency + BFS / DFS depending on weights.

13. DP essentials & Mock
` coin change (min coins) --> bottom up DP
` longest increasing subsequence (O(nlog) method)

SQL Essentials
--
users(user_id, name, created_at)
orders(order_id, user_id, amount, status, created_at)
order_items(order_id, product_id, qty, price)
products(product_id, category, name)

JOINS (INNER, LEFT/RIGHT, FULL)
  Inner --> keeps only matching rows. 
  LEFT --> keeps all left rows (nulls on right)

GROUP BY / HAVING vs WHERE 
  WHERE filters rows before grouping. 
  HAVING filters 'after' aggregates. 

NULL semantics (COUNT*, COUNT(col), comparisons) 
  COUNT(*) counts rows
  COUNT(col) skips NULLs. 
  COALESCE to replace NULL. (ex: COALESCE(SUM(o.amount), 0)

Subqueries: correlated vs uncorrleated, EXISTS vs IN 
  EXISTS stops at first match, IN materiales list. 

Transactions / Isolation
  READ COMMITTED prevents dirty reads
  REPEATABLE READ also prevents non-repeatable reads
  SERIALIZABLE prevents phantoms (locking)

Normalization (1NF / 2NF / 3NF)
  1NF: atomic columns
  2NF: no partial key dependencies
  3NF: no transitive dependencies.

SQL Mock Prep
-

