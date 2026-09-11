# 25 Arrays & Strings — Python Solutions

Each solution includes the core pattern and complexity.

## 1. Two Sum
```python
def two_sum(nums, target):
    seen = {}
    for i, x in enumerate(nums):
        if target - x in seen: return [seen[target-x], i]
        seen[x] = i
```
**O(n) time, O(n) space — Hashing**

## 2. Best Time to Buy and Sell Stock
```python
def max_profit(prices):
    low, ans = float('inf'), 0
    for p in prices:
        low = min(low, p); ans = max(ans, p-low)
    return ans
```
**O(n), O(1) — Greedy**

## 3. Maximum Subarray
```python
def max_sub_array(nums):
    cur = ans = nums[0]
    for x in nums[1:]: cur = max(x, cur+x); ans = max(ans, cur)
    return ans
```
**O(n), O(1) — Kadane**

## 4. Contains Duplicate
```python
def contains_duplicate(nums): return len(nums) != len(set(nums))
```
**O(n), O(n) — Set**

## 5. Product Except Self
```python
def product_except_self(nums):
    out=[1]*len(nums); p=1
    for i,x in enumerate(nums): out[i]=p; p*=x
    p=1
    for i in range(len(nums)-1,-1,-1): out[i]*=p; p*=nums[i]
    return out
```
**O(n), O(1) extra — Prefix/Suffix**

## 6. Maximum Product Subarray
```python
def max_product(nums):
    cur_max=cur_min=ans=nums[0]
    for x in nums[1:]:
        if x<0: cur_max,cur_min=cur_min,cur_max
        cur_max=max(x,cur_max*x); cur_min=min(x,cur_min*x); ans=max(ans,cur_max)
    return ans
```
**O(n), O(1) — DP**

## 7. Missing Number
```python
def missing_number(nums): return len(nums)*(len(nums)+1)//2-sum(nums)
```
**O(n), O(1) — Math**

## 8. Move Zeroes
```python
def move_zeroes(nums):
    j=0
    for x in nums:
        if x: nums[j]=x; j+=1
    while j<len(nums): nums[j]=0; j+=1
```
**O(n), O(1) — Two pointers**

## 9. Merge Sorted Arrays
```python
def merge(a,b):
    i=j=0; out=[]
    while i<len(a) and j<len(b):
        if a[i]<=b[j]: out.append(a[i]); i+=1
        else: out.append(b[j]); j+=1
    return out+a[i:]+b[j:]
```
**O(n+m), O(n+m) — Two pointers**

## 10. Remove Duplicates from Sorted Array
```python
def remove_duplicates(nums):
    if not nums:return 0
    j=1
    for i in range(1,len(nums)):
        if nums[i]!=nums[i-1]: nums[j]=nums[i]; j+=1
    return j
```
**O(n), O(1)**

## 11. Valid Anagram
```python
from collections import Counter
def is_anagram(s,t): return Counter(s)==Counter(t)
```
**O(n), O(n) — Hashing**

## 12. Valid Palindrome
```python
def is_palindrome(s):
    s=''.join(c.lower() for c in s if c.isalnum())
    return s==s[::-1]
```
**O(n), O(n)**

## 13. Longest Common Prefix
```python
def longest_common_prefix(strs):
    if not strs:return ''
    p=strs[0]
    for s in strs[1:]:
        while not s.startswith(p): p=p[:-1]
    return p
```
**O(total characters)**

## 14. Reverse String
```python
def reverse_string(s): return s[::-1]
```
**O(n)**

## 15. First Unique Character
```python
from collections import Counter
def first_unique(s):
    c=Counter(s)
    return next((i for i,x in enumerate(s) if c[x]==1),-1)
```
**O(n), O(n)**

## 16. Group Anagrams
```python
from collections import defaultdict
def group_anagrams(strs):
    d=defaultdict(list)
    for s in strs: d[''.join(sorted(s))].append(s)
    return list(d.values())
```
**O(n·k log k)**

## 17. Longest Substring Without Repeating Characters
```python
def length_of_longest_substring(s):
    seen={}; left=ans=0
    for right,c in enumerate(s):
        if c in seen: left=max(left,seen[c]+1)
        seen[c]=right; ans=max(ans,right-left+1)
    return ans
```
**O(n), O(n) — Sliding window**

## 18. Longest Repeating Character Replacement
```python
def character_replacement(s,k):
    from collections import Counter
    c=Counter(); left=ans=0
    for right,x in enumerate(s):
        c[x]+=1
        while right-left+1-max(c.values())>k: c[s[left]]-=1; left+=1
        ans=max(ans,right-left+1)
    return ans
```
**O(n), O(1) alphabet — Sliding window**

## 19. Valid Parentheses
```python
def valid_parentheses(s):
    st=[]; pairs={')':'(',']':'[','}':'{'}
    for c in s:
        if c in pairs:
            if not st or st.pop()!=pairs[c]: return False
        else: st.append(c)
    return not st
```
**O(n), O(n) — Stack**

## 20. Three Sum
```python
def three_sum(nums):
    nums.sort(); out=[]
    for i in range(len(nums)-2):
        if i and nums[i]==nums[i-1]: continue
        l,r=i+1,len(nums)-1
        while l<r:
            s=nums[i]+nums[l]+nums[r]
            if s==0: out.append([nums[i],nums[l],nums[r]]); l+=1; r-=1
            elif s<0:l+=1
            else:r-=1
    return out
```
**O(n²), O(1) extra — Sorting + two pointers**

## 21. Container With Most Water
```python
def max_area(h):
    l,r=0,len(h)-1; ans=0
    while l<r:
        ans=max(ans,(r-l)*min(h[l],h[r]))
        if h[l]<h[r]: l+=1
        else:r-=1
    return ans
```
**O(n), O(1) — Two pointers**

## 22. Majority Element
```python
def majority_element(nums):
    cand=count=0
    for x in nums:
        if count==0:cand=x
        count += 1 if x==cand else -1
    return cand
```
**O(n), O(1) — Boyer-Moore**

## 23. Rotate Array
```python
def rotate(nums,k):
    k%=len(nums); nums[:]=nums[-k:]+nums[:-k]
```
**O(n), O(n) with slicing**

## 24. Find Pivot Index
```python
def pivot_index(nums):
    total=sum(nums); left=0
    for i,x in enumerate(nums):
        if left==total-left-x:return i
        left+=x
    return -1
```
**O(n), O(1) — Prefix sum**

## 25. Merge Intervals
```python
def merge_intervals(intervals):
    intervals.sort(); out=[]
    for a,b in intervals:
        if not out or a>out[-1][1]: out.append([a,b])
        else: out[-1][1]=max(out[-1][1],b)
    return out
```
**O(n log n), O(n) — Sorting + intervals**
