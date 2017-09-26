CKEDITOR.plugins.add('xiframe', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xiframe', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xiframe/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("嵌套窗体");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(180);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xiframe.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xiframe.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xiframe', {
            label: "嵌套窗体",
            command: 'xiframe',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xiframe', this.path + 'dialogs/xiframe.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xiframe:{
					label : "嵌套窗体",
					command : 'xiframe',
					group : 'xiframe',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )	return null;

				var tagName = element.hasAscendant( 'img', 1 );
				
				if ( tagName && element.getAttribute('xtype')=="xiframe"){
					return {
						xiframe : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});