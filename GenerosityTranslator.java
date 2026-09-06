import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GenerosityTranslator {

    private static final String API_KEY = "your_Gemini_API_key";
    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println(" Accessible Generosity Translator (Java + Google AI)");
        System.out.println("==================================================");
        System.out.print("Enter local initiative request/story:\n> ");

        String localStory = scanner.nextLine();

        if (localStory.trim().isEmpty()) {
            System.out.println("Input cannot be empty.");
            return;
        }

        System.out.println("\nAnalyzing input and generating structured campaign...");

        try {
            String markdownOutput = generateEnglishCampaign(localStory);
            System.out.println("\n--- [ Generated Campaign Markdown ] ---\n");
            System.out.println(markdownOutput);
        } catch (Exception e) {
            System.err.println("API Request failed: " + e.getMessage());
        }
    }

    private static String generateEnglishCampaign(String rawStory) throws Exception {
        String systemInstruction = "You are an assistant for non-profit initiatives. "
                + "Translate and structure the following local initiative input into a transparent, professional English fundraising report. "
                + "Output MUST be formatted in Markdown with these headings: "
                + "1. Initiative Title, 2. Urgent Need & Context, 3. Breakdown of Items Needed (in a table), 4. Verification & Transparency Note. "
                + "Input story: " + rawStory.replace("\"", "\\\"");

        String jsonPayload = "{"
                + "\"contents\": [{"
                + "  \"parts\": [{\"text\": \"" + systemInstruction + "\"}]"
                + "}]"
                + "}";

        URL url = new URL(GEMINI_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        String responseBody = response.toString();
        int textStart = responseBody.indexOf("\"text\": \"");
        if (textStart != -1) {
            textStart += 9;
            int textEnd = responseBody.indexOf("\"", textStart);
            String extracted = responseBody.substring(textStart, textEnd);
            return extracted.replace("\\n", "\n").replace("\\\"", "\"");
        }

        return "Response error or unexpected format: " + responseBody;
    }
}
