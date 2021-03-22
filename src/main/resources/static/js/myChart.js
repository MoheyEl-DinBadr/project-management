// For a pie chart
// the passed argument comes from the var created at script tag
var chartDataStr = decodeHtml(chartData);
var charJsonArray = JSON.parse(chartDataStr);

var arrayLength = charJsonArray.length;

var numericData = [];
var labelData = [];

for(var i=0; i<arrayLength; i++){
    numericData[i] = charJsonArray[i].value;
    labelData[i] = charJsonArray[i].label;
}

new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
        title:{
            display:true,
            text:"Project Statuses"
        }
    }
});


// [{"value":1, "label":"COMPLETED"}, {"value":1, "label":"NOT_STARTED"}, {"value":2, "label":"INPROGRESS"}]
function decodeHtml(html){
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}