$((function () {
    function refresh() {
        let genre = $("#genre")
        let title = $("#title")
        
        
       
       

        
       
        

        $.getJSON("show.php", {genre: genre.val(), title: title.val()}, function (json) {
            $("table tr:gt(0)").remove()
            json.forEach(function (thing) {
                $("table").append(`<tr>
                               
                                <td>${thing[1]}</td>
                                <td>${thing[2]}</td>
                                <td>${thing[3]}</td>
                                <td>${thing[4]}</td>
                               
                               
                                <td>
                                    <a href=editMultimediaFile.php?id=${thing[0]}>Update</a>
                                    <a href=deleteMultimediaFile.php?id=${thing[0]}>Delete</a>
                                    <br>
                                    <br>
                                    
                                </td>
                               </tr>`)
            })
        })
       
        
        
       
        $("#info").text(`The query has been done with the genre "${genre.val()}" and the title "${title.val()}  "`)

       
    }

    $("#genre, #title").on("input", function () {
        refresh()
    })

    refresh()
}));