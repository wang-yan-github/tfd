CKEDITOR.plugins.add('xupload', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xupload', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xupload/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("单附件上传");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(100);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xupload.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xupload.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xupload', {
            label: "单附件上传",
            command: 'xupload',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xupload', this.path + 'dialogs/xupload.js');
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xupload:{
					label : "单附件上传属性",
					command : 'xupload',
					group : 'xupload',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;

				var isInput = element.hasAscendant( 'input', 1 );
				if ( isInput && element.getAttribute('xtype')=="xupload"){
					return {
						xupload : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});