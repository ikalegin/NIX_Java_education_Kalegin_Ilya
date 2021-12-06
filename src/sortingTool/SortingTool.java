package sortingTool;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class SortingTool {
    private final Scanner scanner;
    private final PrintWriter outputStream;

    public SortingTool(InputStream inputStream, PrintWriter outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public void sortLongs(boolean isSortingTypeNatural) {
        List<Integer> integerList = new ArrayList<>();
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (!isInteger(next)) {
                System.out.printf("%s is not a long. It will be skipped", next);
                continue;
            }
            integerList.add(Integer.parseInt(next));
        }
        outputStream.println("Total numbers: " + integerList.size());
        if (isSortingTypeNatural) {
            outputStream.print("Sorted data: ");
            integerList.sort(Integer::compare);
            integerList.forEach(integer -> outputStream.print(integer + " "));
        } else {
            sortByCount(integerList);
        }
    }

    private <T extends Comparable> void sortByCount(List<T> list) {
        SortedMap<Comparable, Integer> map = new TreeMap<>();
        list.forEach(l -> {
            if (map.containsKey(l)) {
                map.put(l, map.get(l) + 1);
            } else {
                map.put(l, 1);
            }
        });
        List<Storage<Comparable>> storageList = new ArrayList<>();

        Integer totalCount = 0;
        for (Map.Entry<Comparable, Integer> entry : map.entrySet()) {
            Storage<Comparable> storage = new Storage<>(entry.getValue(), entry.getKey());
            storageList.add(storage);
            Integer value = entry.getValue();
            totalCount += value;
        }
        storageList.sort(Storage::compareTo);
        Integer finalTotalCount = totalCount;
        storageList.forEach((item) -> {
            outputStream.printf("%s: %s time(s), %s%%\n", item.obj, item.count, (item.count * 100) / finalTotalCount);
        });
    }

    public void sortWords(boolean isSortingTypeNatural) {
        List<String> stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(scanner.next());
        }
        outputStream.println("Total words: " + stringList.size());
        if (isSortingTypeNatural) {
            sortStrings(stringList);
        } else {
            sortByCount(stringList);
        }
    }

    public void sortLines(boolean isSortingTypeNatural) {
        List<String> stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(scanner.nextLine());
        }
        outputStream.println("Total lines: " + stringList.size());
        if (isSortingTypeNatural) {
            sortStrings(stringList);

        } else {
            sortByCount(stringList);
        }
    }

    private void sortStrings(List<String> stringList) {
        stringList.sort((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareToIgnoreCase(s2);
            }
            return Integer.compare(s1.length(), s2.length());
        });
        outputStream.println("Sorted data: ");
        stringList.forEach(outputStream::println);
    }

    private boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static class Storage<T extends Comparable> implements Comparable<Storage<T>> {
        private final Integer count;
        private final T obj;

        public Storage(int count, T obj) {
            this.count = count;
            this.obj = obj;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Storage<?> storage = (Storage<?>) o;
            return obj.equals(storage.obj);
        }

        @Override
        public int hashCode() {
            return Objects.hash(obj);
        }

        @Override
        public int compareTo(Storage<T> o) {
            if (count.compareTo(o.count) == 0) {
                return obj.compareTo(o.obj);
            }
            return count.compareTo(o.count);
        }
    }
}
