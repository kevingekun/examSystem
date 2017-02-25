$(document).ready(function(){
		
	// first example
	$("#navigation").treeview({
		persist: "location",
		collapsed: true,
		unique: true
	});
	
	// second example
	$("#browser").treeview();
	$("#add").click(function() {
		var branches = $("<li><span class='folder'>New Sublist</span><ul>" + 
			"<li><span class='file'>Item1</span></li>" + 
			"<li><span class='file'>Item2</span></li></ul></li>").appendTo("#browser");
		$("#browser").treeview({
			add: branches
		});
		branches = $("<li class='closed'><span class='folder'>New Sublist</span><ul><li><span class='file'>Item1</span></li><li><span class='file'>Item2</span></li></ul></li>").prependTo("#folder21");
		$("#browser").treeview({
			add: branches
		});
	});
	
	
	
	

});