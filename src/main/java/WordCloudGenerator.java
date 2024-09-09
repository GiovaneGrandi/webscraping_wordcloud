import com.opencsv.CSVReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class WordCloudGenerator {
    // Lista de palavras comuns a serem ignoradas (stop words)
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "o", "a", "os", "as", "um", "uma", "de", "da", "do", "para", "em", "por",
            "com", "e", "é", "que", "se", "no", "na", "nos", "nas", "mas", "ou", "como", "porque"
    ));

    public static void main(String[] args) throws IOException {
        // Caminho do arquivo CSV
        String csvFilePath = "texto_reviews.csv";

        // Lê o CSV e obtém o mapa de palavras e suas frequências
        Map<String, Integer> wordFrequency = readCSV(csvFilePath);

        // Gera a imagem da nuvem de palavras
        generateWordCloud(wordFrequency, "nuvem_palavras.png");
    }

    // Função que lê o CSV e retorna um mapa de palavras e suas frequências
    public static Map<String, Integer> readCSV(String filePath) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Combina todas as palavras de cada linha e conta a frequência
                for (String text : line) {
                    String[] words = text.toLowerCase().split("\\W+"); // Quebra o texto em palavras
                    for (String word : words) {
                        if (!STOP_WORDS.contains(word) && word.length() > 1) { // Filtra as stop words
                            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1); // Conta a frequência
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }

    // Função que gera a nuvem de palavras
    public static void generateWordCloud(Map<String, Integer> wordFrequency, String outputFilePath) throws IOException {
        // Tamanho da imagem
        int width = 800;
        int height = 600;

        // Criação do buffer de imagem
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        // Configurações da imagem
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height); // Preencher o fundo com branco

        // Fonte básica para as palavras
        Font baseFont = new Font("Arial", Font.BOLD, 20);

        // Ordenar as palavras por frequência (maior para menor)
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordFrequency.entrySet());
        words.sort((a, b) -> b.getValue() - a.getValue());

        // Coordenadas iniciais para posicionamento das palavras
        int x = 50;
        int y = 100;

        // Desenhar cada palavra variando o tamanho conforme a frequência
        for (Map.Entry<String, Integer> entry : words) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            // Calcular o tamanho da fonte baseado na frequência
            int fontSize = Math.max(10, frequency * 2); // Tamanho mínimo de 10 e multiplicador de 2
            Font font = baseFont.deriveFont((float) fontSize);

            graphics.setFont(font);
            graphics.setColor(new Color((int)(Math.random() * 0x1000000))); // Cor aleatória

            // Desenhar a palavra
            graphics.drawString(word, x, y);

            // Atualizar as coordenadas para a próxima palavra
            x += frequency * 10; // Move para a direita
            if (x > width - 200) { // Quebra de linha se passar da largura
                x = 50;
                y += fontSize + 30; // Move para baixo
            }

            // Evitar sair da imagem
            if (y > height - 50) {
                break; // Para de desenhar se ultrapassar a altura
            }
        }

        // Liberar o contexto gráfico
        graphics.dispose();

        // Salvar a imagem como PNG
        File outputFile = new File(outputFilePath);
        ImageIO.write(image, "png", outputFile);

        System.out.println("Nuvem de palavras gerada: " + outputFilePath);
    }
}
