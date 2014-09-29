package meetup.client.response;

import java.util.List;

public class ListResponse<T> {

    private Meta meta;
    private List<T> results;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
