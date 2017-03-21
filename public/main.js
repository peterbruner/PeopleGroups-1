function getFiles(personData) {
    for (var i in personData) {
        var elem = $("<a>");
        elem.attr("href", "/person/" + personData[i].id);
        elem.text(personData[i].name);
        $("#personList").append(elem);
        var elem2 = $("<br>");
        $("#personList").append(elem2);
    }
}

$.get("/person", getFiles);
