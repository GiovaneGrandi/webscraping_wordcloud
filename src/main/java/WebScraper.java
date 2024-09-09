import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        //Array com as URLs das páginas utilizadas
        String[] urls = {
                "https://br.ign.com/age-of-mythology-retold/128838/review/age-of-mythology-retold-e-um-remaster-que-faz-justica-ao-jogo-original-review",
                "https://br.ign.com/black-myth-wukong/128286/review/black-myth-wukong-poe-voce-na-pele-do-rei-macaco-em-epica-jornada-repleta-de-excelentes-combates-rev",
                "https://br.ign.com/shin-megami-tensei-v-vengeance/125394/review/shin-megami-tensei-v-vengeance-e-um-mergulho-em-um-mundo-sombrio-que-revitaliza-a-franquia-com-uma-n",
                "https://br.ign.com/stellar-blade/122956/review/stellar-blade-e-colcha-de-retalhos-de-diversos-elementos-que-compoem-otima-aventura-review",
                "https://br.ign.com/persona-3-reload/119037/review/persona-3-reload-e-uma-aula-de-remake-e-consegue-melhorar-tudo-que-o-original-oferece-review"
        };

        try {
            //Abrir um FileWriter para escrever no CSV
            FileWriter csvWriter = new FileWriter("texto_reviews.csv");

            //Escrever o cabeçalho no CSV
            csvWriter.append("Texto\n");

            //Loop através das URLs
            for (String url : urls) {
                //Conectar ao site e obter o HTML
                Document doc = Jsoup.connect(url).get();

                //Selecionar apenas os <p> que estão dentro de um elemento com um ID "id_text"
                Elements paragraphs = doc.select("#id_text p");

                //Loop para pegar cada parágrafo e salvar no CSV
                for (Element paragraph : paragraphs) {
                    csvWriter.append(paragraph.text()).append("\n");
                }

                System.out.println("Dados extraídos da URL: " + url);
            }

            //Fechar o arquivo CSV
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Dados salvos no CSV com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
