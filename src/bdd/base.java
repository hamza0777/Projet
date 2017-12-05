package bdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Utilisateur;

public class base {
	 private Connection connexion;
	 Statement statement=null;
	 ResultSet resultat=null;
	 public void loaddatabase (){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){	
		}
		try{
			connexion=DriverManager.getConnection("jdbc:mysql://localhost/mapfe","root","");
			//System.out.println("cc");
		}catch(SQLException e){
			//System.out.println("vg");
			e.printStackTrace();
		}
	}
	 
	 public void ajoututilisateur(Utilisateur utilisateur){
			loaddatabase();
			
			
			
			String query="INSERT INTO `user` (`Id_user`, `Nom_user`, `prenom_user`, `email_user`, `pass_user`, `image`, type_user)"
					+ " VALUES (NULL, ?, ?, ?, ?, NULL,0)";
			
			try {
				PreparedStatement  prepdStmt = connexion.prepareStatement(query);
				prepdStmt.setString(1, utilisateur.getNom_user());
				prepdStmt.setString(2, utilisateur.getPrenom_user());
				prepdStmt.setString(3, utilisateur.getEmail_user());	
				prepdStmt.setString(4, utilisateur.getPass_user());
				//prepdStmt.setString(5, obj.getType_user());
						
				prepdStmt.executeUpdate(); 
				
			}catch (SQLException e){
				
				e.printStackTrace();
			}
		}
	 public int verifemail(String email){
		 int R=0;
		 //String req="SELECT email_user FROM user WHERE email_user="+email;
	
	  try {
		 statement = (Statement) connexion.createStatement();
		 ResultSet resultat= statement.executeQuery("SELECT email_user FROM user ");
         
		  while(resultat.next()){
			
			   R=R+1;
			   
			  // System.out.println(R);
		   }
	      }catch (Exception e){
	    	  
	      }
	  return R;  
	 }
	
}
