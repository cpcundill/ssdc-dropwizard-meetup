package main;

import com.sun.jersey.api.client.Client;
import health.MeetupEventsServiceHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import main.config.AppConfig;
import web.EventRatingsResource;

import java.text.DateFormat;

public class MeetupEventRatingsApp extends Application<AppConfig> {

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.getObjectMapper().setDateFormat(DateFormat.getDateInstance(DateFormat.FULL));
    }

    @Override
    public void run(AppConfig config, Environment env) throws Exception {
        Client httpClient = Client.create();
        env.jersey().register(new EventRatingsResource(httpClient, config.getMeetup(), config.getEventsUrl()));
        env.healthChecks().register("Meetup Events Service", new MeetupEventsServiceHealthCheck(httpClient, config.getEventsHealthUrl()));
    }

    public static void main(String[] args) throws Exception {
        new MeetupEventRatingsApp().run(args);
    }
}
