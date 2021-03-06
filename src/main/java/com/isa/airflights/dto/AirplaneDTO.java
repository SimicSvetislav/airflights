package com.isa.airflights.dto;


public class AirplaneDTO {
	
	private Long id;
	private String fullName;
	private Long airline_id;
	private SegmentConfigDTO segmentConfig;
	
	
	
	public SegmentConfigDTO getSegmentConfig() {
		return segmentConfig;
	}

	public void setSegmentConfig(SegmentConfigDTO segmentConfig) {
		this.segmentConfig = segmentConfig;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

	public Long getAirline_id() {
		return airline_id;
	}

	public void setAirline_id(Long airline_id) {
		this.airline_id = airline_id;
	}


}