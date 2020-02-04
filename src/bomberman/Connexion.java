package bomberman;
import java.util.Properties;
import java.sql.*;


public class Connexion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
         Connection dbConnection = null;

            try {
              String url = "jdbc:mysql://mysql-mpreard.alwaysdata.net/mpreard_bomberman";
              Properties info = new Properties();
              info.put("user", "mpreard");
              info.put("password", "7b3097628");

              dbConnection = DriverManager.getConnection(url, info);

              if (dbConnection != null) {
                System.out.println("Successfully connected to MySQL database test");
              }

            } catch (SQLException ex) {
              System.out.println("An error occurred while connecting MySQL databse");
              ex.printStackTrace();
            }
            
            try {
                String query = "UPDATE score SET score = ? WHERE id= ?";
                PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
                preparedStmt.setInt   (1, 6072);
                preparedStmt.setInt   (2, 1);

                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                
            } catch (SQLException exp){
                  System.err.println("Got an exception! ");
                  System.err.println(exp.getMessage());
                }
            
            try {
                Statement stmn = dbConnection.createStatement();
                ResultSet rs = stmn.executeQuery("SELECT * FROM score");
                while(rs.next()) {
                    System.out.println("Id : "+rs.getInt("id")+ "\n" +"Score : "+rs.getInt("score")+ "\n" + "Nom : "+rs.getString("name") + "\n");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

}