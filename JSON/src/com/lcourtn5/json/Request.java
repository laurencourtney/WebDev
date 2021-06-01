package com.lcourtn5.json;

public class Request {
	private String hike;
	private String date;
	private String duration;
	private String party;
	
	public Request() {
		super();
	}

	public String getHike() {
		return hike;
	}

	public void setHike(String hike) {
		this.hike = hike;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((hike == null) ? 0 : hike.hashCode());
		result = prime * result + ((party == null) ? 0 : party.hashCode());
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
		Request other = (Request) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (hike == null) {
			if (other.hike != null)
				return false;
		} else if (!hike.equals(other.hike))
			return false;
		if (party == null) {
			if (other.party != null)
				return false;
		} else if (!party.equals(other.party))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [hike=" + hike + ", date=" + date + ", duration=" + duration + ", party=" + party + "]";
	}

}