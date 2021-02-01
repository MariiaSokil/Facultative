$(document).ready(function() {
    $('#example').DataTable({
            ajax: {
                    url: '/courses',
                    dataSrc: ''
                },
            "columns": [
                { "data": "title" },
                { "data": "topic" },
                { "data": "duration" },
                { "data": "status" },
                { "data": "teacher",
                   render: function ( data, type, row ) {
                                              return data.firstName +' '+ data.lastName;
                                      }
                 },
                { "data": "students",
                   render: function (data, type, row) {
                        return data.length;
                   } },
                 { "data": "",
                     render: function ( data, type, row ) {
                             return 'n/a';
                     }
                 }
            ]
    });
} );