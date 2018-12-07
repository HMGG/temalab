package demo2api;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class Location {
	
	public long id;
	
	public float latitude;
	
	public float longitude;
	
	public Location(float x, float y) {
		super();
		this.latitude = x;
		this.longitude = y;
	}
	
	public Geometry toGeometry() {
		return new GeometryFactory().createPoint(new Coordinate(latitude,longitude)).buffer(1);
	}
}
