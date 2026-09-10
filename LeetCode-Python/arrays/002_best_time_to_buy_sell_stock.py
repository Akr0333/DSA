from typing import List

def max_profit(prices: List[int]) -> int:
    best = 0
    lowest = float('inf')
    for price in prices:
        lowest = min(lowest, price)
        best = max(best, price - lowest)
    return best

# Time: O(n) | Space: O(1)
