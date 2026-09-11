# 🧠 DSA Algorithms — Quick Revision Notes

A beginner-friendly, interview-focused revision sheet for Data Structures & Algorithms.

## 1. Complexity Basics

| Notation | Meaning | Example |
|---|---|---|
| O(1) | Constant | Array access |
| O(log n) | Logarithmic | Binary Search |
| O(n) | Linear | Linear Search |
| O(n log n) | Efficient sorting | Merge Sort, Heap Sort |
| O(n²) | Quadratic | Bubble Sort, simple nested loops |
| O(2ⁿ) | Exponential | Basic recursive subset generation |

**Rule:** Prefer lower time complexity, but also consider space complexity and implementation simplicity.

---

## 2. Searching Algorithms

### Linear Search — O(n)
Check every element until the target is found.

```python
def linear_search(arr, target):
    for i, value in enumerate(arr):
        if value == target:
            return i
    return -1
```

### Binary Search — O(log n)
Works only on a **sorted** array. Repeatedly discard half of the search space.

```python
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        if arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```

**Interview pattern:** first/last occurrence, lower bound, upper bound, search on answer.

---

## 3. Sorting Algorithms

### Bubble Sort — O(n²)
Repeatedly swap adjacent elements when they are in the wrong order.

### Selection Sort — O(n²)
Find the minimum element and place it at the current position.

### Insertion Sort — O(n²)
Build the sorted portion one element at a time. Good for small or nearly sorted data.

### Merge Sort — O(n log n)
Divide the array into halves, sort each half, then merge them.

**Space:** O(n)

### Quick Sort — Average O(n log n), Worst O(n²)
Choose a pivot, partition the array, then recursively sort both sides.

### Heap Sort — O(n log n)
Build a heap and repeatedly extract the maximum/minimum element.

**Remember:** Merge Sort guarantees O(n log n); Quick Sort is often very fast in practice but has a quadratic worst case.

---

## 4. Arrays & Strings

Important patterns:
- Traversal
- Prefix sum
- Difference array
- Two pointers
- Sliding window
- Kadane's algorithm
- Frequency counting
- Sorting + scanning

### Prefix Sum
`prefix[i]` stores the sum of elements up to index `i`.

Range sum from `l` to `r`:

`prefix[r] - prefix[l - 1]`

### Kadane's Algorithm — O(n)
Find maximum subarray sum.

```python
def max_subarray(arr):
    best = current = arr[0]
    for x in arr[1:]:
        current = max(x, current + x)
        best = max(best, current)
    return best
```

---

## 5. Two Pointers

Use two indexes moving through an array/string. Common when data is sorted or when searching for pairs.

Typical problems:
- Two Sum on sorted array
- Remove duplicates
- Container With Most Water
- Palindrome checking
- 3Sum

Typical complexity: **O(n)** after sorting/preprocessing.

---

## 6. Sliding Window

Maintain a moving window over contiguous elements instead of repeatedly calculating each range.

Useful for:
- Longest substring problems
- Maximum/minimum sum of a fixed-size window
- Longest subarray satisfying a condition

Typical complexity: **O(n)**.

---

## 7. Linked List

A linked list stores nodes connected by pointers/references.

Core operations:
- Insert at head — O(1)
- Search — O(n)
- Delete with known node/previous pointer — O(1)
- Access by index — O(n)

### Reverse a Linked List
```python
def reverse(head):
    prev = None
    curr = head
    while curr:
        nxt = curr.next
        curr.next = prev
        prev = curr
        curr = nxt
    return prev
```

### Fast & Slow Pointers
Use two pointers moving at different speeds.

Applications:
- Find middle node
- Detect cycle
- Find cycle entry
- Happy number style problems

---

## 8. Stack

**LIFO — Last In, First Out**

Applications:
- Parentheses matching
- Undo operations
- Function call stack
- Monotonic stack
- Expression evaluation
- Next Greater Element

Python: use `list.append()` and `list.pop()`.

---

## 9. Queue

**FIFO — First In, First Out**

Applications:
- BFS
- Scheduling
- Buffers
- Level-order tree traversal

Python:
```python
from collections import deque
q = deque()
q.append(10)
x = q.popleft()
```

---

## 10. Hashing

Hash tables provide average **O(1)** insertion, deletion and lookup.

Python tools:
- `dict`
- `set`
- `Counter`
- `defaultdict`

Common problems:
- Frequency counting
- Two Sum
- Duplicate detection
- Group Anagrams
- Longest consecutive sequence

---

## 11. Trees

### Binary Tree
Each node has at most two children.

Traversals:
- **Preorder:** Root → Left → Right
- **Inorder:** Left → Root → Right
- **Postorder:** Left → Right → Root
- **Level order:** BFS by levels

### Binary Search Tree
For each node:
- Left values < node
- Right values > node (for the standard unique-key version)

Average search/insert/delete: O(log n) when balanced.
Worst case: O(n).

---

## 12. Heap / Priority Queue

A heap gives efficient access to the minimum or maximum element.

Operations:
- Peek — O(1)
- Insert — O(log n)
- Remove top — O(log n)

Applications:
- Top K elements
- Kth largest/smallest
- Dijkstra's algorithm
- Scheduling
- Merge K sorted lists

Python:
```python
import heapq
heap = []
heapq.heappush(heap, 5)
smallest = heapq.heappop(heap)
```

---

## 13. Graphs

A graph contains **vertices/nodes** and **edges**.

Representations:
- Adjacency list — usually preferred for sparse graphs
- Adjacency matrix — useful for dense graphs or direct edge lookup

### BFS — O(V + E)
Uses a queue. Best for shortest path in an **unweighted** graph and level exploration.

### DFS — O(V + E)
Uses recursion or an explicit stack. Useful for connectivity, components and backtracking.

Important graph problems:
- Connected components
- Cycle detection
- Bipartite graph
- Topological sorting
- Shortest path
- Minimum spanning tree

---

## 14. Shortest Path Algorithms

| Algorithm | Main Use | Complexity |
|---|---|---|
| BFS | Unweighted graph | O(V + E) |
| Dijkstra | Non-negative edge weights | O((V+E) log V) with heap |
| Bellman-Ford | Negative edges allowed | O(VE) |
| Floyd-Warshall | All-pairs shortest paths | O(V³) |

**Important:** Dijkstra does not correctly handle negative edge weights.

---

## 15. Minimum Spanning Tree

Connect all vertices with minimum total edge weight and no cycles.

### Kruskal's Algorithm
- Sort edges by weight.
- Add an edge when it does not create a cycle.
- Uses **Disjoint Set Union (DSU)**.

Time: **O(E log E)**.

### Prim's Algorithm
Grow the MST from a starting vertex, repeatedly taking the cheapest connecting edge.

---

## 16. Greedy Algorithms

Make the best local choice at each step with the goal of obtaining a global optimum.

Examples:
- Activity selection
- Fractional knapsack
- Huffman coding
- Kruskal
- Prim

**Warning:** Greedy does not work for every optimisation problem. Prove or understand why the greedy choice is valid.

---

## 17. Recursion & Backtracking

### Recursion
A function solves a problem by calling itself on a smaller subproblem.

Always identify:
1. Base case
2. Recursive case
3. Progress toward the base case

### Backtracking
Try a choice → recurse → undo the choice.

Applications:
- Permutations
- Combinations
- N-Queens
- Sudoku
- Subsets
- Maze problems

---

## 18. Dynamic Programming

Use DP when a problem has:
- **Overlapping subproblems**
- **Optimal substructure**

Two styles:
- Top-down: recursion + memoisation
- Bottom-up: iterative tabulation

Classic problems:
- Fibonacci
- 0/1 Knapsack
- Coin Change
- Longest Common Subsequence
- Longest Increasing Subsequence
- House Robber
- Grid paths

**DP checklist:**
`State → Transition → Base case → Order → Answer`

---

## 19. Trie

A Trie stores strings character by character.

Applications:
- Prefix search
- Autocomplete
- Dictionary lookup
- Word search

Typical search/insert complexity: **O(L)** where `L` is the word length.

---

## 20. Disjoint Set Union (Union-Find)

Supports:
- `find(x)` — identify component
- `union(a, b)` — merge components

Use:
- Path compression
- Union by rank/size

With these optimisations, operations are effectively near O(1) amortised.

Applications:
- Kruskal's MST
- Dynamic connectivity
- Cycle detection in undirected graphs

---

## 🎯 Interview Pattern Cheat Sheet

| If you see... | Think... |
|---|---|
| Sorted array | Binary search / two pointers |
| Contiguous subarray/substring | Sliding window / prefix sum |
| Pair or triplet | Hashing / two pointers |
| Frequency | Hash map / Counter |
| Next greater/smaller | Monotonic stack |
| Top K | Heap |
| Shortest unweighted path | BFS |
| Shortest weighted path, no negative edges | Dijkstra |
| All combinations | Backtracking |
| Repeated subproblems | Dynamic Programming |
| Prefix matching | Trie |
| Components/connectivity | DFS/BFS/DSU |
| Dependency ordering | Topological sort |

## 🏆 DSA Study Order

`Arrays → Strings → Hashing → Two Pointers → Sliding Window → Stack → Queue → Linked List → Binary Search → Trees → Heap → Recursion → Backtracking → Graphs → Greedy → Dynamic Programming → Trie → DSU`

## ✅ Problem-Solving Method

1. Understand the problem.
2. Write a brute-force solution mentally.
3. Identify the bottleneck.
4. Match the problem to a known pattern.
5. Choose the data structure.
6. Analyse time and space complexity.
7. Code.
8. Test edge cases.
9. Optimise only when necessary.

> **DSA is not about memorising code. It is about recognising patterns and choosing the right tool.**
