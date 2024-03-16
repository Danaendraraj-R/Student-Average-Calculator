<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
    
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
    
        h3 {
            text-align: center;
            color: #333;
        }
    
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }
    
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    
        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            cursor: pointer;
        }
    
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a{
           text-decoration: none;
            color: #000;
            cursor: pointer;
        }
        </style>
</head>

<body>
    <%
    // Retrieve values from the session
    String email = (String) session.getAttribute("email");
    String username = (String) session.getAttribute("username");

    if (email == null || username == null) {
        response.sendRedirect("index.html");
    }
%>

  <center>  
    <form action='Register' method='post'>
        <center><span id="usernameDisplay"><h3>Welcome <%= username %></h3></span></center>
        <h3>Add Marks</h3>
        <label for='name'>Name:</label>
        <input type='text' name='name' required><br>

        <label for='class'>Class:</label>
        <input type='text' name='class' required><br>

        <label for='seatNo'>Seat Number:</label>
        <input type='text' name='seatNo' required><br>

        <label for='totalMarks'>Total Marks(?/500):</label>
        <input type='text' name='totalMarks' required><br>

        <input type='submit' value='Submit'>


        <center><a href="View.jsp">View Records</a></center>
        
    </form>
    
        <form action="Logout" method="post"> 
            <input type="submit" value="Logout">
          </form>

    </center>


    
</body>
</html>
