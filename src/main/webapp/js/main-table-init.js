$(document).ready(function() {
    $('#example').DataTable({
            ajax: {
                    url: '/courses',
                    dataSrc: ''
                },
            "columns": [
                { "data": "title" },
                { "data": "category",
                  render: function ( data, type, row ) {
                                             return data.name; }
                },
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
                             return '<form class="form-inline"><button disabled class="btn btn-outline-success" type="submit">Apply</button></form>';
                     }
                 }
            ]
    });
} );