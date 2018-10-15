from collections import deque


def person_is_seller(name):
    return name[-1] == 'm'


graph = {}
graph["you"] = ["alice", "bob", "claire"]
graph["bob"] = ["anuj", "peggy"]
graph["alice"] = ["peggy"]
graph["claire"] = ["thom", "jonny"]
graph["anuj"] = []
graph["peggy"] = []
graph["thom"] = []
graph["jonny"] = []


def search(name):
    search_queue = deque()
    search_queue += graph[name]
    # This array is how you keep track of which people you've searched before.
    searched = []

    def solve(man):
        if person_is_seller(man):
            return True
        else:
            persions = graph[man]

    while search_queue:
        person = search_queue.popleft()
        if person not in searched:
            solve(person)
    return solve(name)


print(search("you"))
