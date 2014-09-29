package web;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Function;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import main.config.MeetupConfig;
import meetup.client.response.Event;
import meetup.client.response.ListResponse;
import meetup.client.response.Rating;
import web.response.RatedEvent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.FluentIterable.from;

@Path("/events") @Produces(MediaType.APPLICATION_JSON)
public class EventRatingsResource {

    private static final String EVENT_RATINGS_URL = "/2/event_ratings";
    private final Client httpClient;
    private final MeetupConfig meetupConfig;
    private final String eventsUrl;

    public EventRatingsResource(Client httpClient, MeetupConfig meetupConfig, String eventsUrl) {
        this.httpClient = httpClient;
        this.meetupConfig = meetupConfig;
        this.eventsUrl = eventsUrl;
    }

    @GET @Timed
    public List<RatedEvent> listEvents(@QueryParam("groupName") final String groupName) {
        List<RatedEvent> events = httpClient.resource(eventsUrl)
                .queryParam("groupName", groupName)
                .get(new GenericType<List<RatedEvent>>() {});

        return from(events).transform(new Function<RatedEvent, RatedEvent>(){
            public RatedEvent apply(RatedEvent input) {
                List<Rating> ratings = httpClient.resource(meetupConfig.getHost() + EVENT_RATINGS_URL)
                        .queryParam("key", meetupConfig.getKey())
                        .queryParam("event_id", input.getId())
                        .get(new GenericType<ListResponse<Rating>>() {})
                        .getResults();
                input.setRatings(ratings);
                return input;
            }
        }).toList();
    }
}
