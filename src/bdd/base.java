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
			}finally{
				if ( connexion != null ) {
	    	        try {
	    	            connexion.close();
	    	        }catch ( SQLException ignore ) {
	    	        }
	    	    }
			}
			}
	 public boolean verifemail(String email){
		 loaddatabase();
		 boolean test=false;
		 String req="SELECT email_user FROM user WHERE email_user=?";
	
	  try {
		 statement = (Statement) connexion.createStatement();
		 PreparedStatement  prepdStmt = connexion.prepareStatement(req);
		 prepdStmt.setString(1,email);
		 ResultSet res=prepdStmt.executeQuery();
		 boolean exist=res.next();
		   test=exist;
	      }catch (Exception e){
	    	  e.getMessage();
	      }
	    	    
	      
	  return test;  
	 }
	 
	 public boolean virfConex(String pass ,String email ) {
		 loaddatabase();
			boolean err=false;
			
			 try {
				 String	 query = "SELECT * FROM user Where email_user= ? && pass_user=? ";
				 PreparedStatement  prepdStmt = (PreparedStatement) connexion.prepareStatement(query);
				 			prepdStmt.setString(1, email);
				 			prepdStmt.setString(2, pass);
				 			 ResultSet res = prepdStmt.executeQuery();
				 			boolean encore = res.next();
				 			
				 			err=encore;

			    } catch (SQLException e ) {
			        
			    } 
			return err;
		}
	
}
