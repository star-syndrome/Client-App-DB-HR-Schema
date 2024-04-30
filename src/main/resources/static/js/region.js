// Dashboard
$(document).ready(() => {
    $("#table-region").DataTable({
        ajax: {
            url: "/api/region/getAll",
            dataSrc: "",
        },
        columnDefs: [
            {className: "text-center", targets: "_all", searchable: true, orderable: true}
        ],
        order: [[1, 'asc']],
        columns: [
            {data: "id"},
            {data: "name"},
            {data: null,
            render: (data) => {
                return /*html*/ `
                <div class="d-flex m-auto gap-4 justify-content-center">
                    <button
                        type="button"
                        class="btn btn-primary d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#details"
                        title="Details ${data.name}"
                        onclick="findById(${data.id})">
                        <span class="material-symbols-rounded"> info </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-warning d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#update"
                        title="Update ${data.name}"
                        onclick="beforeUpdateRegion(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.name}"
                        onclick="deleteRegion(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
            }}
        ]
    });
    $('#table-region')
    .on('draw.dt', function () {
        $('#table-region')
        .DataTable()
        .column(0, {search:'applied', order:'applied'})
        .nodes()
        .each(function (cell, i) {
        cell.innerHTML = i + 1;
        });
    });
});

// Create Region
$("#create-region").click((e) => { 
    e.preventDefault();
    
    let valueName = $("#create-name").val();
    let validateName = validationsRegionName(valueName);

    if (validateName !== "LFG!") {
        Swal.fire({
            position: "center",
            icon: "error",
            title: validateName,
            showConfirmButton: false,
            timer: 1500
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/api/region/create",
            dataType: "JSON",
            beforeSend: addCSRFToken(),
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                name: valueName
            }),
            success: function (response) {
                $("#create").modal("hide");
                $("#table-region").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "New Region Successfully Added!",
                    showConfirmButton: false,
                    timer: 1500
                });
                $("#create-name").val("");
            },
            error: function (error) {
                console.log(error);
                Swal.fire({
                    position: "center",
                    icon: "error",
                    title: "Region Name Already Exists!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
});

// Find Region By ID
function findById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        url: "/api/region/" + id,
        success: function (response) {
            $("#details-id").val(response.id);
            $("#details-name").val(response.name);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

// Get Data Region for Update
function beforeUpdateRegion(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        url: "/api/region/" + id,
        success: function (response) {
            $("#update-id").val(response.id);
            $("#update-name").val(response.name);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

// Update Region
$("#update-region").click((e) => { 
    e.preventDefault();
    
    let valueName = $("#update-name").val();
    let valueId = $("#update-id").val();

    let validateName = validationsRegionName(valueName);
    if (validateName !== "LFG!") {
        Swal.fire({
            position: "center",
            icon: "error",
            title: validateName,
            showConfirmButton: false,
            timer: 1500
        });
    } else {
        $.ajax({
            type: "PUT",
            url: "/api/region/update/"+ valueId,
            dataType: "JSON",
            beforeSend: addCSRFToken(),
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                name: valueName
            }),
            success: function (response) {
                $("#update").modal("hide");
                $("#table-region").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Region Successfully Updated!",
                    showConfirmButton: false,
                    timer: 1500
                });
                $("#update-name").val("");
                $("#update-id").val("");
            },
            error: function (error) {
                console.log(error);
                Swal.fire({
                    position: "center",
                    icon: "error",
                    title: "Region Name Already Exists!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
});

// Delete Region By ID
function deleteRegion(id) {
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: "/api/region/" + id,
                dataType: "JSON",
                beforeSend: addCSRFToken(),
                contentType: "application/json",
                success: (response) => {
                    $("#table-region").DataTable().ajax.reload();
                },
                error: (error) => {
                    console.log(error); 
                }
            });
            Swal.fire({
                title: "Deleted!",
                text: "Region has been deleted.",
                icon: "success"
            });
        }
    });
}

// Validations for Region Name
function validationsRegionName(name) {
    if (name === "") {
        return "Region Name Must Not Blank!"
    } else if (name.length > 25) {
        return "Region Name Too Long!"
    } else {
        return "LFG!"
    }
}