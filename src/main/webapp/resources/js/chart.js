$(document).ready(function () {

    google.charts.load('current', { 'packages': ['corechart'] });
    google.charts.setOnLoadCallback(drawChart1);

    function drawChart1() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work', 11],
            ['Eat', 2],
            ['Commute', 2],
            ['Watch TV', 2],
            ['Sleep', 7]
        ]);

        var data2 = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work', 11],
            ['Eat', 22],
            ['Commute', 1],
            ['Watch TV', 5],
            ['Sleep', 9]
        ]);

        var data3 = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work', 3],
            ['Eat', 2],
            ['Commute', 13],
            ['Watch TV', 15],
            ['Sleep', 9]
        ]);

        var options = {
            title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('chartContainer1'));
        chart.draw(data, options);

        var chart2 = new google.visualization.PieChart(document.getElementById("chartContainer2"));
        chart2.draw(data2, options);

        var chart3 = new google.visualization.PieChart(document.getElementById("chartContainer3"));
        chart3.draw(data3, options);
    };
});