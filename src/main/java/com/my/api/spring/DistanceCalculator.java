package com.my.api.spring;




public class DistanceCalculator {

        // Uses the Haversine formula to calculate the distance between two points

         public double distance(double lat1, double lon1, double lat2, double lon2) {

            final int R = 6371; // Radious of the earth
            Double latDistance = toRad(lat2-lat1);
            Double lonDistance = toRad(lon2-lon1);
            Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            Double distance = R * c;

            return distance;

        }

        private static Double toRad(Double value) {
            return value * Math.PI / 180;
        }

    }

