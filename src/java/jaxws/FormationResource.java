package jaxws;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import travail.*;

/**
 * REST Web Service
 *
 * @author professeur
 */
@Path("formation")
public class FormationResource
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FormationResource
     */
    public FormationResource()
    {
    }

    /**
     * Retrieves representation of an instance of jaxws.FormationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public String getXml()
    {
        ArrayList<Formation> liste = gestionSQL.ListeDesFormations();
	String chaine = "<listeformation>";
    for(int i =0; i < liste.size(); i++)
   {
	chaine += "<formation>" + liste.get(i).getLibelle() + "/" + liste.get(i).getNiveau() + "</formation>";
   }
   chaine += "</listeformation>";
   return chaine;

    }
    
    @GET // Méthode HTTP utilisée pour afficher une formation dont le libellé est passé en paramètre
    @Path("{lib}")
    @Produces("application/xml")
    public String getParamXml(@PathParam("lib") String plibForm)
    {
            ArrayList<Formation> liste = gestionSQL.ListeDesFormations();
            String chaine = "<listeformation>";
            for(int i = 0; i < liste.size(); i++)
            {
                    if (plibForm.equals(liste.get(i).getLibelle()))
                    {
                    chaine += "<formation>" + liste.get(i).getLibelle() + "/" + liste.get(i).getNiveau() + "/" + liste.get(i).getType() + "</formation>";
                    }
            }
            return (chaine + "</listeformation>");
    }
    
    @POST // Méthode HTTP utilisée pour ajouter une formation au catalogue
    //@Produces("application/xml")
    public String creForm(@QueryParam("lib") String plibForm, @QueryParam("niv") String pnivForm, @QueryParam("typ") String ptypForm, @QueryParam("des") String pdesForm, @QueryParam("dip") int pdipForm, @QueryParam("duree") int pdurForm, @QueryParam("cout") int pcouForm)
    {
            // A FAIRE : Vérification de la non existence de la formation
            // dont le libellé et le niveau sont passés en paramètres
            Formation maFormation = new Formation(plibForm, pnivForm, ptypForm, pdesForm, pdipForm, pdurForm, pcouForm);
            gestionSQL.creFormation(maFormation);
            return plibForm + "/" + pnivForm;
    }

}
