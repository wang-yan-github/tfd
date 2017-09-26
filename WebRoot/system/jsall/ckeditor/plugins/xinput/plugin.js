CKEDITOR.plugins.add('xinput', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xinput', {
        	exec:function(){
	    		var url = contextPath+"/system/jsall/ckeditor/plugins/xinput/plugin.jsp";
	    		$("#div-modal-dialog").width(360);
	    		$("#myModalLabel").html("单行输入框");
	    		
	    		$("#modaliframe")[0].contentWindow.location=url;
	    		$("#modaliframe").width(355);
	    		$("#modaliframe").height(220);
	    		
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xinput.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xinput.toDomStr());
                            $('#modal').modal("hide");
                        }
                	});
                });
	    	}
	    });
        editor.ui.addButton('xinput', {
            label: "单行输入框",
            command: 'xinput',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xinput', this.path + 'dialogs/xinput.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xinput:{
					label : "单行输入框属性",
					command : 'xinput',
					group : 'xinput',
					order : 1
				}
			});
		}
		
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element,selection){
				if (!element || element.isReadOnly()) return null;

				var isInput = element.hasAscendant( 'input', 1 );
				
				if ( isInput && element.getAttribute('xtype')=="xinput"){
					return {
						xinput : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});