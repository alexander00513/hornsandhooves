function checkbox() {
    if ($(".disabled:checkbox").prop("checked")) {
        $("input:text").prop("readonly", true).css("color", "#9f6d87");
        $(".select_o").prop("disabled", true).css("color", "#9f6d87");
        $(".select_o:selected").prop("disabled", false);
        $(".select").css("color", "#9f6d87");
        $(".check").prop("value", false)
    } else {
        $("input:text").prop("readonly", false).css("color", "black");
        $(".select_o").prop("disabled", false).css("color", "black");
        $(".select").css("color", "black");
        $(".check").prop("value", true)
    }
}
