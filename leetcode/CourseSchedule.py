# neetcode solution, problem written on note, ~45min

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        preMap = { i: [] for i in range(numCourses)} # for each course, it starts with empty preReq.

        for crs, pre in prerequisites:
            preMap[crs].append(pre)
        
        # visitset = all courses along curr DFS path. 
        visitSet = set()
        def dfs(crs):
            if crs in visitSet: # loop detected
                return False
            if preMap[crs] == []: # end of path --> good
                return True
            visitSet.add(crs) # add this node to visitSet. 

            for pre in preMap[crs]:
                if not dfs(pre): return False
            
            visitSet.remove(crs)
            preMap[crs] = []
            return True
        
        for crs in range(numCourses):
            if not dfs(crs): return False

        return True


# review sesh (on my own)
--> still not really used to python. gotta practice more. dfs part need a bit more to review for understanding.

# 3rd review sesh (cafe with jay)
