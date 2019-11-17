package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL = "https://moikrug.ru";
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";
    //private static final String REFERRER = "no-referrer-when-downgrade";
    private static final String REFERRER = "";


    @Override
    public List<Vacancy> getVacancies(String searchString) {

        Document doc;
        List<Vacancy> vacancies = new ArrayList<>();

        for (int pageNumber = 0; ; pageNumber++) {
            try {
                doc = getDocument(searchString, pageNumber);
            } catch (IOException e) {
                //return Collections.emptyList();
                break;
            }

            Elements elements = doc.getElementsByAttributeValue("class", "job");
            elements.addAll(doc.getElementsByAttributeValue("class", "job marked"));
            if (elements.isEmpty()) {
                break;
            }

            for (Element element : elements) {
                if (element == null) {
                    continue;
                }
                Vacancy vacancy = new Vacancy();

                Element title = element.getElementsByClass("title").first();
                vacancy.setTitle(title.text().trim());
                Element city = element.getElementsByClass("location").first();
                vacancy.setCity(city == null ? "" : city.text().trim());
                vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text().trim());
                vacancy.setSiteName(URL_FORMAT);
                vacancy.setUrl(URL + title.getElementsByTag("a").attr("href"));
                vacancy.setSalary(element.getElementsByClass("salary").text().trim());

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
