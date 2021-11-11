const callAjax = async (url, data, method) => {
    let out;
    await $.ajax({
        url: url,
        data: data,
        contentType: 'application/json',
        error: function () {
            console.log("error")
        },
        success: function (dataOut) {
            out = dataOut;
        },
        type: method
    });
    return out;
}