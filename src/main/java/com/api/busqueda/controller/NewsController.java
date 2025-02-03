package com.api.busqueda.controller;

import com.api.busqueda.model.ErrorResponse;
import com.api.busqueda.service.NewsService;
import com.api.busqueda.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/consulta")
    public ResponseEntity<?> getNews(@RequestParam(value = "q", required = false) String query, @RequestParam(value = "f", defaultValue = "false") boolean f) {
        if (query == null || query.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("g268", "Parámetros inválidos"));
        }

        try {
            List<News> newsList = newsService.searchNews(query,f);
            if (newsList == null || newsList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("g267", "No se encuentran noticias para el texto: " + query));
            }
            return ResponseEntity.ok(newsList);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("g100", "Error interno del servidor"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
