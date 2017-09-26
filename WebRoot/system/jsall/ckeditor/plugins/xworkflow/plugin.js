CKEDITOR.plugins.add('xworkflow', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xworkflow', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xworkflow/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("流程选择控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(150);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xworkflow.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xworkflow.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }	    	
	    });
        editor.ui.addButton('xworkflow', {
            label: "流程选择控件",
            command: 'xworkflow',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xworkflow', this.path + 'dialogs/xworkflow.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xworkflow:{
					label : "流程选择控件",
					command : 'xworkflow',
					group : 'xworkflow',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )	return null;

				var tagName = element.hasAscendant( 'img', 1 );
				if ( tagName && element.getAttribute('xtype')=="xworkflow"){
					return {
						xworkflow : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});