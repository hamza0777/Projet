package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.base;
import beans.Utilisateur;


@WebServlet("/inscription")
public class inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public inscription() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
        base b=new base();
        String msg=request.getParameter("email");
       boolean rs=b.verifemail(msg);
       
		if (rs==false)
		{
			user.setNom_user(request.getParameter("nom"));
			user.setPrenom_user(request.getParameter("prnom"));
			user.setPass_user(request.getParameter("pass"));
			user.setEmail_user(request.getParameter("email"));
			b.ajoututilisateur(user);
		 }else{
			 String err="Votre Email est deja utiliser!";
			 request.setAttribute("msg", err);
			 }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		
	}

}
