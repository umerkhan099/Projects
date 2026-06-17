package simulation;

import cache.FIFOCache;
import cache.LFUCache;
import cache.LRUCache;

public class CacheSimulator {

    private final int[] accessSequence;
    private int currentIndex;

    private final FIFOCache fifo;
    private final LRUCache lru;
    private final LFUCache lfu;

    public CacheSimulator(int[] accessSequence, int cacheSize) {

        this.accessSequence = accessSequence;
        this.currentIndex = 0;

        fifo = new FIFOCache(cacheSize);
        lru = new LRUCache(cacheSize);
        lfu = new LFUCache(cacheSize);
    }

    public boolean nextStep() {

        if (currentIndex >= accessSequence.length) {
            return false;
        }

        int page = accessSequence[currentIndex];

        fifo.access(page);
        lru.access(page);
        lfu.access(page);

        currentIndex++;

        return true;
    }

    public void runAll() {

        while (nextStep()) {
            // Keep running until finished
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isFinished() {
        return currentIndex >= accessSequence.length;
    }

    public int getCurrentAccess() {

        if (currentIndex == 0) {
            return -1;
        }

        return accessSequence[currentIndex - 1];
    }

    public int getTotalSteps() {
        return accessSequence.length;
    }

    public FIFOCache getFIFO() {
        return fifo;
    }

    public LRUCache getLRU() {
        return lru;
    }

    public LFUCache getLFU() {
        return lfu;
    }
}