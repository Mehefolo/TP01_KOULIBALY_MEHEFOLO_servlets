/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MEHEFOLO
 */
public class EtudiantsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String line="";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulaire etudiant</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align=\"center\">LISTES DES ETUDIANTS</h1>");
            out.println("<div align='center'>");
            out.println("<table border='1px'>");
            out.println("<tr>");
            out.println("<th>NOM</th>");
            out.println("<th>PRENOM</th>");
            out.println("<th>EMAIL</th>");
            out.println("</tr>");
                try{
                    BufferedReader br = new BufferedReader(new FileReader("etudiants.csv"));
                
                    while((line = br.readLine()) != null){
                        String[] donne = line.split(",");
                        out.println("<tr>");
                        out.println("<td>"+donne[0]+"</td>");
                        out.println("<td>"+donne[1]+"</td>");
                        out.println("<td>"+donne[2]+"</td>");
                        out.println("</tr>");
                    }
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
            out.println("<table>");
            out.println("<a href=\"/TP01_KOULIBALY_MEHEFOLO/\">Enregistrer des étudiants </a>");
            out.println("<div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String separateur=",";
        String retourChariot="\n";
        String file_header = "nom,prenom,email";
        String nom= request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        FileWriter filewritter= new FileWriter("etudiants.csv",true);
        filewritter.append(nom);
        filewritter.append(separateur);
        filewritter.append(prenom);
        filewritter.append(separateur);
        filewritter.append(email);
        filewritter.append(separateur);
        filewritter.append(retourChariot);
        filewritter.flush();
        filewritter.close();
        PrintWriter out = response.getWriter();
        out.println("Sauvegardé !");
        doGet(request,response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
