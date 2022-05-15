$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

//SAVE ============================================

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidecusIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "CustomerAPI",
		type : "type",
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		window.location.reload();
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidempIDSave").val("");
	$("#formItem")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {

	$("#hidempIDSave").val($(this).data("itemid"));
	
	$("#customer_id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#customer_name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#telephoneNo").val($(this).closest("tr").find('td:eq(3)').text());

});

//delete====================================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "CustomerAPI",
		type : "DELETE",
		data : "customerid=" + $(this).data("customerid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		window.location.reload();
		//window.location.reload();
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
//CLIENT-MODEL================================================================
function validateItemForm() {
	if ($("#customerid").val().trim() == "") {
		return "Insert customer id.";
	}
	
	if ($("#customer_name").val().trim() == "") {
		return "Insert Name.";
	}

	if ($("#address").val().trim() == "") {
		return "Insert address.";
	}
	
	var tmpPrice = $("#amount").val().trim();
	if (!$.isNumeric(telephoneNo)) {
		return "Insert a numerical value for mobile number.";
	}

	if ($("#customerid").val().trim() == "") {
		return "Insert Customer ID.";	

	}	
	
	return true;
}
