package web;

import com.codahale.metrics.annotation.Timed;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import main.config.MeetupConfig;
import meetup.client.response.Event;
import meetup.client.response.ListResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/events") @Produces(MediaType.APPLICATION_JSON)
public class EventsResource {

    private static final String EVENTS_PATH = "/2/events";
    private final Client httpClient;
    private final MeetupConfig meetupConfig;

    public EventsResource(Client httpClient, MeetupConfig meetupConfig) {
        this.httpClient = httpClient;
        this.meetupConfig = meetupConfig;
    }

    @GET @Timed
    public List<Event> listEvents(@QueryParam("groupName") String groupName) {
        // TODO: Call Meetup API!
    }
}
