/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    var html = "<div style=\"valign:middle;width:200px;height:100%;background-color:#0093FF;line-height:60px;text-align:center;margin-top:60px;color:#FFF;font-weight:900;\" align=\"center\" >请选择上传图片</div>";
    config.image_previewText=html; //预览区域显示内容
    config.filebrowserImageUploadUrl= contextPath+"/com/system/filetool/UpImgTool/imgUpload.act"; //待会要上传的action或servlet
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
};
