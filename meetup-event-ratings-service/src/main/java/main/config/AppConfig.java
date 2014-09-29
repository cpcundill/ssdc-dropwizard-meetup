package main.config;

import io.dropwizard.Configuration;

public class AppConfig extends Configuration {

    private MeetupConfig meetup;
    private String eventsUrl;
    private String eventsHealthUrl;

    public MeetupConfig getMeetup() {
        return meetup;
    }

    public void setMeetup(MeetupConfig meetup) {
        this.meetup = meetup;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getEventsHealthUrl() {
        return eventsHealthUrl;
    }

    public void setEventsHealthUrl(String eventsHealthUrl) {
        this.eventsHealthUrl = eventsHealthUrl;
    }
}
