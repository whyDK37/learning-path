class Solution(object):
    def allPathsSourceTarget(self, graph):
        N = len(graph)

        def solve(node):
            if node == N-1: return [[N-1]]
            ans = []
            for nei in graph[node]:
                for path in solve(nei):
                    ans.append([node] + path)
            return ans

        return solve(0)

grap = [[1,2], [3], [3], []]
print(Solution().allPathsSourceTarget(grap))