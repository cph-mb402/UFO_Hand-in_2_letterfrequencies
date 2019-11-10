package cphbusiness.ufo.letterfrequencies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        for (int i = 0; i < 5; i++) {
            String fileName = "C:\\Projects\\School\\TopUpSem2\\UFO\\letterfrequencies\\src\\main\\resources\\FoundationSeries.txt";
            Reader reader = new FileReader(fileName);
//            Map<Integer, Long> freqOld = new HashMap<>();
            Map<Character, Long> freq = new HashMap<>(255);
//            long startTimeOld = System.nanoTime();
//            tallyCharsOld(reader, freqOld);
//            long timeElapsedOld = System.nanoTime() - startTimeOld;
//            System.out.print(timeElapsedOld / 1000000 + ","); // 157,110,60,54,50
            long startTime = System.nanoTime();
            tallyChars(reader, freq);
            long timeElapsed = System.nanoTime() - startTime;
            System.out.print(timeElapsed / 1000000 + ","); // 75,53,34,37,48
//            print_tally(freq);

        }
    }

    private static void tallyCharsOld(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, freq.get(b) + 1);
            } catch (NullPointerException np) {
                freq.put(b, 1L);
            };
        }
    }

    private static void tallyChars(Reader reader, Map<Character, Long> freq) throws IOException {
        int b;
        long counter = 0;
        Character lastChar = null;
        char[] buffer = new char[4096];
        while ((b = reader.read(buffer, 0, buffer.length)) != -1) {
            Arrays.sort(buffer, 0, b);
            for (int i = 0; i < b; i++) {
                if (lastChar == null || lastChar != buffer[i]) {
                    if (lastChar != null) {
                        try {
                            freq.put(lastChar, freq.get(lastChar) + counter);
                        } catch (NullPointerException np) {
                            freq.put(lastChar, counter);
                        }
                        ;
                    }
                    counter = 0;
                    lastChar = buffer[i];
                }
                counter++;
            }
            if (lastChar != null) {
                try {
                    freq.put(lastChar, freq.get(lastChar) + counter);
                } catch (NullPointerException np) {
                    freq.put(lastChar, counter);
                }
                ;
            }
            counter = 0;
            lastChar = null;
        }

    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));
            ;
        }
    }
}
