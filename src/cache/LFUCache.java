package cache;

import java.util.*;

public class LFUCache implements CacheAlgorithm {

    private final int capacity;

    private final List<Integer> cache;
    private final Map<Integer, Integer> frequency;

    private int hits;
    private int misses;

    public LFUCache(int capacity) {

        this.capacity = capacity;

        cache = new ArrayList<>();
        frequency = new HashMap<>();
    }

    @Override
    public boolean access(int page) {

        if (cache.contains(page)) {

            hits++;

            frequency.put(page,
                    frequency.get(page) + 1);

            return true;
        }

        misses++;

        if (cache.size() >= capacity) {

            int leastUsed = cache.get(0);

            for (int p : cache) {

                if (frequency.get(p)
                        < frequency.get(leastUsed)) {

                    leastUsed = p;
                }
            }

            cache.remove((Integer) leastUsed);
            frequency.remove(leastUsed);
        }

        cache.add(page);
        frequency.put(page, 1);

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
        return "LFU";
    }
}