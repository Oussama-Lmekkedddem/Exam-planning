

var ctx2 = document.getElementById("pieChart").getContext('2d');
var pieChart = new Chart(ctx2, {
    type: 'pie',
    data: {
        datasets: [{
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            label: 'Dataset 1'
        }],
        labels: [
            "Red",
            "Orange",
            "Yellow",
            "Green",
            "Blue"
        ]
    },
    options: {
        responsive: true
    }

});

var ctx1 = document.getElementById("barChart").getContext('2d');
var barChart = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        datasets: [{
            label: 'Amount received',
            data: [12, 19, 3, 5, 10, 5, 13, 17, 11, 8, 11, 9],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

// comboBarLineChart
var ctx2 = document.getElementById("comboBarLineChart").getContext('2d');
var comboBarLineChart = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        datasets: [{
            type: 'line',
            label: 'Dataset 1',
            borderColor: '#484c4f',
            borderWidth: 3,
            fill: false,
            data: [12, 19, 3, 5, 2, 3, 13, 17, 11, 8, 11, 9],
        }, {
            type: 'bar',
            label: 'Dataset 2',
            backgroundColor: '#FF6B8A',
            data: [10, 11, 7, 5, 9, 13, 10, 16, 7, 8, 12, 5],
            borderColor: 'white',
            borderWidth: 0
        }, {
            type: 'bar',
            label: 'Dataset 3',
            backgroundColor: '#059BFF',
            data: [10, 11, 7, 5, 9, 13, 10, 16, 7, 8, 12, 5],
        }],
        borderWidth: 1
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});




var ctxL = document.getElementById("lineChart").getContext('2d');
var myLineChart = new Chart(ctxL, {
    type: 'line',
    data: {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
            label: "My First dataset",
            data: [65, 59, 80, 81, 56, 55, 40],
            backgroundColor: [
                'rgba(105, 0, 132, .2)',
            ],
            borderColor: [
                'rgba(200, 99, 132, .7)',
            ],
            borderWidth: 2
        },
            {
                label: "My Second dataset",
                data: [28, 48, 40, 19, 86, 27, 90],
                backgroundColor: [
                    'rgba(0, 137, 132, .2)',
                ],
                borderColor: [
                    'rgba(0, 10, 130, .7)',
                ],
                borderWidth: 2
            }
        ]
    },
    options: {
        responsive: true
    }
});



var ctxPA = document.getElementById("polarChart").getContext('2d');
var myPolarChart = new Chart(ctxPA, {
    type: 'polarArea',
    data: {
        labels: ["Red", "Green", "Yellow", "Grey", "Dark Grey"],
        datasets: [{
            data: [300, 50, 100, 40, 120],
            backgroundColor: ["rgba(219,0,0,0.67)", "rgba(0,165,2,0.7)", "rgba(255,195,15,0.73)",
                "rgba(55,59,66,0.63)", "rgba(0,0,0,0.68)"
            ],
            hoverBackgroundColor: ["rgba(219,0,0,0.71)", "rgba(0,165,2,0.68)",
                "rgba(255,195,15,0.71)", "rgba(55,59,66,0.7)", "rgba(0,0,0,0.66)"
            ]
        }]
    },
    options: {
        responsive: true
    }
});


