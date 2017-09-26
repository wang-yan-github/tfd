CKEDITOR.plugins.add('xlist', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xlist', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xlist/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("明细表");
                $("#div-modal-dialog").width(800);
                $("#modaliframe").width(795);
                $("#modaliframe").height(350);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xlist.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xlist.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }  
	    });
        editor.ui.addButton('xlist', {
            label: "明细表",
            command: 'xlist',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xlist', this.path + 'dialogs/xlist.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xlist:{
					label : "明细表属性",
					command : 'xlist',
					group : 'xlist',
					order : 5
				}
			});
		}
		
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element, selection){
				if ( !element || element.isReadOnly() )	return null;

				var tagName = element.hasAscendant( 'img', 1 );
				
				if ( tagName && element.getAttribute('xtype')=="xlist"){
					return {
						xlist : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});