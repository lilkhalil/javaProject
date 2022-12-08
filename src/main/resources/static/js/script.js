$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});

function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("table");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        /* Check if the two rows should switch place,
        based on the direction, asc or desc: */
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }

      }
    }
    table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
        if (dir == "asc") {
            table.getElementsByTagName("th")[n].classList.toggle("th-sort-asc");
        } else {
            table.getElementsByTagName("th")[n].classList.toggle("th-sort-desc");
        }
  }
  (function(document) {
    'use strict';

    var TableFilter = (function(myArray) {
        var search_input;

        function _onInputSearch(e) {
            search_input = e.target;
            var tables = document.getElementsByClassName(search_input.getAttribute('data-table'));
            myArray.forEach.call(tables, function(table) {
                myArray.forEach.call(table.tBodies, function(tbody) {
                    myArray.forEach.call(tbody.rows, function(row) {
                        var text_content = '';
                        for (let i = 0; i < row.cells.length - 1; i++) {
                          text_content += row.cells[i].textContent.toLowerCase();
                        }
                        var search_val = search_input.value.toLowerCase();
                        row.style.display = text_content.indexOf(search_val) > -1 ? '' : 'none';
                    });
                });
            });
        }

        return {
            init: function() {
                var inputs = document.getElementsByClassName("search-input");
                myArray.forEach.call(inputs, function(input) {
                    input.oninput = _onInputSearch;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function() {
        if (document.readyState === 'complete') {
            TableFilter.init();
        }
    });

})(document);

function formChange() {
  showForm(document.getElementById("operation").value.toLowerCase());  
}

function hideAllForms() {
  var forms = document.getElementById("forms").getElementsByTagName("form");
  for (let i = 0; i < forms.length; i++) {
    var form = forms[i];
    form.style.display = "none";
  }
}

function showForm(id) {
  hideAllForms();
  var forms = document.getElementById("forms").getElementsByTagName("form");
  for (var i = forms.length; i--; ) {
    let form = forms[i];
    if (form.getAttribute("id") === id) {
      form.style.display = "block";
    }
  }
}


function removeOptions(selectElement) {
  var i, L = selectElement.options.length - 1;
  for(i = L; i >= 0; i--) {
     selectElement.remove(i);
  }
}

function appendOption(options, selectClass) {
  var select = document.getElementById(selectClass);
  if (select.options[0] != null) {
    removeOptions(select);
  }
  if (selectClass != "orderName") {
    let element = document.createElement("option");
    element.textContent = "*";
    element.value = "*";
    select.appendChild(element);
  }
  for (let i = 0; i < options.length; i++) {
    var opt = options[i];
    var element = document.createElement("option");
    element.textContent = opt;
    element.value = opt;
    select.appendChild(element);
  }
}


function getColumns() {
  var table = document.getElementById("tableName").value;
  var columns;
  switch(table) {
    case "materials":
      columns = ["id", "name", "resistance", "type"];
      break;
    case "employees":
      columns = ["id", "fathername", "job", "name", "surname"];
      break;
    case "gems":
      columns = ["id", "class", "name", "weight"];
      break;
    case "metals":
      columns = ["id", "density", "name", "sample"];
      break;
    case "tools":
      columns = ["id", "name", "power", "type"];
      break;
    case "jewelrys":
      columns = ["id", "cost", "type", "id_gem", "id_metal"];
      break;   
  }
  appendOption(columns, "columnName");
  appendOption(columns, "orderName");
}

document.addEventListener('DOMContentLoaded', function() {
  getColumns();
}, false);