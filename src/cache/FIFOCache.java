package cache;
import java.util.*;


public class FIFOCache implements CacheAlgorithm {

    private final int capacity;
    private final Queue<Integer> cache;

    private int hits;
    private int misses;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedList<>();
    }

    @Override
    public boolean access(int page) {

        if (cache.contains(page)) {
            hits++;
            return true;
        }

        misses++;

        if (cache.size() >= capacity) {
            cache.poll();
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
        return "FIFO";
    }
}
