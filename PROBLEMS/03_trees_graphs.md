# 25 Trees & Graphs — Python Solutions

## 51. Maximum Depth of Binary Tree
```python
def max_depth(root):
    if not root:return 0
    return 1+max(max_depth(root.left),max_depth(root.right))
```
**O(n), O(h)**

## 52. Inorder Traversal
```python
def inorder(root):
    if not root:return []
    return inorder(root.left)+[root.val]+inorder(root.right)
```
**O(n)**

## 53. Preorder Traversal
```python
def preorder(root):
    if not root:return []
    return [root.val]+preorder(root.left)+preorder(root.right)
```
**O(n)**

## 54. Postorder Traversal
```python
def postorder(root):
    if not root:return []
    return postorder(root.left)+postorder(root.right)+[root.val]
```
**O(n)**

## 55. Level Order Traversal
```python
from collections import deque
def level_order(root):
    if not root:return []
    q=deque([root]); out=[]
    while q:
        level=[]
        for _ in range(len(q)):
            n=q.popleft(); level.append(n.val)
            if n.left:q.append(n.left)
            if n.right:q.append(n.right)
        out.append(level)
    return out
```
**O(n), O(n) — BFS**

## 56. Invert Binary Tree
```python
def invert(root):
    if root: root.left,root.right=invert(root.right),invert(root.left)
    return root
```
**O(n)**

## 57. Same Tree
```python
def same(a,b):
    if not a or not b:return a is b
    return a.val==b.val and same(a.left,b.left) and same(a.right,b.right)
```
**O(n)**

## 58. Validate BST
```python
def valid_bst(root,lo=float('-inf'),hi=float('inf')):
    if not root:return True
    return lo<root.val<hi and valid_bst(root.left,lo,root.val) and valid_bst(root.right,root.val,hi)
```
**O(n), O(h)**

## 59. Lowest Common Ancestor in BST
```python
def lca(root,p,q):
    while root:
        if p.val<root.val and q.val<root.val:root=root.left
        elif p.val>root.val and q.val>root.val:root=root.right
        else:return root
```
**O(h)**

## 60. Diameter of Binary Tree
```python
def diameter(root):
    ans=0
    def depth(n):
        nonlocal ans
        if not n:return 0
        l,r=depth(n.left),depth(n.right); ans=max(ans,l+r)
        return 1+max(l,r)
    depth(root); return ans
```
**O(n), O(h)**

## 61. Balanced Binary Tree
```python
def balanced(root):
    def h(n):
        if not n:return 0
        l,r=h(n.left),h(n.right)
        if l==-1 or r==-1 or abs(l-r)>1:return -1
        return 1+max(l,r)
    return h(root)!=-1
```
**O(n)**

## 62. Kth Smallest in BST
```python
def kth_smallest(root,k):
    st=[]
    while True:
        while root:st.append(root); root=root.left
        root=st.pop(); k-=1
        if k==0:return root.val
        root=root.right
```
**O(h+k), O(h)**

## 63. Subtree of Another Tree
```python
def is_subtree(root,sub):
    def same(a,b):
        if not a or not b:return a is b
        return a.val==b.val and same(a.left,b.left) and same(a.right,b.right)
    if not sub:return True
    if not root:return False
    return same(root,sub) or is_subtree(root.left,sub) or is_subtree(root.right,sub)
```
**O(n·m) worst case**

## 64. Binary Tree Right Side View
```python
from collections import deque
def right_view(root):
    if not root:return []
    q=deque([root]); out=[]
    while q:
        for i in range(len(q)):
            n=q.popleft()
            if n.left:q.append(n.left)
            if n.right:q.append(n.right)
            if i==0:last=n.val
        out.append(last)
    return out
```
**O(n)**

## 65. Number of Islands
```python
def num_islands(g):
    R,C=len(g),len(g[0]); ans=0
    def dfs(r,c):
        if r<0 or c<0 or r>=R or c>=C or g[r][c]!='1':return
        g[r][c]='0'
        for dr,dc in ((1,0),(-1,0),(0,1),(0,-1)):dfs(r+dr,c+dc)
    for r in range(R):
        for c in range(C):
            if g[r][c]=='1':ans+=1; dfs(r,c)
    return ans
```
**O(RC), O(RC) worst-case stack**

## 66. Clone Graph
```python
def clone_graph(node):
    seen={}
    def dfs(n):
        if n in seen:return seen[n]
        copy=Node(n.val); seen[n]=copy
        copy.neighbors=[dfs(x) for x in n.neighbors]
        return copy
    return dfs(node) if node else None
```
**O(V+E)**

## 67. Course Schedule
```python
from collections import deque
def can_finish(n,pre):
    g=[[] for _ in range(n)]; deg=[0]*n
    for a,b in pre:g[b].append(a); deg[a]+=1
    q=deque(i for i in range(n) if deg[i]==0); done=0
    while q:
        x=q.popleft(); done+=1
        for y in g[x]:
            deg[y]-=1
            if deg[y]==0:q.append(y)
    return done==n
```
**O(V+E) — Topological sort**

## 68. Rotting Oranges
```python
from collections import deque
def oranges(grid):
    q=deque(); fresh=0
    for r,row in enumerate(grid):
        for c,x in enumerate(row):
            if x==2:q.append((r,c))
            elif x==1:fresh+=1
    t=0
    while q and fresh:
        for _ in range(len(q)):
            r,c=q.popleft()
            for dr,dc in ((1,0),(-1,0),(0,1),(0,-1)):
                nr,nc=r+dr,c+dc
                if 0<=nr<len(grid) and 0<=nc<len(grid[0]) and grid[nr][nc]==1:
                    grid[nr][nc]=2; fresh-=1; q.append((nr,nc))
        t+=1
    return t if fresh==0 else -1
```
**O(RC), O(RC) — Multi-source BFS**

## 69. Flood Fill
```python
def flood_fill(img,sr,sc,color):
    old=img[sr][sc]
    if old==color:return img
    def dfs(r,c):
        if r<0 or c<0 or r>=len(img) or c>=len(img[0]) or img[r][c]!=old:return
        img[r][c]=color
        for dr,dc in ((1,0),(-1,0),(0,1),(0,-1)):dfs(r+dr,c+dc)
    dfs(sr,sc); return img
```
**O(RC)**

## 70. Graph Valid Tree
```python
def valid_tree(n,edges):
    if len(edges)!=n-1:return False
    g=[[] for _ in range(n)]
    for a,b in edges:g[a].append(b);g[b].append(a)
    seen={0}; st=[0]
    while st:
        for x in g[st.pop()]:
            if x not in seen:seen.add(x);st.append(x)
    return len(seen)==n
```
**O(V+E)**

## 71. Dijkstra Shortest Path
```python
import heapq
def dijkstra(g,start):
    d={start:0}; pq=[(0,start)]
    while pq:
        dist,u=heapq.heappop(pq)
        if dist!=d[u]:continue
        for v,w in g[u]:
            nd=dist+w
            if nd<d.get(v,float('inf')):d[v]=nd;heapq.heappush(pq,(nd,v))
    return d
```
**O((V+E) log V) — non-negative weights**

## 72. Network Delay Time
```python
def network_delay(times,n,k):
    g=[[] for _ in range(n+1)]
    for u,v,w in times:g[u].append((v,w))
    d=dijkstra(g,k); return max(d.values()) if len(d)==n else -1
```
**O((V+E) log V)**

## 73. Connected Components
```python
def components(n,edges):
    g=[[] for _ in range(n)]
    for a,b in edges:g[a].append(b);g[b].append(a)
    seen=set(); ans=0
    for s in range(n):
        if s in seen:continue
        ans+=1; st=[s]; seen.add(s)
        while st:
            for x in g[st.pop()]:
                if x not in seen:seen.add(x);st.append(x)
    return ans
```
**O(V+E) — DFS**

## 74. Bipartite Graph
```python
from collections import deque
def bipartite(g):
    color={}
    for s in range(len(g)):
        if s in color:continue
        color[s]=0;q=deque([s])
        while q:
            u=q.popleft()
            for v in g[u]:
                if v not in color:color[v]=1-color[u];q.append(v)
                elif color[v]==color[u]:return False
    return True
```
**O(V+E)**

## 75. Course Schedule Order
```python
from collections import deque
def order(n,pre):
    g=[[] for _ in range(n)]; deg=[0]*n
    for a,b in pre:g[b].append(a);deg[a]+=1
    q=deque(i for i in range(n) if not deg[i]);out=[]
    while q:
        u=q.popleft();out.append(u)
        for v in g[u]:deg[v]-=1; q.append(v) if deg[v]==0 else None
    return out if len(out)==n else []
```
**O(V+E) — Topological sort**
