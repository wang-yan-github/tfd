CKEDITOR.plugins.add('xuploads', {
    requires: ['dialog'],
    init: function(editor){
    	editor.addCommand('xuploads', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xuploads/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("批量附件上传");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(100);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xuploads.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xuploads.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
        });
        editor.ui.addButton('xuploads', {
            label: "批量附件上传",
            command: 'xuploads',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xuploads', this.path + 'dialogs/xuploads.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xuploads:{
					label : "批量附件上传",
					command : 'xuploads',
					group : 'xuploads',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )
					return null;

				var isInput = element.hasAscendant( 'input', 1 );
				if ( isInput && element.getAttribute('xtype')=="xuploads")
				{
					return {
						xuploads : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});