import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Charline
 * Date: 06/11/13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class Gestion {
    public Gestion(){
    }

    public Connection getConnection(String base){
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+ base +"", "root", "");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
        public ArrayList getSQLTables(Connection connection)
        {
            ArrayList<String>listeTable=new ArrayList();
            try
            {

                // Créer un objet MetaData de Base de données
                DatabaseMetaData mtData;
                mtData = connection.getMetaData();
                String[] types = {"TABLE"};
                // Accéder à la liste des tables
                ResultSet res = mtData.getTables(null, null, "%", types);
                int i;
                i=1;
                while(res.next())
                {

                    String nomTable=res.getString(3);
                    // Ajouter le nom de la table dans le ArrayList
                    listeTable.add(nomTable);
                    i=i+1;

                }
                // Afficher les nom des tables sur le console
                System.out.println("Liste des tables :");
                System.out.println(listeTable);
                System.out.println("Entrez la table voulue (chiffre premiére table indice 0)");


            }catch(Exception err)
            {
                System.out.println(err);
            }
            return listeTable;
        }

    private ArrayList getTables(Connection conn, Object table) throws SQLException {

        ArrayList listCol=new ArrayList();
        try
        {
//on récupère les métadonnées à partir de la Connection
        DatabaseMetaData dmd = conn.getMetaData();
        Statement stmt=conn.createStatement();
        // Créer un objet MetaData de ResultSet
        ResultSet res=stmt.executeQuery("Select * from "+table);
        ResultSetMetaData rsMetaData=res.getMetaData();
        // Accéder à la liste des colonnes
        int nbrColonne = rsMetaData.getColumnCount();
        Scanner sc0 = new Scanner(System.in);
        boolean ok = false;
            String mode="";
            while (ok == false){
        System.out.println("Quel sera le mode de post du formulaire (GET ou POST)");
       mode = sc0.nextLine();
        if (mode.equals("")){

            System.out.println("erreur !");

        }
        else
        {
            ok=true;
        }
        }
            ArrayList test= new ArrayList();
            test.add(mode);
        for (int i = 1; i <= nbrColonne; i++)
        {
            // Retourner le nom de la colonne
            String nom=rsMetaData.getColumnName(i);
            // Retourner le type de la colonne
           // String type=rsMetaData.getColumnTypeName(i); permet de faire afficher le type des colonnes
            listCol.add(nom);// +type si on veut le faire afficher
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Quel sera le label du champ "+nom);
            String label = sc1.nextLine();
            System.out.println("Quel sera le type de composant graphique du champ "+label+" (text,liste-deroulante,checkbox ou radio");
            String composant = sc1.nextLine();

            test.add(label);
            test.add(composant);

        }

                // Ouvrir le fichier
                // (en append si tu veux écrire à la suite du fichier s'il existe)
            PrintWriter formulaire;


            formulaire =  new PrintWriter(new BufferedWriter
                    (new FileWriter("formulaire.txt")));
            for (int i = 0; i < test.size(); i++)
            {


                if ( test.get(i).equals("GET")){

                    formulaire.println ("<FORM METHOD='GET'><table><br>");


                }
                if (test.get(i).equals("POST")){
                    formulaire.write ("<FORM METHOD='POST'><table><br>");


                }
                if (test.get(i).equals("text")){
                    formulaire.write ("<TR><TD><label for="+test.get(i-1)+">"+test.get(i-1)+" :</label></TD>");
                    formulaire.write ("<TD><input type='text' id="+test.get(i)+"/></TD></TR><br>");


                }


                if (test.get(i).equals("checkbox")){
                System.out.println("combien de checkbox ?");
                Scanner sc1 = new Scanner(System.in);
                String nbr = sc1.nextLine();
                    formulaire.write ("<TR><TD><label for="+test.get(i-1)+">"+test.get(i-1)+" :</label><br>");
                for (int e = 0; e < Integer.parseInt(nbr); e++)
                {
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Label du "+(e+1)+" checkbox ?");
                    String label = sc3.nextLine();
                    formulaire.write ("<input type='checkbox' name="+test.get(i)+" value="+label+" >"+ label+"<BR>");

                }

                    formulaire.write ("</TD></TR><BR>");

            }
                if (test.get(i).equals("liste-deroulante")){
                    System.out.println("combien d'option ?");
                    Scanner sc1 = new Scanner(System.in);
                    String nbr = sc1.nextLine();
                    formulaire.write ("<TR><TD><label for="+test.get(i-1)+">"+test.get(i-1)+" :</label><br><FORM><SELECT name="+test.get(i)+" size='1'>");
                    for (int e = 0; e < Integer.parseInt(nbr); e++)
                    {
                        Scanner sc3 = new Scanner(System.in);
                        System.out.println("Label du "+(e+1)+" Option ?");
                        String label = sc3.nextLine();
                        formulaire.write ("<OPTION>"+label+"<OPTION></FORM> "
                        );

                    }

                    formulaire.write ("</TD></TR><BR>");

                }
                if (test.get(i).equals("radio")){
                System.out.println("combien de radio boutton?");
                Scanner sc1 = new Scanner(System.in);
                String nbr = sc1.nextLine();
                    formulaire.write ("<TR><TD><label for="+test.get(i-1)+">"+test.get(i-1)+" :</label><br>");
                for (int e = 0; e < Integer.parseInt(nbr); e++)
                {
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Label du "+(e+1)+" radio boutton ?");
                    String label = sc3.nextLine();
                    formulaire.write ("<input type='radio' name="+test.get(i)+" value="+label+">"+ label+"<BR>");

                }
                    formulaire.write ("</TD></TR><TR><TD><input name=\"submit\" type=\"submit\" value=\"Envoyer\" /></TD></TR> <BR>");

                }


            }

            formulaire.write ("</FORM>");
            formulaire.close();  // Fermer le fichier









        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(listCol);
        return listCol;
    }


    public void runMenu() throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sélectionnez une base de données");
        String base = sc.nextLine();
        Connection connection = this.getConnection(base);
        ArrayList listetable=this.getSQLTables(connection);
        int table = Integer.parseInt(sc.nextLine());

       this.getTables(connection, listetable.get(table));
        Desktop desk = Desktop.getDesktop();
        desk.open(new File("formulaire.txt"));
    }




}
