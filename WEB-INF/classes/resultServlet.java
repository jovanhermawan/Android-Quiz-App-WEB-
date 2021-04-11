// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/result")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class resultServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/clicker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {// Step 3 & 4: Execute a SQL SELECT query and Process the query result
         // Retrieve the books' id. Can order more than one books.
         // Returns an array of Strings
            String id = request.getParameter("id");
            String sqlStr = "SELECT choice, COUNT(*) AS count FROM responses WHERE questionNo = " +id+" GROUP BY choice";
            out.println("<!DOCTYPE html> <html lang=\"en-US\"> <head> <link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Open+Sans\" /> <style>$yellow:#f5ba1a; $black:#000000; $grey:#cccccc; body { font-family: Open Sans, Geneva, sans-serif; font-size: 14px; background: #f2f2f2; } .clearfix { &:after { content: \"\"; display: block; clear: both; visibility: hidden; height: 0; } } .form_wrapper { width: 550px; max-width: 100%; box-sizing: border-box; color:white; margin: 5% auto 0; position: relative; z-index: 1; border-top: 5px solid $yellow; -webkit-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1); -moz-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1); box-shadow: 0 0 3px rgba(0, 0, 0, 0.1); -webkit-transform-origin: 50% 0%; transform-origin: 50% 0%; -webkit-transform: scale3d(1, 1, 1); transform: scale3d(1, 1, 1); -webkit-transition: none; transition: none; -webkit-animation: expand 0.8s 0.6s ease-out forwards; animation: expand 0.8s 0.6s ease-out forwards; opacity: 0; border-radius: 10px; background: white; box-shadow: 0 27px 55px 0 rgba(0, 0, 0, 0.3), 0 17px 17px 0 rgba(0, 0, 0, 0.15); .title_container { text-align: center; padding-bottom: 15px; } h3 { font-size: 1.1em; font-weight: normal; line-height: 1.5em; margin: 0; } label { font-size: 12px; } .row { margin: 10px -15px; >div { padding: 0 15px; box-sizing: border-box; } } .col_half { width: 50%; float: left; } .input_field { position: relative; margin-bottom: 20px; -webkit-animation: bounce 0.6s ease-out; animation: bounce 0.6s ease-out; >span { position: absolute; left: 0; top: 0; color: #333; height: 100%; border-right: 1px solid $grey; text-align: center; width: 30px; >i { padding-top: 100px; } } } .textarea_field { >span { >i { padding-top: 10px; } } } .button{background: $yellow; height: 35px; line-height: 35px; width: 100%; border: none; outline: none; cursor: pointer; color: #fff; font-size: 1.1em; margin-bottom: 10px; -webkit-transition: all 0.30s ease-in-out; -moz-transition: all 0.30s ease-in-out; -ms-transition: all 0.30s ease-in-out; transition: all 0.30s ease-in-out;} &[type=\"checkbox\"], &[type=\"radio\"] { border: 0; clip: rect(0 0 0 0); height: 1px; margin: -1px; overflow: hidden; padding: 0; position: absolute; width: 1px; } } } .form_container { .row { .col_half.last { border-left: 1px solid $grey; } } } @-webkit-keyframes check { 0% { height: 0; width: 0; } 25% { height: 0; width: 7px; } 50% { height: 20px; width: 7px; } } @keyframes check { 0% { height: 0; width: 0; } 25% { height: 0; width: 7px; } 50% { height: 20px; width: 7px; } } @-webkit-keyframes expand { 0% { -webkit-transform: scale3d(1,0,1); opacity:0; } 25% { -webkit-transform: scale3d(1,1.2,1); } 50% { -webkit-transform: scale3d(1,0.85,1); } 75% { -webkit-transform: scale3d(1,1.05,1); } 100% { -webkit-transform: scale3d(1,1,1);  opacity:1; } } @keyframes expand { 0% { -webkit-transform: scale3d(1,0,1); transform: scale3d(1,0,1);  opacity:0; } 25% { -webkit-transform: scale3d(1,1.2,1); transform: scale3d(1,1.2,1); } 50% { -webkit-transform: scale3d(1,0.85,1); transform: scale3d(1,0.85,1); } 75% { -webkit-transform: scale3d(1,1.05,1); transform: scale3d(1,1.05,1); } 100% { -webkit-transform: scale3d(1,1,1); transform: scale3d(1,1,1);  opacity:1; } } @-webkit-keyframes bounce { 0% { -webkit-transform: translate3d(0,-25px,0); opacity:0; } 25% { -webkit-transform: translate3d(0,10px,0); } 50% { -webkit-transform: translate3d(0,-6px,0); } 75% { -webkit-transform: translate3d(0,2px,0); } 100% { -webkit-transform: translate3d(0,0,0); opacity: 1; } } @keyframes bounce { 0% { -webkit-transform: translate3d(0,-25px,0); transform: translate3d(0,-25px,0); opacity:0; } 25% { -webkit-transform: translate3d(0,10px,0); transform: translate3d(0,10px,0); } 50% { -webkit-transform: translate3d(0,-6px,0); transform: translate3d(0,-6px,0); } 75% { -webkit-transform: translate3d(0,2px,0); transform: translate3d(0,2px,0); } 100% { -webkit-transform: translate3d(0,0,0); transform: translate3d(0,0,0); opacity: 1; } } @media (max-width: 600px) { .form_wrapper { .col_half { width: 100%; float: none; } } .bottom_row { .col_half { width: 50%; float: left; } } .form_container { .row { .col_half.last { border-left: none; } } } .remember_me { padding-bottom: 20px; } } h2{ width: 100%; display: flex; align-items: center; justify-content: center; height: 80px; font-size: 20px; font-weight: bold; background: #4aa0a8; border-radius: 10px 10px 0 0;} </style></head><body style=\"background-image:url('lorcamsmall.jpg');background-repeat:repeat; font-family: 'Open Sans'\"> <div class=\"form_wrapper\" style=\"margin-bottom:0px\"><h2 style=\"color:white\">Answers for Question "+ id+"</h2></div><div class=\"form_wrapper\" style=\"margin-top:0px\"> <div id=\"piechart\" ></div> <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script> <script type=\"text/javascript\">google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart);function drawChart() { var data = google.visualization.arrayToDataTable([['Answers', 'Number of Votes']");
            ResultSet rset = stmt.executeQuery(sqlStr);
            while(rset.next()){
            out.println(",['"+rset.getString("choice")+"',"+ rset.getString("count")+"]");
            
            }
         // Print the submit button and </form> end-tag
         
      }catch(Exception ex) {
         out.println("<p>Error: Please Type In Your Unique ID</p>");
         ex.printStackTrace();
      }   // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
      out.println("]);var options = {'title':'', 'width':550, 'height':400};var chart = new google.visualization.PieChart(document.getElementById('piechart')); chart.draw(data, options); } </script> </body> </html>");
      out.close();
   }
}