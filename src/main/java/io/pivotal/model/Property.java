package io.pivotal.model;

public class Property {

    int id; //key class
    Integer ElevationFeet;
    String Address;
    Float Lat;
    Float Lon;
    Integer PxElevation;
    Integer PxSlope;
    Integer PxAspect;
    Integer Type;
    Integer AspectAzimuth;
    Integer SlopeGradePercentage;

    public Property(int id, Integer elevationFeet, String address) {
        this.id = id;
        ElevationFeet = elevationFeet;
        Address = address;
    }

    public Property() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return id == property.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getLat() {
        return Lat;
    }

    public void setLat(Float lat) {
        Lat = lat;
    }

    public Float getLon() {
        return Lon;
    }

    public void setLon(Float lon) {
        Lon = lon;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getPxElevation() {
        return PxElevation;
    }

    public void setPxElevation(Integer pxElevation) {
        PxElevation = pxElevation;
    }

    public Integer getPxSlope() {
        return PxSlope;
    }

    public void setPxSlope(Integer pxSlope) {
        PxSlope = pxSlope;
    }

    public Integer getPxAspect() {
        return PxAspect;
    }

    public void setPxAspect(Integer pxAspect) {
        PxAspect = pxAspect;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getElevationFeet() {
        return ElevationFeet;
    }

    public void setElevationFeet(Integer elevationFeet) {
        ElevationFeet = elevationFeet;
    }

    public Integer getAspectAzimuth() {
        return AspectAzimuth;
    }

    public void setAspectAzimuth(Integer aspectAzimuth) {
        AspectAzimuth = aspectAzimuth;
    }

    public Integer getSlopeGradePercentage() {
        return SlopeGradePercentage;
    }

    public void setSlopeGradePercentage(Integer slopeGradePercentage) {
        SlopeGradePercentage = slopeGradePercentage;
    }

}
