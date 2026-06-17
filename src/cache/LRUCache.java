package cache;

import java.util.*;

public class LRUCache implements CacheAlgorithm {
    private final int capacity;
    private final List<Integer> cache;

    private int hits;
    private int misses;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ArrayList<>();
    }

    @Override
    public boolean access(int page) {

        if (cache.contains(page)) {

            hits++;

            cache.remove((Integer) page);
            cache.add(page);

            return true;
        }

        misses++;

        if (cache.size() >= capacity) {
            cache.remove(0);
        }

        cache.add(page);

        return false;
    }

    @Override
    public List<Integer> getCacheContents() {
        return new ArrayList<>(cache);
    }

    @Override
    public int getHits() {
        return hits;
    }

    @Override
    public int getMisses() {
        return misses;
    }

    @Override
    public String getName() {
        return "LRU";
    }
}


