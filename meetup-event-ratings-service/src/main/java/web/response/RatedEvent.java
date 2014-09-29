package web.response;

import meetup.client.response.Event;
import meetup.client.response.Rating;

import java.util.List;

public class RatedEvent extends Event {

    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
