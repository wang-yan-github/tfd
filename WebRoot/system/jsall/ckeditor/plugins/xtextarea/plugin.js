CKEDITOR.plugins.add('xtextarea', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xtextarea', {
             exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xtextarea/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("多行文本框");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(220);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xtextarea.validate()){
                        	editor.insertHtml(window["modaliframe"].flowform_plugin_xtextarea.toDomStr());
                        	$('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xtextarea', {
            label: "多行文本框",
            command: 'xtextarea',
           icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xtextarea', this.path + 'dialogs/xtextarea.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xtextarea:{
					label : "多行文本框属性",
					command : 'xtextarea',
					group : 'xtextarea',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )	return null;

				var tagName = element.hasAscendant( 'textarea', 1 );
				if ( tagName && element.getAttribute('xtype')=="xtextarea"){
					return {
						xtextarea : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});
