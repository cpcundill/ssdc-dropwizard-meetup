package main;

import com.sun.jersey.api.client.Client;
import health.MeetupHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import main.config.AppConfig;
import web.EventsResource;

import java.text.DateFormat;

public class MeetupEventsApp extends Application<AppConfig> {

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        //bootstrap.getObjectMapper().setDateFormat(DateFormat.getDateInstance(DateFormat.FULL));
    }

    @Override
    public void run(AppConfig config, Environment env) throws Exception {
        Client httpClient = Client.create();
        env.jersey().register(new EventsResource(httpClient, config.getMeetup()));
        env.healthChecks().register("meetup.com", new MeetupHealthCheck(httpClient, config.getMeetup()));
    }

    public static void main(String[] args) throws Exception {
        new MeetupEventsApp().run(args);
    }
}
