<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Page</title>
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

        .total-amount {
            font-size: 18px;
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Reimbursement Claim</h2>
        <form id="claimForm">
            <div class="form-group">
                <button class="btn btn-primary">Create New Claim</button>
            </div>
            <div class="form-group">
                <label for="tripDate">Trip Date:</label>
                <input type="date" class="form-control" id="tripDate">
            </div>
            <div class="form-group">
                <label for="receiptType">Select Receipt Type:</label>
                <select class="form-control" id="receiptType">
                    <option value="taxi">Taxi</option>
                    <option value="hotel">Hotel</option>
                    <option value="planeTicket">Plane Ticket</option>
                    <option value="train">Train</option>
                </select>
            </div>
            <div class="form-group">
                <label for="receiptAmount">Receipt Amount:</label>
                <input type="number" class="form-control" id="receiptAmount" placeholder="Receipt Amount">
                <button class="btn btn-secondary mt-2" type="button" id="addReceipt">Add Receipt</button>
            </div>
            <div id="receiptContainer"></div>
            <div class="form-group">
                <label for="dailyAllowance">Claim Daily Allowance:</label>
                <input type="number" class="form-control" id="dailyAllowance" placeholder="Number of days">
            </div>
            <div class="form-group">
                <label for="carUsage">Claim Reimbursement for Car Usage:</label>
                <input type="number" class="form-control" id="carUsage" placeholder="Distance in km">
            </div>
            <button class="btn btn-primary" type="button" id="createClaim">Submit</button>
            <div class="total-amount">
            Total Reimbursement Amount: $<span id="calculatedReimbursement">0.00</span>
        </div>
        </form>
        
    </div>
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        var addReceiptButton = document.querySelector("#addReceipt");
        var receiptContainer = document.querySelector("#receiptContainer");

        addReceiptButton.addEventListener("click", function() {
            var receiptType = document.querySelector("#receiptType").value;
            var receiptAmount = document.querySelector("#receiptAmount").value;

            var receiptRow = document.createElement("div");
            receiptRow.className = "receipt-row";
            receiptRow.textContent = receiptType + " - $" + receiptAmount;

            receiptContainer.appendChild(receiptRow);

            // Clear the receipt type and amount fields
            document.querySelector("#receiptType").value = "";
            document.querySelector("#receiptAmount").value = "";
        });

        var submitButton = document.querySelector("#createClaim");
        submitButton.addEventListener("click", function() {
            // Collect user input
            var tripDate = document.querySelector("#tripDate").value;
            var dailyAllowance = document.querySelector("#dailyAllowance").value;
            var carUsage = document.querySelector("#carUsage").value;

            // Collect receipt data
            var receiptRows = document.querySelectorAll(".receipt-row");
            var receipts = [];
            receiptRows.forEach(function(row) {
                var receiptParts = row.textContent.split(" - $");
                var receiptType = receiptParts[0];
                var receiptAmount = parseFloat(receiptParts[1]);
                receipts.push({ type: receiptType, amount: receiptAmount });
            });

            // Create a data object to send to the server
            var formData = {
                tripDate: tripDate,
                dailyAllowance: dailyAllowance,
                carUsage: carUsage,
                receipts: receipts
            };

            var baseUrl = "<%= request.getContextPath() %>/user";

            // Send data to the server using AJAX
            var xhr = new XMLHttpRequest();
            xhr.open("POST", baseUrl, true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Response received from the server, update the UI 
                	var responseObj = JSON.parse(xhr.responseText);
                    var calculatedReimbursement = responseObj.calculatedReimbursement;
                    var calculatedReimbursementElement = document.getElementById("calculatedReimbursement");
                    calculatedReimbursementElement.textContent = calculatedReimbursement.toFixed(2);
                }
            };

            xhr.send(JSON.stringify(formData));
        });
    });

    </script>
</body>
</html>
