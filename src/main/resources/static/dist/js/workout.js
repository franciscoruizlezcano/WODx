
let workoutExerciseJson = [];

let athleteJson = [];

let traininglevelJson = [];

$("#formworkout").submit(function (event) {

    if ($("#tableExercise tbody tr").length > 0) {
        if ($("#tableAthlete tbody tr").length > 0 || $("#tableTraininglevel tbody tr").length > 0) {

            let coach = { idCoach: $("#idCoach").val() };

            $("#tableExercise tbody tr").each(function (index) {

                let exercisemode = { idExerciseMode: "" };

                let timeunit = { idTimeUnit: "" };

                let exercise = { idExercise: "" };

                let workout = { idWorkout: "0" };

                let workoutExercise = { workout: workout, exercise: "", series: "", value: "", exercisemode: "", timeunit: "", };

                let data = { idExercise: "", idExerciseMode: "", series: "", value: "", idTimeUnit: "" };

                $(this).children("td").each(function (index2) {
                    switch (index2) {
                        case 0:
                            console.log($(this).text());
                            data.idExercise = $(this).text();
                            break;
                        case 2:
                            data.idExerciseMode = $(this).children(':first-child').val();
                            break;
                        case 3:
                            data.series = $(this).children(':first-child').val();
                            break;
                        case 4:
                            data.value = $(this).children(':first-child').val();
                            break;
                        case 5:
                            data.idTimeUnit = $(this).children(':first-child').val();
                            break;
                    }
                });

                exercise.idExercise = data.idExercise;
                exercisemode.idExerciseMode = data.idExerciseMode;

                if (data.idExerciseMode == '1') {
                    timeunit.idTimeUnit = 0;
                } else {
                    timeunit.idTimeUnit = data.idTimeUnit;
                }

                workoutExercise.exercise = exercise;
                workoutExercise.exercisemode = exercisemode;
                workoutExercise.series = data.series;
                workoutExercise.value = data.value;
                workoutExercise.timeunit = timeunit;

                workoutExerciseJson[index] = workoutExercise;
            });

            $("#tableAthlete tbody tr").each(function (index) {
                let data = { idAthlete: "" };

                let athlete = { idAthlete: "" };

                $(this).children("td").each(function (index2) {
                    switch (index2) {
                        case 0:
                            data.idAthlete = $(this).text();
                            break;
                    }

                });
                athlete.idAthlete = data.idAthlete;
                athleteJson[index] = athlete;
            });

            $("#tableTraininglevel tbody tr").each(function (index) {
                let data = { idTrainingLevel: "" };

                let traininglevel = { idTrainingLevel: "" };

                $(this).children("td").each(function (index2) {
                    switch (index2) {
                        case 0:
                            data.idTrainingLevel = $(this).text();
                            break;
                    }
                });
                traininglevel.idTrainingLevel = data.idTrainingLevel;
                traininglevelJson[index] = traininglevel;
            });

            $("#athleteJson").val(JSON.stringify(athleteJson));
            $("#traininglevelJson").val(JSON.stringify(traininglevelJson));
            $("#workoutExerciseJson").val(JSON.stringify(workoutExerciseJson));
        } else {
            event.preventDefault();
            $("#completetable").css("display", "block");
        }
    } else {
        event.preventDefault();
        $("#exercisetable").css("display", "block");
    }
});

function add(nameSelect, nameTable) {
    let select = document.getElementById(nameSelect);
    let table = document.getElementById(nameTable);
    let selected = select.options[select.selectedIndex];

    let row = table.children[1].insertRow(0);
    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);

    cell1.innerHTML = selected.value;
    cell2.innerHTML = selected.textContent;

    selected.remove();
}

function addExercise(exercisemodeList, timeunitList) {
    let select = document.getElementById("selectExercise");
    let table = document.getElementById("tableExercise");
    let selected = select.options[select.selectedIndex];

    //Create elements on row
    let exercisemodeTable = document.createElement("select");
    let valueTable = document.createElement("input");
    let seriesTable = document.createElement("input");
    let timeunitTable = document.createElement("select");


    exercisemodeTable.className = "form-control";
    exercisemodeTable.required = "true";
    exercisemodeTable.onchange = function () {
        if (exercisemodeTable.value == '2') {
            timeunitTable.style.display = "block";
        } else {
            timeunitTable.style.display = "none";
            timeunitTable.selectedIndex = "";
            timeunitTable.value = "";
        }
    };


    valueTable.className = "form-control";
    valueTable.type = "number";
    valueTable.required = "true";
    valueTable.min = "1";

    seriesTable.className = "form-control";
    seriesTable.type = "number";
    seriesTable.required = "true";
    seriesTable.min = "1";

    timeunitTable.className = "form-control";

    //exercisemodeTable <select>
    for (var i = 0; i < exercisemodeList.length; i++) {
        let option = document.createElement("option");
        option.value = exercisemodeList[i]["idExerciseMode"];
        option.text = exercisemodeList[i]["description"];
        exercisemodeTable.appendChild(option);
    }

    //timeunitTable <select>
    for (var i = 0; i < timeunitList.length; i++) {
        let option = document.createElement("option");
        option.value = timeunitList[i]["idTimeUnit"];
        option.text = timeunitList[i]["description"];
        timeunitTable.appendChild(option);
    }

    //Create input series
    let tbody = table.children[1];
    let cantRows = 0;
    console.log(cantRows);
    if ($("#tableExercise tbody tr").length > 0) {
        cantRows = $("#tableExercise tbody tr").length;
    }
    let row = table.children[1].insertRow(cantRows);

    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let cell3 = row.insertCell(2);
    let cell4 = row.insertCell(3);
    let cell5 = row.insertCell(4);
    let cell6 = row.insertCell(5);

    cell1.innerHTML = selected.value;
    cell2.innerHTML = selected.textContent;
    cell3.appendChild(exercisemodeTable);
    cell4.appendChild(valueTable);
    cell5.appendChild(seriesTable);
    cell6.appendChild(timeunitTable);

    timeunitTable.style.display = "none";

    if (selected.children.length == 0) {
        selected.display = "none";
    }
}

