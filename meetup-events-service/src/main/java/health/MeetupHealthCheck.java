package health;

import com.codahale.metrics.health.HealthCheck;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import main.config.MeetupConfig;

public class MeetupHealthCheck extends HealthCheck {

    private static final String STATUS_PATH = "/status";
    private final Client httpClient;
    private final MeetupConfig meetupConfig;

    public MeetupHealthCheck(Client httpClient, MeetupConfig meetupConfig) {
        this.httpClient = httpClient;
        this.meetupConfig = meetupConfig;
    }

    @Override
    protected Result check() throws Exception {
        try {
            int status = httpClient.resource(meetupConfig.getHost() + STATUS_PATH)
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
