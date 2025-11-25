# nov 25

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        seen = set()
        
        for i, num in enumerate(nums):
            if num in seen:
                return True
            else:
                seen.add(num)

        return False
