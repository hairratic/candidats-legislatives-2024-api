package hairratic.legislatives.circonscriptions.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public record CirconscriptionProperties(String codeCirconscription,
                                        String nomDepartement,
                                        String nomCirconscription,
                                        String codeDepartement) {
    public static CirconscriptionProperties fromJson(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);

            return new CirconscriptionProperties(
                    node.get("codeCirconscription").asText(),
                    node.get("nomDepartement").asText(),
                    node.get("nomCirconscription").asText(),
                    node.get("codeDepartement").asText()
            );
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }
}
