package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return  true;
        if (obj == null)
            return false;
        if(!(obj instanceof Solution))
            return false;
        if (!getClass().equals(obj.getClass()))
            return false;
        Solution sol = (Solution) obj;

        return (sol.first != null ? sol.first.equals(first) : first == null) &&
                (sol.last != null ? sol.last.equals(last) : last == null);
    }

    public int hashCode() {
        return 31 * (1 + (first == null ? 0 : first.hashCode())) +
                (last == null ? 0 : last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
