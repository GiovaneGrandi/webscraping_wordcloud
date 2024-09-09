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
                "https://br.ign.com/persona-3-reload/119037/review/persona-3-reload-e-uma-aula-de-remake-e-consegue-melhorar-tudo-que-o-original-oferece-review",
                "https://br.ign.com/persona-3-reload-expansion-pass/129441/review/persona-3-reload-episodio-aigis-e-um-epilogo-com-alma-propria-e-um-gosto-agridoce",
                "https://br.ign.com/visions-of-mana/128842/review/visions-of-mana-fica-na-zona-de-conforto-mas-historia-heroica-e-jogabilidade-divertida-atraem",
                "https://br.ign.com/star-wars-outlaws-1/128767/review/star-wars-outlaws-e-uma-divertida-e-imperfeita-aventura-num-mundo-fora-da-lei-review",
                "https://br.ign.com/zenless-zone-zero/126482/review/zenless-zone-zero-e-explosao-de-emocoes-na-tela-mas-sofre-com-quebra-de-ritmo-em-recursos-essenciais",
                "https://br.ign.com/f1-24/125787/review/f1-24-traz-poucas-mudancas-em-relacao-ao-antecessor-mas-melhora-o-modo-carreira-review",
                "https://br.ign.com/mullet-madjack/123568/review/seja-rapido-e-acerte-robos-em-mullet-madjack-jogo-brasileiro-que-acerta-em-praticamente-tudo-review",
                "https://br.ign.com/dragons-dogma-2/121487/review/dragons-dogma-2-brilha-com-batalhas-grandiosas-e-otimo-sistema-de-peoes-mas-nao-atinge-nivel-dos-mel",
                "https://br.ign.com/final-fantasy-vii-rebirth/119922/review/final-fantasy-7-rebirth-estabelece-ambiente-grandioso-para-gloriosa-conclusao-da-saga-review",
                "https://br.ign.com/granblue-fantasy-relink/119288/review/granblue-fantasy-relink-compensa-historia-basica-com-combate-atrativo-review",
                "https://br.ign.com/tekken-8/119168/review/tekken-8-e-uma-obra-prima-dos-jogos-de-luta-e-o-melhor-fighting-game-3d-ja-criado",
                "https://br.ign.com/like-a-dragon-infinite-wealth/118908/review/apoiado-no-carisma-de-seus-protagonistas-like-a-dragon-infinite-wealth-tropeca-na-propria-estrutura",
                "https://br.ign.com/the-last-of-us-part-ii-remastered/118627/review/the-last-of-us-parte-2-remastered-e-uma-boa-adicao-a-franquia-mas-poderia-ser-uma-atualizacao-gratui",
                "https://br.ign.com/like-a-dragon-gaiden-the-man-who-erased-his-name/115701/review/fiel-as-proprias-raizes-like-a-dragon-gaiden-tem-no-combate-e-em-kiryu-suas-maiores-forcas-review",
                "https://br.ign.com/call-of-duty-modern-warfare-iii/115869/review/call-of-duty-modern-warfare-3-e-um-jogo-fraco-que-seria-uma-boa-dlc-review-em-progresso",
                "https://br.ign.com/alan-wake-ii/115356/review/alan-wake-2-review-em-progresso",
                "https://br.ign.com/spider-man-2/114759/review/dois-aranhas-incomodam-muito-mais-viloes-em-spider-man-2-review",
                "https://br.ign.com/horizon-chase-2/114316/review/horizon-chase-2-e-a-segunda-parte-de-uma-carta-de-amor-review",
                "https://br.ign.com/assassins-creed-mirage/114363/review/assassins-creed-mirage-e-realmente-uma-volta-as-origens-da-franquia-review",
                "https://br.ign.com/ea-sports-fc-24/113986/review/eafc-24-e-um-bom-pontape-inicial-para-uma-nova-era-da-franquia-de-futebol-mas-falta-ousadia-review",
                "https://br.ign.com/cyberpunk-2077-phantom-liberty/113871/review/cyberpunk-2077-phantom-liberty-apresenta-personagens-marcantes-e-se-beneficia-do-melhor-momento-do-j",
                "https://br.ign.com/lies-of-p/113459/review/lies-of-p-e-o-melhor-soulslike-nao-feito-pela-fromsoftware-review"
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
