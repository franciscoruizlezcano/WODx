$(document).ready(function () {
    init();

    function init() {

        initComponents();
    }

    function initComponents() {
        if ($('#table')) {
            $('#table').DataTable();
        }

        if ($('[data-toggle="tooltip"]')) {
            $('[data-toggle="tooltip"]').tooltip()
        }

        if ($('.input-group.date')) {
            $('.input-group.date').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayHighlight: true
            });
        }

        if (document.getElementsByClassName('needs-validation')) {
            // Get the forms we want to add validation styles to
            let forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            let validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });

        }
    }
});