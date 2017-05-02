$(document).ready(function() {


					//Delete specific score by game title and player id
					$("#remove-score").click(function(event) {
						$.ajax({
							type : 'DELETE',
							url : "/game/delete/" + $("#removeGameTitle").val() + "/" + $("#removePlayerId").val(),
							success : function(result) {
								// Empty all boxes after click if all is done !!)
								$("#removeGameTitle").val("");
								findByPlayerId($("#removePlayerId").val());
								$("#removePlayerId").val("");
								alert('Score successfully deleted');

							},

							error : function(result) {
								alert('Incorrect player id or game name');
								$("#removeGameTitle").val("");
								$("#removePlayerId").val("");
							}
						});

					});


					//delete player and all their scores
					$("#delete-score").click(function(event) {
						$.ajax({
							type : 'DELETE',
							url : "/player/" + $("#deletePlayerId").val(),
							success : function(result) {
								// Empty all boxes after click if all is done !!)
								$("#playerTopScores").html("");
								$("#deletePlayerId").val("");
								alert('All scores and player successfully deleted');

							},

							error : function(result) {
								alert('Incorrect player id');
								$("#deletePlayerId").val("");
								
							}
						});

					});

					//Submits new score to database by player id and game title
					$("#submit-score").click(function(event) {
						$.ajax({
							type : 'POST',
							contentType : 'application/json',
							url : "/game/submitscore",
							dataType : "json",
							data : JSON.stringify({
								"gameTitle" : $("#submitGameTitle").val(),
								"score" : $("#submitScoreAmount").val(),
								"playerId" : $("#submitPlayerId").val()
							}),
							success : function(data, textStatus, jqXHR) {
								// Empty all boxes after click if all is done !!)
								$("#submitGameTitle").val("");
								$("#submitScoreAmount").val("");
								findByPlayerId($("#submitPlayerId").val());
								$("#submitPlayerId").val("");
								alert('Score successfully submitted');

							},

							error : function(jqXHR, textStatus, errorThrown) {
								alert('Incorrect player id or game title');
								$("#submitGameTitle").val("");
								$("#submitScoreAmount").val("");
								$("#submitPlayerId").val("");
							}
						});

					});
					//Update score by id and game title
					$("#update-score").click(function(event) {
						$.ajax({
							type : 'PUT',
							contentType : 'application/json',
							url : "/game/updatescore",
							dataType : "json",
							data : JSON.stringify({
								"gameTitle" : $("#updateGameTitle").val(),
								"score" : $("#updateScoreAmount").val(),
								"playerId" : $("#updatePlayerId").val()
							}),
							success : function(data, textStatus, jqXHR) {
								// Empty all boxes after click if all is done !!)
								$("#updateGameTitle").val("");
								$("#updateScoreAmount").val("");
								findByPlayerId($("#updatePlayerId").val());
								alert('Score successfully updated');
								$("#updatePlayerId").val("");

							},

							error : function(jqXHR, textStatus, errorThrown) {
								alert('Incorrect player id');
								$("#updateGameTitle").val("");
								$("#updateScoreAmount").val("");
								$("#updatePlayerId").val("");
							}
						});

					});


					// Register for our service
					$("#registerButton").click(function(event) {
										// Post to url
										$.ajax({
											type : 'POST',
											contentType : 'application/json',
											url : "/player",
											dataType : "json",
											data : JSON.stringify({"playerName" : $("#registerPlayer").val()}), // player name
											success : function(data,textStatus, jqXHR) {
												alert('Registered successfully');

												$("#registerPlayer").val("");

												$("#registerLabel").show();

												$("#inputText").attr('value', data);
											},

											error : function(jqXHR,textStatus,errorThrown) {
												alert(errorThrown);
												alert('Username not atleast 1 character or already exists');
												$("#registerPlayer").val("");
											}
										});

					});

					// onclick for buttons
					$("#get-scores").click(function(event) {

						findByPlayerId($("#getAllTopScores").val());


					});
					$("#get-highscore").click(
						function(event) {
							findByGameName($("#gameTitle").val(), $(
								"#slider-value").val());
						});

					$("#get-player-highscore").click(function(event) {
						findByPlayerName($("#player-name").val());
					});
					$("#get-player-highscore-admin").click(function(event) {
						findAllGamesPlayer($("#player-name-admin").val());
					});
					$("#get-player-id-admin").click(function(event) {
						findUserIdByName($("#player-name-admin-id").val());
					});
					
					
				});

//Start of regular functions for regular customers.
//Find all player scores by player id
function findByPlayerId(playerId) {
	$.ajax({
		type : 'GET',
		url : '/player/'
		+ playerId,
		dataType : "json",
		success : function(data) {
			$("#getAllTopScores").val("");
			$("#playerTopScores").html("");
			for (var i = 0; i < data.length; i++) {

				$("#playerTopScores").append(
					'<tr><th>'
					+ data[i].gameTitle
					+ '</th><td>'
					+ data[i].score
					+ '</td><td>'
					+ data[i].player.playerName
					+ '</td></tr>');

			}

		},
		error : function(jqXHR, textStatus,
			errorThrown) {
			alert('Player not found or no scores in highscore');
			$("#getAllTopScores").val("");
		}
	});
}


// Get all games by player name
function findByPlayerName(playerName) {
	$.ajax({
		type : 'GET',
		url : '/game/topscore/' + playerName,
		dataType : "json",
		success : function(data) {
			// Empty before next query
			$("#playerHighscore").html("");
			for (var i = 0; i < data.length; i++) {

				$("#playerHighscore").append(
					'<tr><th>' + (i + 1) + '</th><td>'
					+ data[i].player.playerName
					+ '</td><td>'
					+ data[i].score
					+ '</td><td>'
					+ data[i].gameTitle
					+ '</td></tr>');

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Player not found');
		}
	});
}



// get all games by game name and amount

function findByGameName(gameName, amount) {
	$.ajax({
		type : 'GET',
		url : '/game/topscore/' + gameName + '/' + amount,
		dataType : "json",
		success : function(data) {
			// Empty before next query
			$("#highscorePage").html("");
			for (var i = 0; i < data.length; i++) {

				$("#highscorePage").append(
					'<tr><th>' + (i + 1) + '</th><td>'
					+ data[i].player.playerName
					+ '</td><td>'
					+ data[i].score
					+ '</td><td>'
					+ data[i].gameTitle
					+ '</td></tr>');

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Game title not found');
		}
	});
}





//Start of admin calls:

//Get all games by player name
function findAllGamesPlayer(playerName) {
	$.ajax({
		type : 'GET',
		url : '/game/topscore/' + playerName,
		dataType : "json",
		success : function(data) {
			// Empty before next query
			$("#playerHighscore").html("");
			for (var i = 0; i < data.length; i++) {

				$("#playerHighscore").append(
					'<tr><th>' + (i + 1) + '</th><td>'
					+ data[i].player.playerName
					+ '</td><td>'
					+ data[i].score
					+ '</td><td>'
					+ data[i].gameTitle
					+ '</td><td>'
					+ '<button onclick="deleteGameById('
					+ data[i].id
					+');">DELETE</button>'
					+ '</td></tr>');

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Player not found');
		}
	});
}

function deleteGameById(gameId) {
	$.ajax({
		type : 'DELETE',
		url : '/admin/api/' + gameId,
		success : function(data) {
			alert("Successfully deleted");
			// Empty before next query
			$("#playerHighscore").html("");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Player not found');
		}
	});
}

function findUserIdByName(playerName) {
	$.ajax({
		type : 'GET',
		url : '/admin/api/' + playerName,
		dataType : "json",
		success : function(data) {
			
			alert('Fetched successfully');

			$("#returnedPlayer").val("");

			$("#userIdLabel").show();

			$("#userIdInputText").attr('value', data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Player not found');
		}
	});
}




// Scaling code for footer to stay down..
$(document).on(
	"pagecreate",
	function() {
		$(document).on("pagecontainershow", function() {
			$(".ui-content").height(getRealContentHeight());
		})

		$(window).on("resize orientationchange", function() {
			$(".ui-content").height(getRealContentHeight());
		})

		function getRealContentHeight() {
			var activePage = $.mobile.pageContainer
			.pagecontainer("getActivePage"), screen = $.mobile
			.getScreenHeight(), header = $(".ui-header").hasClass(
				"ui-header-fixed") ? $(".ui-header").outerHeight() - 1
			: $(".ui-header").outerHeight(), footer = $(
				".ui-footer").hasClass("ui-footer-fixed") ? $(
				".ui-footer").outerHeight() - 1 : $(".ui-footer")
				.outerHeight(), contentMargins = $(".ui-content",
					activePage).outerHeight()
				- $(".ui-content", activePage).height();
				var contentHeight = screen - header - footer - contentMargins;

				return contentHeight;
			}
		});