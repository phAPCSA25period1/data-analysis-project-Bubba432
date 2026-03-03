import java.io.File;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * TODO:
 *  - Update the path to your dataset file
 *  - Read the CSV file using Scanner
 *  - Parse each row and extract the correct columns
 *  - Construct Data objects from each row
 *  - Store them in an array
 *  - Write methods to analyze the dataset (min, max, average, filters, etc.)
 *  - Print insights and answer your guiding question
 *  - Add Javadoc comments for any methods you create
 */
public class App {

    public static void main(String[] args) {

            // path to our CSV dataset (relative to project root)
        File file = new File("../data/pokemon.csv");

        // we will store every Pokémon in an ArrayList
        java.util.ArrayList<Pokemon> list = new java.util.ArrayList<>();

        // read the file and build objects
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            if (scanner.hasNextLine()) {
                // skip header row
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                // assuming columns: Name,Type,HP,Attack
                String name = parts[0].trim();
                String type = parts[1].trim();
                int hp = Integer.parseInt(parts[2].trim());
                int attack = Integer.parseInt(parts[3].trim());
                Pokemon p = new Pokemon(name, type, hp, attack);
                list.add(p);
            }
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Dataset file not found: " + file.getPath());
            return;
        }

        // run some analysis
        int maxHp = findMaxHp(list);
        int minHp = findMinHp(list);
        double avgHp = computeAverageHp(list);
        String typeWithHighestAvg = findTypeWithHighestAverageHp(list);

        // print insights and answer guiding question
        System.out.println("Total rows loaded: " + list.size());
        System.out.println("Highest HP in dataset: " + maxHp);
        System.out.println("Lowest HP in dataset: " + minHp);
        System.out.printf("Average HP (all Pokémon): %.2f\n", avgHp);
        System.out.println("Type with highest average HP: " + typeWithHighestAvg);
        System.out.println();
        System.out.println("Guiding question answer:");
        System.out.println("The Pokémon type with the highest average HP is " + typeWithHighestAvg + ".");
    }

    /**
     * Finds the maximum HP value among all Pokémon in the list.
     *
     * @param list ArrayList of Pokémon objects
     * @return largest HP value found (or Integer.MIN_VALUE if list empty)
     */
    public static int findMaxHp(java.util.ArrayList<Pokemon> list) {
        int max = Integer.MIN_VALUE;
        for (Pokemon p : list) {
            if (p.getHp() > max) {
                max = p.getHp();
            }
        }
        return max;
    }

    /**
     * Finds the minimum HP value among all Pokémon in the list.
     *
     * @param list ArrayList of Pokémon objects
     * @return smallest HP value (or Integer.MAX_VALUE if list empty)
     */
    public static int findMinHp(java.util.ArrayList<Pokemon> list) {
        int min = Integer.MAX_VALUE;
        for (Pokemon p : list) {
            if (p.getHp() < min) {
                min = p.getHp();
            }
        }
        return min;
    }

    /**
     * Computes the average HP across all Pokémon.
     *
     * @param list ArrayList of Pokémon objects
     * @return average HP (0.0 if list empty)
     */
    public static double computeAverageHp(java.util.ArrayList<Pokemon> list) {
        if (list.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Pokemon p : list) {
            sum += p.getHp();
        }
        return sum / list.size();
    }

    /**
     * Determines which Pokémon type has the highest average HP.
     *
     * @param list ArrayList of Pokémon objects
     * @return the type (String) with highest average HP, or "" if list empty
     */
    public static String findTypeWithHighestAverageHp(java.util.ArrayList<Pokemon> list) {
        if (list.isEmpty()) {
            return "";
        }
        java.util.Map<String, java.util.List<Pokemon>> buckets = new java.util.HashMap<>();
        for (Pokemon p : list) {
            buckets.computeIfAbsent(p.getType(), k -> new java.util.ArrayList<>()).add(p);
        }
        String bestType = "";
        double bestAvg = Double.NEGATIVE_INFINITY;
        for (java.util.Map.Entry<String, java.util.List<Pokemon>> e : buckets.entrySet()) {
            double sum = 0;
            for (Pokemon p : e.getValue()) {
                sum += p.getHp();
            }
            double avg = sum / e.getValue().size();
            if (avg > bestAvg) {
                bestAvg = avg;
                bestType = e.getKey();
            }
        }
        return bestType;
    }


        // OPTIONAL TODO:
    // Add user interaction:
    // Ask the user what kind of analysis they want to see

}