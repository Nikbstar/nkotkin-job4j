package ru.nik66.sqlruparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nik66.sqlruparser.jdbc.VacancyDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlRuParser implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParser.class);

    public static void main(String[] args) {
//        try (InputStream is = SqlRuParser.class.getClassLoader().getResourceAsStream("sqlru.properties")) {
        try (InputStream is = new FileInputStream(new File(args[0]))) {
            Properties properties = new Properties();
            properties.load(is);
            String cron = properties.getProperty("cron.time");
            JobDetail job = JobBuilder.newJob(SqlRuParser.class)
                    .withIdentity("myJob")
                    .usingJobData("jdbc.driver", properties.getProperty("jdbc.driver"))
                    .usingJobData("jdbc.url", properties.getProperty("jdbc.url"))
                    .usingJobData("jdbc.username", properties.getProperty("jdbc.username"))
                    .usingJobData("jdbc.password", properties.getProperty("jdbc.password"))
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                LOG.error(e.getMessage(), e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Start schedule");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String driver = dataMap.getString("jdbc.driver");
        String url = dataMap.getString("jdbc.url");
        String name = dataMap.getString("jdbc.username");
        String pass = dataMap.getString("jdbc.password");
        try {
            Class.forName(driver);
            try (Connection connection = DriverManager.getConnection(url, name.isEmpty() ? null : name, pass.isEmpty() ? null : pass)) {
                WebParser parser = new WebParser(new VacancyDAO(connection));
                parser.addVacanciesFromWebToDB();
                parser.resultToLog();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        System.out.println("Finish schedule");
    }
}
