import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicBot {

    private static final Map<String, String[]> patterns = new HashMap<>();
    static {
        patterns.put("(?i)my name is (.*)", new String[]{"Hello %1, How are you today ?"});
        patterns.put("(?i)hi|hey|hello", new String[]{"Hello", "Hey there"});
        patterns.put("(?i)what is your name ?", new String[]{"I am a chatbot created by GitHub Copilot."});
        patterns.put("(?i)how are you ?", new String[]{"I'm doing good\nHow about You ?"});
        patterns.put("(?i)sorry (.*)", new String[]{"Its alright", "Its OK, never mind"});
        patterns.put("(?i)i'm (.*) doing good", new String[]{"Nice to hear that", "Alright :)"});
        patterns.put("(?i)quit", new String[]{"Bye, take care. See you soon :)", "It was nice talking to you. See you soon :)"});
        }

        private static void learnNewPattern(String input, String response) {
        patterns.put(input, new String[]{response});
        patterns.put("(?i)hi|hey|hello", new String[]{"Hello", "Hey there"});
        patterns.put("(?i)what is your name ?", new String[]{"I am a chatbot created by GitHub Copilot."});
        patterns.put("(?i)how are you ?", new String[]{"I'm doing good\nHow about You ?"});
        patterns.put("(?i)sorry (.*)", new String[]{"Its alright", "Its OK, never mind"});
        patterns.put("(?i)i'm (.*) doing good", new String[]{"Nice to hear that", "Alright :)"});
        patterns.put("(?i)quit", new String[]{"Bye, take care. See you soon :)", "It was nice talking to you. See you soon :)"});
    }

    public static void main(String[] args) {
        System.out.println("Hi, I'm a chatbot created by GitHub Copilot. Type 'quit' to exit.");
        try (Scanner scanner = new Scanner(System.in)) {

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Bye, take care. See you soon :)");
                break;
            }

            boolean matched = false;
            for (Map.Entry<String, String[]> entry : patterns.entrySet()) {
                Pattern pattern = Pattern.compile(entry.getKey());
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    String response = entry.getValue()[(int) (Math.random() * entry.getValue().length)];
                    for (int i = 1; i <= matcher.groupCount(); i++) {
                        response = response.replace("%" + i, matcher.group(i));
                    }
                    System.out.println(response);
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                System.out.println("I didn't understand that.");
            }
        }

        }
    }
}