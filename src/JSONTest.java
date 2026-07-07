import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 14:00:07
 */
private static final Scanner scanner = new Scanner(System.in);
private static final Random random = new Random();
void main() {
//    gsonWriter();
    jacksonWriter();
}

private static void jacksonWriter() {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("expense_json_1.json");
    List<Expense> expenses;

    if (file.exists() && file.length() > 0) {
        try {
            expenses = mapper.readValue(file, new TypeReference<>() {});

        } catch (Exception ex) {
            System.err.println("Failed to read JSON file: " + ex.getMessage());
            throw new RuntimeException("Reading process failed: ", ex);
        }
    } else {
        expenses = new ArrayList<>();
    }

    int startIndex = expenses.size() + 1;
    for (int i = 0; i <= 100; i++) {
        expenses.add(new Expense(
                (long) i,
                "Title-" + (startIndex + i),
                "description-" + (startIndex + i),
                generateAmount(),
                generateCategory(),
                Instant.now()));
    }

    try {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, expenses);
    } catch (Exception ex) {
        System.err.println("Failed to write JSON file: " + ex.getMessage());
        throw new RuntimeException("Writing process failed: ", ex);
    }
}

private static void gsonWriter() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type listType = new TypeToken<List<Expense>>() {}.getType();
    List<Expense> expenses;

    File file = new File("expense_json_1.json");
    if (file.exists()) {
        try (FileReader reader = new FileReader(file)) {
            expenses = gson.fromJson(reader, listType);

        } catch (IOException ex) {
            System.err.println("Failed to read JSON file: " + ex.getMessage());
            throw new RuntimeException("Reading process failed: ", ex);
        }
    } else {
        expenses = null;
    }

    if (expenses == null) {
        expenses = new ArrayList<>();
    }

    int startIndex = expenses.size() + 1;
    for (int i = 0; i <= 100; i++) {
        expenses.add(new Expense(
                (long) i,
                "Title-" + (startIndex + 1),
                "description-" + (startIndex + 1),
                generateAmount(),
                generateCategory(),
                Instant.now()));
    }

    try (FileWriter writer = new FileWriter(file)) {
        gson.toJson(expenses, writer);

    } catch (IOException ex) {
        System.err.println("Failed to write JSON file: " + ex.getMessage());
        throw new RuntimeException("Writing process failed: " + ex);
    }
}

private static Long inputId() {
    println("Please enter expense ID");
    return scanner.nextLong();
}

private static String inputTitle() {
    println("Please enter expense title");
    return scanner.next();
}

private static double inputAmount() {
    println("Please enter expense amount");
    return scanner.nextDouble();
}

private static String generateTitle() {
    StringBuilder title = new StringBuilder("Title-");
    int version = 1;
    //    for (int i = 1; i <= 100; i++) {
//        title.append("title-").append(i);
//    }
//    return String.valueOf(title);
    return String.valueOf(title.append(version++));
}

private static String generateDescription() {
    StringBuilder description = new StringBuilder();
    for (int i = 1; i <= 100; i++) {
        description.append("description").append(i);
    }
    return String.valueOf(description);
}

private static double generateAmount() {
    double amount = random.nextDouble(0.0, 1000.0);
    return Math.round(amount * 100.0) / 100.0;
}

private static String generateCategory() {
    String[] categories = {"SHOPPING", "FOOD", "HOUSE_RENT", "CAR", "OTHER"};
    return categories[random.nextInt(categories.length)];
}

private record Expense(Long id, String title, String description, double amount, String category, Instant createdAt) {
}