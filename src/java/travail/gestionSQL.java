package travail;
import java.sql.*;
import java.util.ArrayList;

public class gestionSQL
{
    private static ArrayList<Formation> listeFormations = new ArrayList<Formation>();
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static String pilote = "org.gjt.mm.mysql.Driver";
    private static String url = new String("jdbc:mysql://localhost/formarmor");
    private static Formation uneFormation;
    public static ArrayList<Formation> ListeDesFormations()
    {
        try
        {
            listeFormations = new ArrayList<Formation>();
            Class.forName(pilote);           			            
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            rs = stmt.executeQuery("select * from formation");
            while (rs.next())
            {
                uneFormation = new Formation(rs.getString("libelle"), rs.getString("niveau"), rs.getString("type_form"), rs.getString("description"), rs.getInt("diplomante"), rs.getInt("duree"), rs.getInt("coutrevient"));
                listeFormations.add(uneFormation);
            }
            rs.close();
            stmt.close();
            conn.close();
            return listeFormations;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void creFormation(Formation f)
    {
        try
        {
            Class.forName(pilote);           			            
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            String insertion = "('" + f.getLibelle() + "', '" + f.getNiveau() + "', '" + f.getType() + "', '" + f.getDescription() + "', " + f.getDiplomante() + ", " + f.getDuree() + ", " + f.getCoutRevient() +")";
            System.out.println(insertion);
            int nb = stmt.executeUpdate("insert into formation values" + insertion);
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
