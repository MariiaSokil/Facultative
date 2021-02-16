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


                { "data": "enrollment"},
                 { "data": "price"},
                 { "data": "",
                     render: function ( data, type, row ) {
                             return '<form class="form-inline" method="post" action="/courses?id='+row.id+'">' +
                                         '<input type="hidden" name="title" value="'+ row.title + '" />' +
                                         '<input type="hidden" name="category_id" value="'+ row.category.id + '" />' +
                                         '<input type="hidden" name="category_name" value="'+ row.category.name + '" />' +
                                         '<input type="hidden" name="duration" value="'+ row.duration + '" />' +
                                         '<input type="hidden" name="enrollment" value="'+ row.enrollment + '" />' +
                                         '<input type="hidden" name="price" value="'+ row.price + '" />' +
                                         '<input type="hidden" name="status" value="'+ row.status + '" />' +
                                         '<input type="hidden" name="teacher_id" value="'+ row.teacher.id + '" />' +
                                         '<button class="btn btn-outline-success" type="submit">Apply</button>' +
                                    '</form>';
                     }
                 }
            ]
    });
} );