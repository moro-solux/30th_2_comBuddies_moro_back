package com.example.moro.app.map.util;

import com.example.moro.app.map.dto.BoundingBox;

public class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static BoundingBox calculateBoundingBox(double lat, double lng, double radiusKm)
    {
        double latRadian = Math.toRadians(lat);
        double lngRadian = Math.toRadians(lng);

        double latDelta = Math.toDegrees(radiusKm / EARTH_RADIUS_KM);
        double lngDelta = Math.toDegrees(
                radiusKm / (EARTH_RADIUS_KM * Math.cos(latRadian))
        );

        return new BoundingBox(
                lat - latDelta,
                lat + latDelta,
                lng - lngDelta,
                lng + lngDelta
        );
    }

}
