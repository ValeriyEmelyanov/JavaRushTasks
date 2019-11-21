package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";
//    private static final String REFERRER = "no-referrer-when-downgrade";
    private static final String REFERRER = "";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        Document doc;
        List<Vacancy> vacancies = new ArrayList<>();

        for (int pageNumber = 0;; pageNumber++) {
            try {
                doc = getDocument(searchString, pageNumber);
            } catch (IOException e) {
                //return Collections.emptyList();
                break;
            }

            Elements elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (elements.isEmpty()) {
                break;
            }

            for (Element element : elements) {
                if (element == null) {
                    continue;
                }
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title").text());
                vacancy.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer")
                        .text().trim());
                vacancy.setSiteName(URL_FORMAT);
                String urlPage = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                vacancy.setUrl(urlPage);
                String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                vacancy.setSalary(salary.length()==0 ? "" : salary);

                vacancies.add(vacancy);
            }
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url).userAgent(USER_AGENT).referrer(REFERRER).get();
    }
}
