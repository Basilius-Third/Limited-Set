import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LimitedSetImpl<T> implements LimitedSet<T> {
    private static final int MAXIMUM_SET_SIZE = 10;
    private final Map<T, Integer> improvisedSet;

    public LimitedSetImpl() {
        improvisedSet = new HashMap<>();
    }

    @Override
    public void add(T value) {
        if (improvisedSet.get(value) == null) {
            if (improvisedSet.size() >= MAXIMUM_SET_SIZE) {
                List<T> leastViewed = findLeastViewed();
                T element = leastViewed.get(chooseRandom(leastViewed.size()));
                improvisedSet.remove(element);
            }
            improvisedSet.put(value, 0);
        }
    }

    @Override
    public boolean remove(T value) {
        return improvisedSet.remove(value, improvisedSet.get(value));
    }

    @Override
    public boolean contains(T value) {
        if (improvisedSet.containsKey(value)) {
            improvisedSet.put(value, improvisedSet.get(value) + 1);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return improvisedSet.size();
    }

    private List<T> findLeastViewed() {
        List<T> leastViewed = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Map.Entry<T, Integer> entry : improvisedSet.entrySet()) {
            Integer value = entry.getValue();
            if (value == min) {
                leastViewed.add(entry.getKey());
            } else if (value < min) {
                min = value;
                leastViewed.clear();
                leastViewed.add(entry.getKey());
            }
        }
        return leastViewed;
    }

    private int chooseRandom(int listSize) {
        Random random = new Random();
        return random.nextInt(listSize);
    }
}

