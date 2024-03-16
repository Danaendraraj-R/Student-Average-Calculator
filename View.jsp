<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Data</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
                table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #4caf50;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #4caf50;
        }
        a{
           text-decoration: none;
            color: #fff;
            cursor: pointer;
            background-color: #4caf50;
            width:120px;
        }
    </style>
</head>
<body>

    <h2>View Records</h2>
    
    <div id="dataContainer"></div>

    <center><a href="Index1.jsp">Back</a></center>

    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "View",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    displayData(data);
                },
                error: function(error) {
                    console.log("Error fetching data: " + error);
                }
            });

            function displayData(data) {
                var container = $("#dataContainer");
                container.empty();

                if (data.length === 0) {
                    container.append("<p>No data available</p>");
                } else {
                    var table = "<table border='1'><tr><th>Name</th><th>Class</th><th>Seat No</th><th>Total Marks</th><th>Percentage</th></tr>";
                    for (var i = 0; i < data.length; i++) {
                        table += "<tr><td>" + data[i].name + "</td><td>" + data[i].class + "</td><td>" + data[i].seatno + "</td><td>" + data[i].totalmarks + "</td><td>" + data[i].percentage + "</td></tr>";
                    }
                    table += "</table>";
                    container.append(table);
                }
            }
        });
    </script>

</body>
</html>
