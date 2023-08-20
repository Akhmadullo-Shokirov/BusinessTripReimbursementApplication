<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f7f7f7;
            font-family: Arial, sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin Panel</h2>
        <form id="adminForm">
            <div class="form-group">
                <label for="dailyAllowanceRate">Daily Allowance Rate ($/day):</label>
                <input type="number" class="form-control" id="dailyAllowanceRate" value="15">
            </div>
            <div class="form-group">
                <label for="carMileageRate">Car Mileage Rate ($/km):</label>
                <input type="number" class="form-control" id="carMileageRate" value="0.3">
            </div>
            <div class="form-group">
                <label for="receiptsList">Edit Receipts List:</label>
                <textarea class="form-control" id="receiptsList" rows="3">taxi,hotel,planeTicket,train</textarea>
            </div>
            <div class="form-group">
                <label for="reimbursementLimit">Define Reimbursement Limit:</label>
                <select class="form-control" id="reimbursementLimit">
                    <option value="receiptType">Per Receipt Type</option>
                    <option value="totalReimbursement">Total Reimbursement</option>
                    <option value="distance">Distance</option>
                </select>
                <input id="limitAmount" type="number" class="form-control mt-2" placeholder="Limit Amount">
            </div>
            <button class="btn btn-primary" type="button" id="saveChanges">Save Changes</button>
        </form>
    </div>
    <script>
        document.querySelector("#saveChanges").addEventListener("click", function() {
            // Collect admin input
            var dailyAllowanceRate = document.querySelector("#dailyAllowanceRate").value;
            var carMileageRate = document.querySelector("#carMileageRate").value;
            var receiptsList = document.querySelector("#receiptsList").value;
            var reimbursementLimitType = document.querySelector("#reimbursementLimit").value;
            var limitAmount = document.querySelector("#limitAmount").value;

            // Create a data object to send to the server
            var formData = {
                dailyAllowanceRate: dailyAllowanceRate,
                carMileageRate: carMileageRate,
                receiptsList: receiptsList,
                reimbursementLimit: reimbursementLimitType,
                limitAmount: limitAmount
            };

         // Set the base URL for the AJAX request
         var baseUrl = "<%= request.getContextPath() %>/admin"

            // Send data to the server using AJAX
            var xhr = new XMLHttpRequest();
            xhr.open("POST", baseUrl, true); // Append the path to the base URL
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    
                }
            };
            
            var params = Object.keys(formData).map(function(key) {
                return encodeURIComponent(key) + "=" + encodeURIComponent(formData[key]);
            }).join("&");
            
            console.log("Params: " + params)
			console.log("URL: " + baseUrl)
            xhr.send(params);
        });
    </script>
</body>
</html>
