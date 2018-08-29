package bfs;

import java.util.*;

public class BreadthFirstSearch {

    public static boolean search(Map<String, List<String>> graph, String source, String target) {
        Queue<String> searchQueue = new ArrayDeque<>(graph.get(source));
        // This list is how you keep track of which people you've searched before.
        List<String> searched = new ArrayList<>();

        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();
            // Only search this person if you haven't already searched them
            if (!searched.contains(person)) {
                if (person_is_seller(person, target)) {
                    System.out.println(person + " is a mango seller!");
                    return true;
                } else {
                    searchQueue.addAll(graph.get(person));
                    // Marks this person as searched
                    searched.add(person);
                }
            }
        }

        return false;
    }

    private static boolean person_is_seller(String name, String target) {
        return name.endsWith(target);
    }
}
