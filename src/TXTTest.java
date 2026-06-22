import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 17:39:07
 */
void main() {
    Scanner scanner = new Scanner(System.in);
    println("Please enter expense ID");
    Long id = scanner.nextLong();

    println("Please enter expense title");
    String title = scanner.next();

    println("Please enter expense amount");
    double amount = scanner.nextDouble();

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("expense_txt.txt", true));
        writer.write(String.valueOf(new Expense(id, title, amount)));
        writer.newLine();
        writer.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

record Expense(Long id, String title, double amount) {
}