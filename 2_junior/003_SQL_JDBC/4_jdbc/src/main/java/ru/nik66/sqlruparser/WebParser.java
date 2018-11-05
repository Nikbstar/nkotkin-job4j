package ru.nik66.sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nik66.sqlruparser.jdbc.VacancyDAO;
import ru.nik66.sqlruparser.model.Vacancy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WebParser {

    private final static Logger LOG = LoggerFactory.getLogger(WebParser.class);

    private final VacancyDAO dao;

    public WebParser(VacancyDAO dao) {
        this.dao = dao;
    }

    public void resultToLog() {
        for (Vacancy vacancy : this.dao.read()) {
            LOG.info(vacancy.toString());
        }
    }

    public void addVacanciesFromWebToDB() {
        try {
            int index = 1;
            String title;
            String link;
            LocalDateTime lastUpdate = this.dao.getLastUpdate();
            LocalDateTime date = LocalDateTime.now();
            String urlHome = "http://www.sql.ru/forum/job-offers/";
            do {
                String url = String.format("%s%d", urlHome, index++);
                Document document = Jsoup.connect(url).get();
                Elements mainTable = document.select("table[class=forumtable] > tbody > tr");
                for (Element row : mainTable) {
                    title = row.select("td[class=postslisttopic] > a:nth-child(1)").text();
                    if (title.toLowerCase().contains("java") && !title.toLowerCase().contains("script")) {
                        date = parseDate(row.select("td:nth-child(6)").text());
                        if (date.isEqual(lastUpdate) || date.isBefore(lastUpdate)) {
                            break;
                        }
                        link = row.select("td[class=postslisttopic] > a").attr("href");
                        this.dao.create(new Vacancy(title, link, date));
                    }
                }
            } while (date.isAfter(lastUpdate));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private LocalDateTime parseDate(String date) {
        LocalDateTime result;
        if (date.startsWith("сегодня")) {
           date = date.substring(9);
           result = LocalDate.now().atTime(Integer.parseInt(date.split(":")[0]), Integer.parseInt(date.split(":")[1]));
        } else if (date.startsWith("вчера")) {
            date = date.substring(7);
            result = LocalDate.now().atTime(Integer.parseInt(date.split(":")[0]), Integer.parseInt(date.split(":")[1])).minusDays(1);
        } else {
            if (date.contains("май")) {
                date = date.replace('й', 'я');
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yy, HH:mm", new Locale("ru"));
            result = LocalDateTime.parse(date, dtf);
        }
        return result;
    }

}
