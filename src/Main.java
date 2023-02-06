import java.sql.*;
public class Main {

    public static void main(String[] args)throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Erreur lors du chrgement : \t"+e.getMessage());
        }

        String url = "jdbc:mysql://localhost:3306/immodb";
        Connection connexion = null;
        Statement stmt = null;
        ResultSet resultat, resultat2, resultat3, resultat4 = null;


        try {
            connexion = DriverManager.getConnection(url, "root", "");
            stmt = (Statement) connexion.createStatement();
            resultat = stmt.executeQuery("Select a_code, a_loyer, a_superf FROM appartement;");
            while(resultat.next()) {
                System.out.println("Numéro : " + resultat.getInt("a_code") + " " +
                        "Loyer : " + resultat.getInt("a_loyer") + " " +
                        "Superficie : " + resultat.getInt("a_superf"));
            }
            resultat2 = stmt.executeQuery("SELECT imm_num, count(*) as nb_appartements FROM appartement GROUP BY imm_num;");
            while(resultat.next()) {
                System.out.println("Numéro d'immeuble : " + resultat.getInt("imm_num") + " " +
                        "Nombre d'appartements : " + resultat.getInt("nb_appartements"));

            }
            resultat3=stmt.executeQuery("SELECT * FROM `appartement` WHERE a_loyer=(SELECT MAX(a_loyer) FROM appartement);");
            while(resultat.next()) {
                System.out.println("Numéro d'immeuble " + resultat.getInt("imm_num" + "" +
                        "Prix loyer" + resultat.getInt("a_loyer") + "" + "Superficie m² :" + resultat.getInt("a_superf") +
                        "Nombre d'appartements : " + resultat.getInt("nb_appartements")));
            }

            resultat4=stmt.executeQuery("SELECT * FROM appartement WHERE a_etat = \"TBE\" OR \"BON\";");
            while(resultat.next()) {
                System.out.println("Numéro d'immeuble " + resultat.getInt("imm_num" + "" +
                        "Prix loyer" + resultat.getInt("a_loyer") + "" + "Superficie m² :" + resultat.getInt("a_superf") +
                        "Nombre d'appartements : " + resultat.getInt("nb_appartements")));
            }
        }
        catch (SQLException e) {
            System.out.println("Erreur lors du chargement"+e.getMessage());
        }
        }
    }
