package hairratic.legislatives.circonscriptions.data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;

import java.util.ArrayList;
import java.util.List;

public record CirconscriptionArea(Geometry area, CirconscriptionProperties properties) {
    public static List<CirconscriptionArea> listFromJson(String jsonStr) throws ParseException {
        List<CirconscriptionArea> circonscriptionAreaList = new ArrayList<CirconscriptionArea>();

        JSONObject json = new JSONObject(jsonStr);
        JSONArray features = json.getJSONArray("features");

        for (int i = 0; i < features.length(); i++) {
            JSONObject currentFeature = (JSONObject) features.get(i);

            GeoJsonReader reader = new GeoJsonReader();
            Geometry geometry = reader.read(currentFeature.get("geometry").toString());

            circonscriptionAreaList.add(
                    new CirconscriptionArea(geometry,
                            CirconscriptionProperties.fromJson(currentFeature.get("properties").toString())
                    ));

        }

        return circonscriptionAreaList;
    }

    public boolean contains(float X, float Y){
        Geometry point = this.area.getFactory().createPoint(new Coordinate(X, Y));

        return area.contains(point);
    }
}
