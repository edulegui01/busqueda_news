package com.api.busqueda.service;

import com.api.busqueda.model.News;
import com.api.busqueda.utils.DateUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    public List<News> searchNews(String query,boolean f) throws Exception {
        List<News> newsList = new ArrayList<>();
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://www.ultimahora.com/buscador?q=" + encodedQuery + "&s=0";
        Document document = Jsoup.connect(url).get();
        Elements articles = document.select("ul.SearchResultsModule-results li"); // Dependiendo de cómo esté estructurada la página web


        if (articles.isEmpty()) {
            return null; // No hay noticias
        }

        for (Element article : articles) {
            String titulo = article.select(".PagePromo-title a").text();

            // Extrae la descripción
            String resumen = article.select(".PagePromo-description").text();

            // Extrae el enlace
            String enlace = article.select(".PagePromo-title a").attr("href");

            // Extrae la imagen
            String enlaceFoto = article.select("img.Image").attr("src");

            String fecha_html = article.select(".PagePromo-date").text();
            String fecha = DateUtils.formatDate(fecha_html);



            News news = new News(fecha, enlace, enlaceFoto, titulo, resumen);

            if (f) {
                String base64Foto = getImageBase64(enlaceFoto);
                String contentTypeFoto = "image/jpeg"; // O lo que corresponda según la imagen
                news.setContenidoFoto(base64Foto);
                news.setContentTypeFoto(contentTypeFoto);
            }

            newsList.add(news);
        }

        return newsList;
    }


    private String getImageBase64(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        byte[] imageBytes = url.openStream().readAllBytes();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
