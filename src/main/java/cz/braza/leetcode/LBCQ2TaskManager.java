package cz.braza.leetcode;

import java.util.*;

/*
Q2. Design Task Manager
Medium
5 pt.
There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.

Implement the TaskManager class:

TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.

void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.

void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.

void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.

int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.

Note that a user may be assigned multiple tasks.



Example 1:

Input:
["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
[[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]

Output:
[null, null, null, 3, null, null, 5]

Explanation

TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.
taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
taskManager.edit(102, 8); // Updates priority of task 102 to 8.
taskManager.execTop(); // return 3. Executes task 103 for User 3.
taskManager.rmv(101); // Removes task 101 from the system.
taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
taskManager.execTop(); // return 5. Executes task 105 for User 5.


Constraints:

1 <= tasks.length <= 10^5
0 <= userId <= 10^5
0 <= taskId <= 10^5
0 <= priority <= 10^9
0 <= newPriority <= 10^9
At most 2 * 10^5 calls will be made in total to add, edit, rmv, and execTop methods.
 */
class ListComparator implements Comparator<List<Integer>>{

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(List<Integer> l1, List<Integer> l2) {
        if (l1.get(1).equals(l2.get(1)))
            return l2.get(2) - l1.get(2);
        else return l2.get(1) - l1.get(1);
    }
}

/*
Failed a test-case 584/662 because the comparator does not work properly:
returns user 31, task 38 with priority 989, while there waits
task for user 35, id 43 and priority 989, that should go sooner (higher id)
Tried comparator above (class), but does the same error.
Did not finish in time, therefore.
PROBLEM: comparing Integers by == instead of equals()
Anyway, got TLE afterwards at testcase 658/662
 */
class TaskManager {
    // user, taskId, priority
    PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(2).equals(b.get(2)) ? b.get(1) - a.get(1) : b.get(2) - a.get(2));
    Map<Integer, List<Integer>> map = new HashMap<>();

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> t: tasks) {
            pq.offer(t);
            map.put(t.get(1), t);
        }
        System.out.println("TaskManager initialized with " + pq.size() + " tasks, highest priority " + pq.peek().get(2));
    }

    public void add(int userId, int taskId, int priority) {
        List<Integer> list = Arrays.asList(userId, taskId, priority);
        pq.offer(list);
        map.put(taskId, list);
    }

    public void edit(int taskId, int newPriority) {
        List<Integer> list = map.get(taskId);
        pq.remove(list);
        list.set(2, newPriority);
        pq.offer(list);
    }

    public void rmv(int taskId) {
        pq.remove(map.get(taskId));
        map.remove(taskId);
    }

    public int execTop() {
        if (pq.isEmpty()) return -1;
        List<Integer> task = pq.poll();
        map.remove(task.get(1));
        return task.get(0);
    }
}


public class LBCQ2TaskManager {
    public static void main(String[] args) {
        List<List<Integer>> init = new ArrayList<>();
        init.add(new ArrayList<>(Arrays.asList(35,49,316)));
        init.add(new ArrayList<>(Arrays.asList(35,43,989)));
        init.add(new ArrayList<>(Arrays.asList(31,38,989)));
        init.add(new ArrayList<>(Arrays.asList(24,35,1354)));

        TaskManager tm = new TaskManager(init);
        tm.add(4, 104, 5);
        tm.edit(35, 304);
        System.out.println(tm.execTop());
        tm.add(5, 105, 15);
        System.out.println(tm.execTop());
    }
}
/*
[[
[[21,51,329],[41,70,576],[43,47,765],
[35,49,316],[9,27,316],[37,76,156],[7,26,383],[41,52,458],[34,99,351],[33,57,657],[5,83,545],[46,54,252],[41,65,360],[19,36,937],[46,75,159],
[31,38,989],[16,1,801],[16,62,306],[26,41,692],[14,79,233],[35,48,284],[44,30,927],[0,13,886],[31,28,664],[46,24,847],[50,16,295],[23,74,479],[46,71,316],[44,60,827],[40,66,594],[26,100,86],
[35,43,989],[36,88,328],[43,55,333],[25,0,431],[9,8,83],[7,96,68],[7,12,137],[44,21,826],[42,20,940],[30,84,196],[8,11,819],[31,95,331],[43,82,508],[8,14,285],[11,72,710],[1,18,453],[22,59,174],[33,23,123],[24,22,18],[50,68,882],[17,98,701],[17,58,115],[6,97,379],[6,67,12],[31,2,249],[50,53,76],[0,93,562],[43,78,914],[2,17,390],[24,35,354],[12,15,364],[33,42,672],[44,81,489],[4,45,300],[16,37,435],[37,85,350],[14,31,783],[13,50,726],[43,80,597],[40,89,151],[17,63,215],[23,86,404],[38,69,660],[28,87,17],[15,32,656],[21,34,447],[42,64,719],[4,40,585],[50,4,837],[29,6,437],[2,25,856],[26,5,153]]
]
,[35,304],[1,9,473],[100,928],[]]
 */