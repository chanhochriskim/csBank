# nov 25 - python maxxing

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
      seen = {} # hashmap<int, int> map 

      for i, num in enumerate(nums):
        diff = target - num
        if diff in seen:
          return [i, seen[diff]]

        seen[num] = i


      return []
