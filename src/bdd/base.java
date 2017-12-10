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
					+ " VALUES (NULL, ?, ?, ?, ?,?,?)";
			
			try {
				PreparedStatement  prepdStmt = connexion.prepareStatement(query);
				prepdStmt.setString(1, utilisateur.getNom_user());
				prepdStmt.setString(2, utilisateur.getPrenom_user());
				prepdStmt.setString(3, utilisateur.getEmail_user());	
				prepdStmt.setString(4, utilisateur.getPass_user());
				prepdStmt.setString(5, utilisateur.getImage_user());
				prepdStmt.setInt(6, utilisateur.getType_user());
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
	 public Utilisateur recherUser(String email){
		 loaddatabase();
		 Utilisateur s= new Utilisateur();
			String	 query = "SELECT *  FROM user Where email_user= ?  ";
			
			try{
			
			 PreparedStatement  prepdStmt = (PreparedStatement) connexion.prepareStatement(query);
			 	prepdStmt.setString(1, email);
			 	ResultSet res = prepdStmt.executeQuery();
			 	while(res.next()){
			 		s.setId_user(res.getInt("Id_user"));
			 		s.setNom_user(res.getString("Nom_user"));
			 		s.setPrenom_user(res.getString("prenom_user"));
			 		s.setEmail_user(res.getString("email_user"));
			 		s.setPass_user(res.getString("pass_user"));
			 		s.setImage_user(res.getString("image"));
			 		s.setType_user(res.getInt("type_user"));
			 	}
			 	
			 			 
			}catch (SQLException e) {
			}
			return s;
		}
	 public void modifier(Utilisateur users){
		 loaddatabase();
		 
		 String query = "UPDATE user SET Nom_user='?' ,prenom_user='?' ,email_user='?' ,pass_user='?' WHERE id='?'";
				 try {
					 PreparedStatement  prepdStmt = connexion.prepareStatement(query);
						prepdStmt.setString(1, users.getNom_user());
						prepdStmt.setString(2, users.getPrenom_user());
						prepdStmt.setString(3, users.getEmail_user());	
						prepdStmt.setString(4, users.getPass_user());
						prepdStmt.setInt(5, users.getId_user());
						prepdStmt.executeUpdate();
				 }
				 catch (SQLException e) {
				 }
	 }
	 
}
