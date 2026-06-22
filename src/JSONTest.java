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
void main() {
//    gsonWriter();
    jacksonWriter();

}

private static void jacksonWriter() {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("expense_json.json");
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

    expenses.add(new Expense(inputId(), inputTitle(), inputAmount()));

    try {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, expenses);
    } catch (Exception ex) {
        System.err.println("Failed to write JSON file: " + ex.getMessage());
        throw new RuntimeException("Writing process failed: ", ex);
    }
}

private static void gsonWriter() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type listType = new TypeToken<List<Expense>>(){}.getType();
    List<Expense> expenses;

    File file = new File("expense_gson.json");
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

    expenses.add(new Expense(inputId(), inputTitle(), inputAmount()));

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

private record Expense(Long id, String title, double amount) {
}