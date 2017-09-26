
var gridbordercolor = "#eee";

var InitiateEasyPieChart = function () {
    return {
        init: function () {
            var easypiecharts = jQuery('[data-toggle=easypiechart]');
            jQuery.each(easypiecharts, function () {
                var barColor = getcolor(jQuery(this).data('barcolor')) || themeprimary,
                    trackColor = getcolor(jQuery(this).data('trackcolor')) || false,
                    scaleColor = getcolor(jQuery(this).data('scalecolor')) || false,
                    lineCap = jQuery(this).data('linecap') || "round",
                    lineWidth = jQuery(this).data('linewidth') || 3,
                    size = jQuery(this).data('size') || 110,
                    animate = jQuery(this).data('animate') || false;

                jQuery(this).easyPieChart({
                    barColor: barColor,
                    trackColor: trackColor,
                    scaleColor: scaleColor,
                    lineCap: lineCap,
                    lineWidth: lineWidth,
                    size: size,
                    animate : animate
                });
            });
        }
    };
}();
