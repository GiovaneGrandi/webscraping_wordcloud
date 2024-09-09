import nltk

nltk.download('stopwords') #Fazendo o download das stopwords da lib

import pandas as pd
from wordcloud import WordCloud
import matplotlib.pyplot as plt
from nltk.corpus import stopwords


def generate_wordcloud(text, stop_words, output_image_path):

    '''
        Função para gerar e salvar a nuvem de palavras
    '''

    wordcloud = WordCloud(width=800, height=600, background_color='white', colormap='viridis',
                          max_words=100, stopwords=stop_words, normalize_plurals=False).generate(text)

    #Exibição da nuvem de palavras
    plt.figure(figsize=(10, 8))
    plt.imshow(wordcloud, interpolation='bilinear')
    plt.axis('off')
    plt.show()

    #Salvando a nuvem de palavras em um arquivo
    wordcloud.to_file(output_image_path)
    print(f"Nuvem de palavras salva como: {output_image_path}")


def read_csv(file_path):

    '''
        Função para ler o CSV e combinar o texto
    '''

    #Percorrendo o arquivo com o pandas e adicionando o encoding 'utf-8'
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read().replace(';', '').replace(',', '')  #Remove as vírgulas e pontos e vírgulas do arquivo

    #Criando um dataframe com o conte'udo extraído do CSV
    df = pd.DataFrame(content.splitlines(), columns=['Texto'])

    #Combina todo o texto em uma única string
    text = ' '.join(df['Texto'].dropna())
    return text


if __name__ == "__main__":
    #Caminho do CSV
    csv_file = "texto_reviews.csv"

    #Lê o CSV e obtém o texto
    text_data = read_csv(csv_file)

    #Obtém as stopwords em português
    stop_words = set(stopwords.words('portuguese'))

    #Gera e salva a nuvem de palavras
    output_image_path = "nuvem_palavras.png"
    generate_wordcloud(text_data, stop_words, output_image_path)
