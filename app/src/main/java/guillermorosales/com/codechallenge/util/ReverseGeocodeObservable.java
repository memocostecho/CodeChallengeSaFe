package guillermorosales.com.codechallenge.util;

/**
 * Created by Guillermo Romero on 1/16/16.
 */

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;


public class ReverseGeocodeObservable implements Observable.OnSubscribe<List<Address>> {
    private final double latitude;
    private final double longitude;
    private final int maxResults;
    private final Geocoder geocoder;

    private ReverseGeocodeObservable(Context ctx, double latitude, double longitude, int maxResults) {
        this.geocoder = new Geocoder(ctx);
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxResults = maxResults;
    }

    public static Observable<List<Address>> createObservable(Context ctx, double latitude, double
            longitude, int maxResults) {
        return Observable.create(new ReverseGeocodeObservable(ctx, latitude, longitude, maxResults));
    }

    @Override
    public void call(Subscriber<? super List<Address>> subscriber) {
        if (subscriber.isUnsubscribed()) {
            try {
                subscriber.onNext(geocoder.getFromLocation(latitude, longitude, maxResults));
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
            }
        }
    }
}
