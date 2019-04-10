package ro.siit.web.authors.controller;

import ro.siit.web.authors.model.Author;
import ro.siit.web.authors.model.AuthorDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AuthorsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDAO dao = new AuthorDAO();
        List<Author> authorList = dao.getAllAuthors();
        req.setAttribute("authors", authorList);
        RequestDispatcher r = req.getRequestDispatcher("authors.jsp");
        r.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDAO a = new AuthorDAO();

        // remove author by id from html form
        int rowsAffected = a.removeAuthorById(Integer.parseInt(req.getParameter("param")));

        // fetch new list of authors and display it
        List<Author> list = a.getAllAuthors();
        req.setAttribute("authors", list);
        RequestDispatcher r = req.getRequestDispatcher("authors.jsp");
        r.forward(req, resp);
    }
}
