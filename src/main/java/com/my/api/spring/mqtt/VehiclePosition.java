package com.my.api.spring.mqtt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehiclePosition {
    @JsonProperty("desi")
    private String desi;
    @JsonProperty("dir")
    private String dir;
    @JsonProperty("oper")
    private int oper;
    @JsonProperty("veh")
    private int veh;
    @JsonProperty("tst")
    private String tst;
    @JsonProperty("tsi")
    private long tsi;
    @JsonProperty("spd")
    private double spd;
    @JsonProperty("hdg")
    private int hdg;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("long")
    private double longitude;
    @JsonProperty("acc")
    private double acc;
    @JsonProperty("dl")
    private int dl;
    @JsonProperty("odo")
    private int odo;
    @JsonProperty("drst")
    private int drst;
    @JsonProperty("oday")
    private String oday;
    @JsonProperty("jrn")
    private int jrn;
    @JsonProperty("line")
    private int line;
    @JsonProperty("start")
    private String start;
    @JsonProperty("loc")
    private String loc;
    @JsonProperty("stop")
    private String stop;
    @JsonProperty("route")
    private String route;
    @JsonProperty("occu")
    private int occu;
    private String nextStop;

    public VehiclePosition() {
    }

    public VehiclePosition(String desi, String dir, int oper, int veh, String tst, long tsi, double spd, int hdg, double lat, double longitude, double acc, int dl, int odo, int drst, String oday, int jrn, int line, String start, String loc, String stop, String route, int occu) {
        this.desi = desi;
        this.dir = dir;
        this.oper = oper;
        this.veh = veh;
        this.tst = tst;
        this.tsi = tsi;
        this.spd = spd;
        this.hdg = hdg;
        this.lat = lat;
        this.longitude = longitude;
        this.acc = acc;
        this.dl = dl;
        this.odo = odo;
        this.drst = drst;
        this.oday = oday;
        this.jrn = jrn;
        this.line = line;
        this.start = start;
        this.loc = loc;
        this.stop = stop;
        this.route = route;
        this.occu = occu;
    }

    @Override
    public String toString() {
        return "VehiclePosition{" +
                "desi='" + desi + '\'' +
                ", dir='" + dir + '\'' +
                ", oper=" + oper +
                ", veh=" + veh +
                ", tst='" + tst + '\'' +
                ", tsi=" + tsi +
                ", spd=" + spd +
                ", hdg=" + hdg +
                ", lat=" + lat +
                ", longitude=" + longitude +
                ", acc=" + acc +
                ", dl=" + dl +
                ", odo=" + odo +
                ", drst=" + drst +
                ", oday='" + oday + '\'' +
                ", jrn=" + jrn +
                ", line=" + line +
                ", start='" + start + '\'' +
                ", loc='" + loc + '\'' +
                ", stop='" + stop + '\'' +
                ", route='" + route + '\'' +
                ", occu=" + occu +
                '}';
    }

    public String getDesi() {
        return desi;
    }
    public long getTsi() {
        return tsi;
    }
    public double getLat() {
        return lat;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setVeh(int veh) {
        this.veh = veh;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return oper + "-" + veh;
    }
    public String  getNextStop() {
        return nextStop;
    }
    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }
}
