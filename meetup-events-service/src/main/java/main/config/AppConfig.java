package main.config;

import io.dropwizard.Configuration;

public class AppConfig extends Configuration {

    private MeetupConfig meetup;

    public MeetupConfig getMeetup() {
        return meetup;
    }

    public void setMeetup(MeetupConfig meetup) {
        this.meetup = meetup;
    }
}
