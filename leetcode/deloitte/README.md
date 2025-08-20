Deloitte 101
------------------

Days
--
1. Sliding Window & Hash (Arrays / Strings)
` [solved, aug 19] longest substring without repeating characters (reported!)
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
covering: concepts / implementation / debugging / design

1. Concepts
- INNER vs LEFT JOIN
  Inner keeps only matching rows, whereas LEFT keeps all left rows and NULLs for missing right. Use Coalesce to avoid NULL and compute 0. (left is slower? --> depends on indexes)
- WHERE vs HAVING, what's the difference?
  WHERE filters before grouping, HAVING filters after aggregates. (ex: WHERE status = 'success', THEN group by user_id & HAVING COUNT(*) > 5)
- COUNT(*) vs COUNT(col) with NULLs
  COUNT(*) counts rows, COUNT(col) ignores NULLs. Again, should use COALESCE to aggregate null values.
- Isolation Level (Dirty / Non-Repeatable / Phantom
  Read Committed: no dirty reads.
  Repeatable Read: also prevents non-repeatable reads.
  Serializable: prevents phantoms.

2. Implementation
- Users with total orders & revenue (includes uesrs with 0 order).
  prompt: return user_id, order_cnt, revenue, counting 0 for users with no order.
SELECT u.user_id,
       COUNT(o.order_id) AS order_cnt,
       COALESCE(SUM(o.amount), 0) AS revenue
FROM users u
LEFT JOIN orders o ON o.user_id = u.user_id
GROUP BY u.user_id;

- TOP 3 users by monthly revenue (ties allowed) -- postgreSQL style
WITH monthly AS (
  SELECT o.user_id,
         DATE_TRUNC('month', o.created_at) AS m,
         SUM(o.amount) AS rev
  FROM orders o
  GROUP BY o.user_id, DATE_TRUNC('month', o.created_at)
)
SELECT user_id, m, rev
FROM (
  SELECT user_id, m, rev,
         RANK() OVER (PARTITION BY m ORDER BY rev DESC) AS rnk
  FROM monthly
) t
WHERE rnk <= 3;

- Users who purchased in Jan but not in Feb (anti-join)
WITH jan AS (
  SELECT DISTINCT user_id FROM orders
  WHERE created_at >= '2025-01-01' AND created_at < '2025-02-01'
),
feb AS (
  SELECT DISTINCT user_id FROM orders
  WHERE created_at >= '2025-02-01' AND created_at < '2025-03-01'
)
SELECT j.user_id
FROM jan j
WHERE NOT EXISTS (SELECT 1 FROM feb f WHERE f.user_id=j.user_id)

- Second highest order amount per user
SELECT user_id, amount
FROM (
  SELECT o.user_id, o.amount,
         ROW_NUMBER() OVER (PARTITION BY o.user_id ORDER BY o.amount DESC) AS rn
  FROM orders o
) t
WHERE rn = 2;

- Top product by revenue per category
WITH rev AS (
  SELECT p.category, oi.product_id, SUM(oi.qty * oi.price) AS revenue
  FROM order_items oi
  JOIN products p ON p.product_id = oi.product_id
  GROUP BY p.category, oi.product_id
)
SELECT category, product_id, revenue
FROM (
  SELECT r.*,
         ROW_NUMBER() OVER (PARTITION BY category ORDER BY revenue DESC) AS rn
  FROM rev r
) x
WHERE rn = 1;

3. Debugging / Refactor Promps
- <img width="670" height="466" alt="Screenshot 2025-08-19 at 10 02 07 PM" src="https://github.com/user-attachments/assets/54303552-0bf4-4e7b-88ea-48d2f244cca3" />

- Why is my monthly rank wrong?
  Because I forgot PARTITION BY month, which made me rank globally, not per month.

4. Optimization and Design
Q: Given SELECT ... FROM users u JOIN orders o ON o.user_id=u.user_id WHERE o.created_at >= ..., which indexes help?
A: Composite on orders(user_id, created_at) covers join + filter; users(user_id) is PK. If sorting by created_at, same composite helps.

Q: For order_items join flood, what’s essential?
A: Index on order_items(order_id), possibly (product_id) if frequently filtered/grouped by product or category (with join to products).

Q: Big aggregation by day is slow—what next?
A: Check plan; ensure index on orders(created_at); consider pre-aggregating into a daily fact table if this is a heavy recurring report.
