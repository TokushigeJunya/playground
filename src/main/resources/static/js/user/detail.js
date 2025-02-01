document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("updateButton").addEventListener("click", function(event) {
        event.preventDefault();
        document.getElementById("popup-message").textContent = "本当に更新を実行しますか？";
        document.getElementById("popup").style.display = "block";
        document.getElementById("popup").dataset.action = "update"
    });

    document.getElementById("deleteButton").addEventListener("click", function(event) {
        event.preventDefault();
        document.getElementById("popup-message").textContent = "本当に削除を実行しますか？";
        document.getElementById("popup").style.display = "block";
        document.getElementById("popup").dataset.action = "delete"
    });
            
    document.getElementById("confirmButton").addEventListener("click", function() {
         var action = document.getElementById("popup").dataset.action;
		if (action === "update") { document.getElementById("user-update-form").submit();}
		else if (action === "delete") { document.getElementById("user-delete-form").submit();}
     });

    document.getElementById("cancelButton").addEventListener("click", function() {
        document.getElementById("popup").style.display = "none";
    });
});