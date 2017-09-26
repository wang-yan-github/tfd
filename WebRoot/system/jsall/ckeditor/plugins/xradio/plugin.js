CKEDITOR.plugins.add('xradio', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xradio', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xradio/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("单项选择控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(180);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xradio.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xradio.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
        });
        editor.ui.addButton('xradio', {
            label: "单项选择控件",
            command: 'xradio',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xradio', this.path + 'dialogs/xradio.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xradio:{
					label : "单项选择控件属性",
					command : 'xradio',
					group : 'xradio',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;
	
				var tagName = element.hasAscendant( 'input', 1 );
				
				if ( tagName && element.getAttribute('xtype')=="xradio" && element.getAttribute('type')=="radio"){
					return {
						xradio : CKEDITOR.TRISTATE_OFF
					};
				}
	
				return null;
			});
		}
    }
});