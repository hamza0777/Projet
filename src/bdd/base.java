package bdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import beans.Utilisateur;
import beans.rapport;

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
	 public void modifier(String nom ,String prenom ,String pass ,String email){
		 loaddatabase();
		 
		 String quer ="UPDATE user SET Nom_user='?' , prenom_user='?' , pass_user='?' WHERE email_user='?'";
				 try {
					 PreparedStatement  prepdStmt = connexion.prepareStatement(quer);
						prepdStmt.setString(1, email);
						prepdStmt.setString(2, prenom);
						prepdStmt.setString(3, pass);
						prepdStmt.setString(4, email);
						prepdStmt.execute();
						//System.out.println("cc");
				 }
				 catch (SQLException e) {
					 e.printStackTrace();
				 }
	 }
	 public List<rapport> affiche(){
		 loaddatabase();
		 List<rapport> list=new ArrayList<rapport>();
		 String	 query = "SELECT *  FROM `rapports`";
			
			try{
			
			 Statement stmt = (Statement) connexion.createStatement();	
			 	ResultSet res = stmt.executeQuery(query);
			 	while(res.next()){
			 		rapport s=new rapport();
			 		s.setId_rapport(res.getInt("id_rapport"));
			 		s.setId_user(res.getInt("Id_user"));
			 		s.setNom_etudi(res.getString("nom_etudi"));
			 		s.setPrenom_etudiant(res.getString("prenom_etudi"));
			 		s.setTitre_rapport(res.getString("titre_rapport"));
			 		s.setRapport(res.getString("rapport"));
			 		s.setAnnee_rapport(res.getString("annee_rapport()"));
			 		s.setBranche_etudes(res.getString("branche_etudes"));
			 		s.setMention_rapport(res.getString("mention_rapport"));
			 		s.setDescription(res.getString("description"));
			 		System.out.println(s.toString());
			 		list.add(s);			 
			 		}
			 	
			 			 
			}catch (SQLException e) {
			}
		 return list;
	 }
	 
}
