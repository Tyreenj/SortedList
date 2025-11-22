import java.util.ArrayList;

/**
 * A custom sorted list implementation that maintains strings in lexographic order
 * Uses binary search for efficient insertion and searching
 */
public class SortedList {
    private ArrayList<String> list;

    public SortedList() {
        list = new ArrayList<>();
    }

    /**
     * Binary search to find the position where element should be inserted
     * @param element The string to search for
     * @return The index where element is found, or -(insertion point + 1) if not found
     */
    private int binarySearch(String element) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).compareTo(element);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -(left + 1);
    }

    /**
     * Add an element to the sorted list maintaining sorted order
     * @param element The string to add
     * @return A message describing the operation
     */
    public String add(String element) {
        if (element == null || element.trim().isEmpty()) {
            return "Cannot add empty string";
        }

        int position = binarySearch(element);

        if (position >= 0) {
            list.add(position, element);
            return "Added '" + element + "' at position " + position + " (duplicate)";
        } else {
            int insertionPoint = -(position + 1);
            list.add(insertionPoint, element);
            return "Added '" + element + "' at position " + insertionPoint;
        }
    }

    /**
     * Search for an element in the list
     * @param element The string to search for
     * @return A message describing the search result
     */
    public String search(String element) {
        if (element == null || element.trim().isEmpty()) {
            return "Cannot search for empty string";
        }

        int position = binarySearch(element);

        if (position >= 0) {
            return "Found '" + element + "' at position " + position;
        } else {
            int insertionPoint = -(position + 1);
            return "'" + element + "' not found. Would be inserted at position " + insertionPoint;
        }
    }

    /**
     * Get all elements as a formatted string
     * @return String representation of the list
     */
    public String displayList() {
        if (list.isEmpty()) {
            return "List is empty";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Sorted List (").append(list.size()).append(" elements):\n");
        sb.append("─────────────────────────────\n");

        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("[%2d] %s\n", i, list.get(i)));
        }

        return sb.toString();
    }

    /**
     * Get the size of the list
     * @return Number of elements in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Clear all elements from the list
     */
    public void clear() {
        list.clear();
    }

    /**
     * Check if the list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}