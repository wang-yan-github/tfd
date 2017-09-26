CKEDITOR.plugins.add('xmacro', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xmacro', {
          exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xmacro/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("宏控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(250);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                $("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xmacro.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xmacro.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }      
        });
        editor.ui.addButton('xmacro', {
            label: "宏控件",
            command: 'xmacro',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xmacro', this.path + 'dialogs/xmacro.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xmacro:{
					label : "宏控件",
					command : 'xmacro',
					group : 'xmacro',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;

				var tagName = element.hasAscendant( 'input', 1 );
				
				if ( tagName && element.getAttribute('xtype')=="xmacro"){
					return {
						xmacro : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});