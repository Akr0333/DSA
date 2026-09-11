# 25 Greedy, DP & Backtracking — Python Solutions

## 76. Climbing Stairs
```python
def climb(n):
    a,b=1,1
    for _ in range(n):a,b=b,a+b
    return a
```
**O(n), O(1) — DP**

## 77. House Robber
```python
def rob(nums):
    prev2=prev1=0
    for x in nums:prev2,prev1=prev1,max(prev1,prev2+x)
    return prev1
```
**O(n), O(1) — DP**

## 78. Coin Change
```python
def coin_change(coins,amount):
    dp=[amount+1]*(amount+1);dp[0]=0
    for a in range(1,amount+1):
        for c in coins:
            if c<=a:dp[a]=min(dp[a],1+dp[a-c])
    return -1 if dp[amount]>amount else dp[amount]
```
**O(amount × coins), O(amount)**

## 79. Maximum Sum of Non-Adjacent Elements
```python
def max_non_adj(a):
    take=skip=0
    for x in a:take,skip=skip+x,max(take,skip)
    return max(take,skip)
```
**O(n), O(1)**

## 80. Unique Paths
```python
def unique_paths(m,n):
    dp=[1]*n
    for _ in range(1,m):
        for j in range(1,n):dp[j]+=dp[j-1]
    return dp[-1]
```
**O(mn), O(n)**

## 81. Minimum Path Sum
```python
def min_path(grid):
    dp=grid[0][:]
    for r in range(1,len(grid)):
        dp[0]+=grid[r][0]
        for c in range(1,len(grid[0])):dp[c]=grid[r][c]+min(dp[c],dp[c-1])
    return dp[-1]
```
**O(mn), O(n)**

## 82. Longest Increasing Subsequence
```python
from bisect import bisect_left
def lis(nums):
    d=[]
    for x in nums:
        i=bisect_left(d,x)
        if i==len(d):d.append(x)
        else:d[i]=x
    return len(d)
```
**O(n log n), O(n)**

## 83. Longest Common Subsequence
```python
def lcs(a,b):
    dp=[0]*(len(b)+1)
    for x in a:
        old=0
        for j,y in enumerate(b,1):
            cur=dp[j]
            dp[j]=old+1 if x==y else max(dp[j],dp[j-1])
            old=cur
    return dp[-1]
```
**O(nm), O(m)**

## 84. 0/1 Knapsack
```python
def knapsack(w,v,W):
    dp=[0]*(W+1)
    for weight,value in zip(w,v):
        for cap in range(W,weight-1,-1):dp[cap]=max(dp[cap],dp[cap-weight]+value)
    return dp[W]
```
**O(nW), O(W)**

## 85. Partition Equal Subset Sum
```python
def can_partition(nums):
    s=sum(nums)
    if s%2:return False
    target=s//2; dp={0}
    for x in nums:dp|={y+x for y in list(dp) if y+x<=target}
    return target in dp
```
**O(n·target) typical — Set DP**

## 86. Word Break
```python
def word_break(s,words):
    words=set(words);dp=[False]*(len(s)+1);dp[0]=True
    for i in range(1,len(s)+1):
        dp[i]=any(dp[j] and s[j:i] in words for j in range(i))
    return dp[-1]
```
**O(n²) substring checks — DP**

## 87. Decode Ways
```python
def decode_ways(s):
    if not s or s[0]=='0':return 0
    a,b=1,1
    for i in range(1,len(s)):
        c=0
        if s[i]!='0':c+=b
        if 10<=int(s[i-1:i+1])<=26:c+=a
        a,b=b,c
    return b
```
**O(n), O(1)**

## 88. Edit Distance
```python
def edit_distance(a,b):
    dp=list(range(len(b)+1))
    for i,x in enumerate(a,1):
        prev=dp[0];dp[0]=i
        for j,y in enumerate(b,1):
            cur=dp[j]
            dp[j]=prev if x==y else 1+min(prev,dp[j],dp[j-1])
            prev=cur
    return dp[-1]
```
**O(nm), O(m)**

## 89. Jump Game
```python
def can_jump(nums):
    reach=0
    for i,x in enumerate(nums):
        if i>reach:return False
        reach=max(reach,i+x)
    return True
```
**O(n), O(1) — Greedy**

## 90. Jump Game II
```python
def min_jumps(nums):
    jumps=end=far=0
    for i in range(len(nums)-1):
        far=max(far,i+nums[i])
        if i==end:jumps+=1;end=far
    return jumps
```
**O(n), O(1) — Greedy**

## 91. Gas Station
```python
def gas_station(gas,cost):
    if sum(gas)<sum(cost):return -1
    start=tank=0
    for i in range(len(gas)):
        tank+=gas[i]-cost[i]
        if tank<0:start=i+1;tank=0
    return start
```
**O(n), O(1) — Greedy**

## 92. Assign Cookies
```python
def assign_cookies(g,s):
    g.sort();s.sort();i=0
    for x in s:
        if i<len(g) and x>=g[i]:i+=1
    return i
```
**O(n log n + m log m) — Greedy**

## 93. Activity Selection
```python
def activity_selection(intervals):
    intervals.sort(key=lambda x:x[1]); end=float('-inf');count=0
    for s,e in intervals:
        if s>=end:count+=1;end=e
    return count
```
**O(n log n) — Greedy**

## 94. Subsets
```python
def subsets(nums):
    out=[[]]
    for x in nums:out += [a+[x] for a in out]
    return out
```
**O(2ⁿ), output-sensitive — Backtracking/iteration**

## 95. Permutations
```python
def permutations(nums):
    out=[]
    def bt(path,left):
        if not left:out.append(path[:]);return
        for i,x in enumerate(left):bt(path+[x],left[:i]+left[i+1:])
    bt([],nums);return out
```
**O(n·n!)**

## 96. Combination Sum
```python
def combination_sum(candidates,target):
    out=[]
    def bt(start,total,path):
        if total==target:out.append(path[:]);return
        for i in range(start,len(candidates)):
            x=candidates[i]
            if total+x>target:break
            bt(i,total+x,path+[x])
    candidates.sort();bt(0,0,[]);return out
```
**Exponential — Backtracking**

## 97. Generate Parentheses
```python
def generate_parenthesis(n):
    out=[]
    def bt(s,o,c):
        if len(s)==2*n:out.append(s);return
        if o<n:bt(s+'(',o+1,c)
        if c<o:bt(s+')',o,c+1)
    bt('',0,0);return out
```
**O(Cₙ) outputs — Backtracking**

## 98. N-Queens
```python
def solve_n_queens(n):
    out=[];cols=set();d1=set();d2=set();board=[['.']*n for _ in range(n)]
    def bt(r):
        if r==n:out.append([''.join(x) for x in board]);return
        for c in range(n):
            if c in cols or r-c in d1 or r+c in d2:continue
            cols.add(c);d1.add(r-c);d2.add(r+c);board[r][c]='Q';bt(r+1)
            board[r][c]='.';cols.remove(c);d1.remove(r-c);d2.remove(r+c)
    bt(0);return out
```
**Backtracking; exponential**

## 99. Letter Combinations of a Phone Number
```python
def letter_combinations(digits):
    if not digits:return []
    mp=['','','abc','def','ghi','jkl','mno','pqrs','tuv','wxyz'];out=[]
    def bt(i,s):
        if i==len(digits):out.append(s);return
        for c in mp[int(digits[i])]:bt(i+1,s+c)
    bt(0,'');return out
```
**O(4ⁿ · n) — Backtracking**

## 100. Palindrome Partitioning
```python
def partition(s):
    out=[]
    def bt(i,path):
        if i==len(s):out.append(path[:]);return
        for j in range(i+1,len(s)+1):
            if s[i:j]==s[i:j][::-1]:bt(j,path+[s[i:j]])
    bt(0,[]);return out
```
**Exponential — Backtracking**

---

## 🎯 Pattern Summary

**76–88:** Dynamic Programming  
**89–93:** Greedy  
**94–100:** Backtracking / Recursion

You now have a complete **100-problem DSA practice set** across arrays, strings, linked lists, stacks, queues, trees, graphs, greedy, DP and backtracking.
