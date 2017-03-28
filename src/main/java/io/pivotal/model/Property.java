package io.pivotal.model;

public class Property { 
	Float Lat;
	
	Float Lon;
	
	int id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	String Address; 
	
	Integer PxElevation;    
	
	Integer PxSlope;
	
	Integer PxAspect;   
	
	Integer Type;
	
	Integer ElevationFeet;   
	
	Integer AspectAzimuth;   
	
	Integer SlopeGradePercentage;
	
	public Property() {
		// NOOP
	}
	
	public Property(int id, String address, Integer elevationFeet) {
		super();
		this.id = id;
		this.Address = address;
		this.ElevationFeet = elevationFeet;
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
