package it.unibo.model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.swing.JList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardImplTest {

    private ScoreboardImpl scoreboard;
    private Path tempDirPath;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) {
        scoreboard = new ScoreboardImpl();
        tempDirPath = tempDir;
    }

    @Test
    public void testOpen() throws IOException {
        // Creazione del file temporaneo JSON con dati di esempio
        Path jsonFile = createTempJsonFile("[{\"name\":\"Alice\",\"points\":100}]");

        // Imposta manualmente il percorso del file nel test
        scoreboard.setScoreboardFileForTest(jsonFile.toString());

        JSONArray result = scoreboard.open();
        assertNotNull(result);
        assertEquals(1, result.length());
        assertEquals("Alice", result.getJSONObject(0).getString("name"));
        assertEquals(100, result.getJSONObject(0).getInt("points"));
    }

    @Test
    public void testTop10() throws IOException {
        // Creazione del file temporaneo JSON con dati di esempio
        Path jsonFile = createTempJsonFile("[{\"name\":\"Alice\",\"points\":100},{\"name\":\"Bob\",\"points\":90}]");

        // Imposta manualmente il percorso del file nel test
        scoreboard.setScoreboardFileForTest(jsonFile.toString());

        JList<String> resultList = scoreboard.top10();
        assertNotNull(resultList);
        assertEquals(2, resultList.getModel().getSize());
        assertEquals("1° - Name: Alice, Points: 100", resultList.getModel().getElementAt(0));
        assertEquals("2° - Name: Bob, Points: 90", resultList.getModel().getElementAt(1));
    }

    @Test
    public void testAdd() throws IOException {
        // Creazione del file temporaneo JSON con dati iniziali
        Path jsonFile = createTempJsonFile("[{\"name\":\"Alice\",\"points\":100},{\"name\":\"Bob\",\"points\":90}]");

        // Imposta manualmente il percorso del file nel test
        scoreboard.setScoreboardFileForTest(jsonFile.toString());

        scoreboard.add("Charlie", 95);

        // Verifica che il nuovo punteggio sia stato aggiunto correttamente
        String content = Files.readString(jsonFile);
        JSONArray result = new JSONArray(content);
        assertEquals(3, result.length());
        assertEquals("Alice", result.getJSONObject(0).getString("name"));
        assertEquals(100, result.getJSONObject(0).getInt("points"));
        assertEquals("Charlie", result.getJSONObject(1).getString("name"));
        assertEquals(95, result.getJSONObject(1).getInt("points"));
        assertEquals("Bob", result.getJSONObject(2).getString("name"));
        assertEquals(90, result.getJSONObject(2).getInt("points"));
    }

    // Metodo per creare un file temporaneo JSON con contenuto specificato
    private Path createTempJsonFile(String jsonContent) throws IOException {
        Path jsonFile = tempDirPath.resolve("Scoreboard.json");
        try (BufferedWriter writer = Files.newBufferedWriter(jsonFile)) {
            writer.write(jsonContent);
        }
        return jsonFile;
    }
}
