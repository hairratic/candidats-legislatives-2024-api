package hairratic.legislatives.candidatures.data;

public record Candidat(int id,
                       String departement,
                       String circonscription,
                       int numPanneau,
                       String nomCandidat,
                       String prenomCandidat,
                       String civiliteCandidat,
                       String codeNuance,
                       String libelleNuance) {
}
