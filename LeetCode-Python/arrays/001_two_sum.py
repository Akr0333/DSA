from typing import List


def two_sum(nums: List[int], target: int) -> List[int]:
    seen = {}
    for i, value in enumerate(nums):
        need = target - value
        if need in seen:
            return [seen[need], i]
        seen[value] = i
    return []


if __name__ == "__main__":
    print(two_sum([2, 7, 11, 15], 9))

# Time: O(n) | Space: O(n)
