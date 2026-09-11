# 25 Linked List, Stack & Queue — Python Solutions

## 26. Reverse Linked List
```python
def reverse(head):
    prev=None; cur=head
    while cur:
        nxt=cur.next; cur.next=prev; prev=cur; cur=nxt
    return prev
```
**O(n), O(1)**

## 27. Middle of Linked List
```python
def middle(head):
    slow=fast=head
    while fast and fast.next: slow=slow.next; fast=fast.next.next
    return slow
```
**O(n), O(1) — Fast/slow**

## 28. Linked List Cycle
```python
def has_cycle(head):
    slow=fast=head
    while fast and fast.next:
        slow=slow.next; fast=fast.next.next
        if slow is fast:return True
    return False
```
**O(n), O(1)**

## 29. Merge Two Sorted Lists
```python
def merge_lists(a,b):
    dummy=cur=ListNode(0)
    while a and b:
        if a.val<b.val: cur.next=a; a=a.next
        else: cur.next=b; b=b.next
        cur=cur.next
    cur.next=a or b
    return dummy.next
```
**O(n+m), O(1) extra**

## 30. Remove Nth Node From End
```python
def remove_nth(head,n):
    d=ListNode(0); d.next=head; a=b=d
    for _ in range(n): b=b.next
    while b.next: a=a.next; b=b.next
    a.next=a.next.next
    return d.next
```
**O(n), O(1)**

## 31. Palindrome Linked List
```python
def is_palindrome_list(head):
    vals=[]
    while head: vals.append(head.val); head=head.next
    return vals==vals[::-1]
```
**O(n), O(n)**

## 32. Intersection of Two Linked Lists
```python
def intersection(a,b):
    x,y=a,b
    while x is not y:
        x=x.next if x else b; y=y.next if y else a
    return x
```
**O(n+m), O(1)**

## 33. Design Stack Using Array
```python
class Stack:
    def __init__(self): self.a=[]
    def push(self,x): self.a.append(x)
    def pop(self): return self.a.pop()
    def top(self): return self.a[-1]
```
**O(1) amortised operations**

## 34. Min Stack
```python
class MinStack:
    def __init__(self): self.a=[]
    def push(self,x): self.a.append((x,min(x,self.a[-1][1]) if self.a else x))
    def pop(self): return self.a.pop()[0]
    def getMin(self): return self.a[-1][1]
```
**O(1) all operations**

## 35. Evaluate Reverse Polish Notation
```python
def eval_rpn(tokens):
    st=[]
    for t in tokens:
        if t not in '+-*/': st.append(int(t)); continue
        b,a=st.pop(),st.pop(); st.append(int(eval(f'{a}{t}{b}')))
    return st[-1]
```
**O(n), O(n)**

## 36. Daily Temperatures
```python
def daily_temperatures(t):
    ans=[0]*len(t); st=[]
    for i,x in enumerate(t):
        while st and t[st[-1]]<x:
            j=st.pop(); ans[j]=i-j
        st.append(i)
    return ans
```
**O(n), O(n) — Monotonic stack**

## 37. Next Greater Element
```python
def next_greater(nums):
    ans=[-1]*len(nums); st=[]
    for i,x in enumerate(nums):
        while st and nums[st[-1]]<x: ans[st.pop()]=x
        st.append(i)
    return ans
```
**O(n), O(n)**

## 38. Simplify Path
```python
def simplify_path(path):
    st=[]
    for p in path.split('/'):
        if p in ('','.'): continue
        if p=='..':
            if st: st.pop()
        else: st.append(p)
    return '/'+ '/'.join(st)
```
**O(n), O(n)**

## 39. Backspace String Compare
```python
def build(s):
    st=[]
    for c in s:
        if c=='#':
            if st: st.pop()
        else: st.append(c)
    return st
def backspace_compare(s,t): return build(s)==build(t)
```
**O(n), O(n)**

## 40. Implement Queue Using Two Stacks
```python
class MyQueue:
    def __init__(self): self.a=[]; self.b=[]
    def push(self,x): self.a.append(x)
    def pop(self):
        if not self.b:
            while self.a:self.b.append(self.a.pop())
        return self.b.pop()
```
**Amortised O(1)**

## 41. Number of Recent Calls
```python
from collections import deque
class RecentCounter:
    def __init__(self): self.q=deque()
    def ping(self,t):
        self.q.append(t)
        while self.q[0]<t-3000:self.q.popleft()
        return len(self.q)
```
**O(1) amortised**

## 42. Moving Average From Data Stream
```python
from collections import deque
class MovingAverage:
    def __init__(self,size): self.size=size; self.q=deque(); self.total=0
    def next(self,val):
        self.q.append(val); self.total+=val
        if len(self.q)>self.size:self.total-=self.q.popleft()
        return self.total/len(self.q)
```
**O(1) amortised**

## 43. Valid Parentheses
```python
def valid(s):
    st=[]; p={')':'(',']':'[','}':'{'}
    for c in s:
        if c in p:
            if not st or st.pop()!=p[c]:return False
        else:st.append(c)
    return not st
```
**O(n), O(n)**

## 44. Remove All Adjacent Duplicates
```python
def remove_duplicates(s):
    st=[]
    for c in s:
        if st and st[-1]==c:st.pop()
        else:st.append(c)
    return ''.join(st)
```
**O(n), O(n)**

## 45. Asteroid Collision
```python
def asteroid_collision(a):
    st=[]
    for x in a:
        alive=True
        while alive and x<0 and st and st[-1]>0:
            if st[-1]<-x: st.pop()
            elif st[-1]==-x: st.pop(); alive=False
            else: alive=False
        if alive:st.append(x)
    return st
```
**O(n), O(n)**

## 46. Largest Rectangle in Histogram
```python
def largest_rectangle(h):
    st=[]; ans=0; h.append(0)
    for i,x in enumerate(h):
        while st and h[st[-1]]>x:
            height=h[st.pop()]; left=st[-1] if st else -1
            ans=max(ans,height*(i-left-1))
        st.append(i)
    return ans
```
**O(n), O(n) — Monotonic stack**

## 47. Design Circular Queue
```python
from collections import deque
class CircularQueue:
    def __init__(self,k): self.q=deque(maxlen=k)
    def enQueue(self,x):
        if len(self.q)==self.q.maxlen:return False
        self.q.append(x); return True
    def deQueue(self):
        if not self.q:return False
        self.q.popleft(); return True
```
**O(1)**

## 48. Decode String
```python
def decode(s):
    st=[]; num=0; cur=''
    for c in s:
        if c.isdigit(): num=num*10+int(c)
        elif c=='[': st.append((cur,num)); cur=''; num=0
        elif c==']': prev,k=st.pop(); cur=prev+cur*k
        else: cur+=c
    return cur
```
**O(output size)**

## 49. Remove K Digits
```python
def remove_k_digits(num,k):
    st=[]
    for c in num:
        while k and st and st[-1]>c:st.pop(); k-=1
        st.append(c)
    st=st[:-k] if k else st
    return ''.join(st).lstrip('0') or '0'
```
**O(n), O(n) — Greedy + stack**

## 50. Trapping Rain Water
```python
def trap(h):
    l,r=0,len(h)-1; lm=rm=ans=0
    while l<r:
        if h[l]<h[r]:
            lm=max(lm,h[l]); ans+=lm-h[l]; l+=1
        else:
            rm=max(rm,h[r]); ans+=rm-h[r]; r-=1
    return ans
```
**O(n), O(1) — Two pointers**
