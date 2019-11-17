package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" +
            this.getClass().getPackage().getName().replace(".", "/") +
            "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("krasnodar");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {

        Document doc = null;

        try {
            doc = getDocument();

            Element docTemplate = doc.getElementsByClass("template").first();
            Element template = docTemplate.clone();
            template.removeClass("template").removeAttr("style");

            doc.getElementsByAttributeValueEnding("class", "vacancy").remove();

            for (Vacancy vacancy : vacancies) {
                Element vElement = template.clone();
                vElement.getElementsByClass("city").first().text(vacancy.getCity());
                vElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                vElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = vElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                docTemplate.before(vElement.outerHtml());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return doc.html();
    }

    private void updateFile(String s) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);) {
            outputStreamWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        return Jsoup.parse(file, "UTF-8");
    }
}
