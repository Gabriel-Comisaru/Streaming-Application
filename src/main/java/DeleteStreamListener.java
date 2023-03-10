import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteStreamListener implements EventListener {
    private final File log;

    public DeleteStreamListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, Integer streamId) {
        try (FileWriter fw = new FileWriter(log, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            out.println("(" + dtf.format(now) + ") : Someone has performed " + eventType + " operation for the following stream: " + streamId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
