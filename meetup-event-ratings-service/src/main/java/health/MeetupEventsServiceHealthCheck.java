package health;

import com.codahale.metrics.health.HealthCheck;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import main.config.MeetupConfig;

public class MeetupEventsServiceHealthCheck extends HealthCheck {

    private final Client httpClient;
    private final String eventsHealthUrl;

    public MeetupEventsServiceHealthCheck(Client httpClient, String eventsHealthUrl) {
        this.httpClient = httpClient;
        this.eventsHealthUrl = eventsHealthUrl;
    }

    @Override
    protected Result check() throws Exception {
        try {
            int status = httpClient.resource(eventsHealthUrl)
                    .get(ClientResponse.class).getStatus();

            if (status == 200)
                return Result.healthy("Meetup API is alive and well");
            else
                return Result.unhealthy("Meetup API is not well, response status: " + status);

        } catch (UniformInterfaceException e) {
            return Result.unhealthy("Meetup API is not well, exception when checking status", e);
        }


    }
}
