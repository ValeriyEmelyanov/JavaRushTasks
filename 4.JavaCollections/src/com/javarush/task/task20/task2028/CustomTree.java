package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("k");
    }

    @Override
    public int size() {
        if (root == null) {
            return  0;
        }

        if (root.leftChild == null && root.rightChild == null) {
            return 0;
        }

        int count = 0;
        count += countChildEntry(root);

        return count;
    }

    private int countChildEntry(Entry<String> entry) {
        int count = 0;
        if (entry.leftChild != null) {
            count ++;
            count += countChildEntry(entry.leftChild);
        }
        if (entry.rightChild != null) {
            count ++;
            count += countChildEntry(entry.rightChild);
        }
        return count;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        Entry<String> top = root;
        Entry<String> added = new Entry<>(s);
        Queue<Entry> queue = new LinkedList<>();
        do {
            if (top.leftChild != null) {
                queue.add(top.leftChild);
            } else if (top.rightChild == null) {
                added.parent = top;
                top.leftChild = added;
                top.availableToAddLeftChildren = false;
                return true;
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
            } else if (top.leftChild != null && top.leftChild.leftChild == null) {
                added.parent = top;
                top.rightChild = added;
                top.availableToAddRightChildren = false;
                return true;
            }
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
        } while (!queue.isEmpty());
        return false;

        /*
        Entry<String> entry = root;
        while (true) {
            //int cmp = entry.elementName.compareTo(s);
            int cmp = s.compareTo(entry.elementName);
            if (cmp == 0) {
                return false;
            } else if (cmp < 0) {
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = new Entry<>(s);
                    entry.availableToAddLeftChildren = false;
                    entry.leftChild.parent = entry;
                    return true;
                } else {
                    entry = entry.leftChild;
                }
            } else if (cmp > 0) {
                if (entry.availableToAddRightChildren) {
                    entry.rightChild = new Entry<>(s);
                    entry.availableToAddRightChildren = false;
                    entry.rightChild.parent = entry;
                    return true;
                } else {
                    entry = entry.rightChild;
                }
            }
        }
        */
    }

    public String getParent(String s) {
        Entry<String> top = root;
        Queue<Entry> queue = new LinkedList<>();
        queue.add(top);
        do {
            if (top.elementName.equals(s)) {
                return top.parent.elementName;
            }
            if (top.leftChild != null) {
                queue.add(top.leftChild);
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
            }
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
        } while (!queue.isEmpty());
        return null;

        /*
        Entry<String> entry = root;
        while (entry != null) {
            int cmp = s.compareTo(entry.elementName);
            if (cmp == 0) {
                return entry.parent.elementName;
            }
            if (cmp < 0) {
                entry = entry.leftChild;
            } else {
                entry = entry.rightChild;
            }
        }
        return null;
        */
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }

        String s = (String) o;

        Entry<String> top = root;
        Entry<String> deleted = null;
        Queue<Entry> queue = new LinkedList<>();
        queue.add(top);
        do {
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
            if (top.elementName.equals(s)) {
                deleted = top;
                break;
            }
            if (top.leftChild != null) {
                queue.add(top.leftChild);
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
            }
        } while (!queue.isEmpty());

        if (deleted == null) {
            return  false;
        }

        Entry<String> parent = deleted.parent;

        if (parent == null) {
            root = new Entry<>("k");
            return true;
        }

        if (parent.leftChild == deleted) {
            parent.leftChild = null;
        } else {
            parent.rightChild = null;
        }
        return true;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        @Override
        public String toString() {
            return elementName;
        }
    }
}
