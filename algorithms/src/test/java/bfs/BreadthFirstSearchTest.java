package bfs;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class BreadthFirstSearchTest {

    @Test
    public void test() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("you", Arrays.asList("alice", "bob", "claire"));
        graph.put("bob", Arrays.asList("anuj", "peggy"));
        graph.put("alice", Arrays.asList("peggy"));
        graph.put("claire", Arrays.asList("thom", "jonny"));
        graph.put("anuj", Collections.emptyList());
        graph.put("peggy", Collections.emptyList());
        graph.put("thom", Collections.emptyList());
        graph.put("jonny", Collections.emptyList());

        assertTrue(BreadthFirstSearch.search(graph, "you", "m"));
    }


    @Test
    public void testGoodMorning() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("起床", Arrays.asList("洗澡", "刷牙"));
        graph.put("刷牙", Arrays.asList("吃早餐"));
        graph.put("洗澡", Collections.emptyList());
        graph.put("吃早餐", Collections.emptyList());

        // 6.3 请问下面的三个列表哪些可行、哪些不可行？
        List<String> task = Arrays.asList("起床", "洗澡", "吃早餐", "刷牙");
        assertTrue(BreadthFirstSearch.search(graph, task.get(0), task.get(1)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(1), task.get(2)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(2), task.get(3)));

        task = Arrays.asList("起床", "刷牙", "吃早餐", "洗澡");
        assertTrue(BreadthFirstSearch.search(graph, task.get(0), task.get(1)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(1), task.get(2)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(2), task.get(3)));

        task = Arrays.asList("洗澡", "起床", "刷牙", "吃早餐");
        assertTrue(BreadthFirstSearch.search(graph, task.get(0), task.get(1)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(1), task.get(2)));
        assertFalse(BreadthFirstSearch.search(graph, task.get(2), task.get(3)));

        // 可行的
        task = Arrays.asList("起床", "洗澡", "刷牙", "吃早餐");
        assertTrue(BreadthFirstSearch.search(graph, task.get(0), task.get(1)));
        assertTrue(BreadthFirstSearch.search(graph, task.get(1), task.get(2)));
        assertTrue(BreadthFirstSearch.search(graph, task.get(2), task.get(3)));

        task = Arrays.asList("起床", "刷牙", "洗澡", "吃早餐");
        assertTrue(BreadthFirstSearch.search(graph, task.get(0), task.get(1)));
        assertTrue(BreadthFirstSearch.search(graph, task.get(1), task.get(2)));
        assertTrue(BreadthFirstSearch.search(graph, task.get(2), task.get(3)));
    }
}