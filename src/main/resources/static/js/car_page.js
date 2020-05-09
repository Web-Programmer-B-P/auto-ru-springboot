$(document).ready(function () {
    $('#back-home').on('click', function () {
        let status = $('.flag-sale').is(':checked');
        let advertId = $('.advert-id').text();
        if ($('.show_checkbox').text()) {
            markAsSold(status, advertId);
        } else {
            window.location.href = '/';
        }
    });

    if ($('.show_checkbox').text()) {
        $('.box_checkbox').show();
    }
});

function markAsSold(saleStatus, id) {
    let commonData = new FormData();
    commonData.append("id", id);
    commonData.append("saleStatus", saleStatus);
    $.ajax({
        url: '/status',
        type: 'POST',
        data: commonData,
        processData: false,
        contentType: false,
        dataType: "text",
    }).done(function () {
        window.location.href = '/';
    }).fail(function (error) {
        console.log(error)
    });
}