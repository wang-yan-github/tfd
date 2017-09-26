CKEDITOR.plugins.add('xcheckbox', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xcheckbox', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xcheckbox/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("复选框控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(180);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xcheckbox.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xcheckbox.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }	    	
	    });
        editor.ui.addButton('xcheckbox', {
            label: "复选框控件",
            command: 'xcheckbox',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xcheckbox', this.path + 'dialogs/xcheckbox.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xcheckbox:{
					label : "复选框控件属性",
					command : 'xcheckbox',
					group : 'xcheckbox',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )return null;

				var isInput = element.hasAscendant( 'input', 1 );
				
				if ( isInput && element.getAttribute('xtype')=="xcheckbox" && element.getAttribute('type')=="checkbox"){
					return {
						xcheckbox : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});