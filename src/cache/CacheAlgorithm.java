package cache;

import java.util.List;

public interface CacheAlgorithm {

    boolean access(int page);

    List<Integer> getCacheContents();

    int getHits();

    int getMisses();

    String getName();
}

