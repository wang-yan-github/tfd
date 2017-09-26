CKEDITOR.plugins.add('xselect', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xselect', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xselect/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("下拉列表控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(240);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xselect.validate()){
                        	editor.insertHtml(window["modaliframe"].flowform_plugin_xselect.toDomStr());
                        	$('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xselect', {
            label: "下拉列表控件",
            command: 'xselect',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xselect', this.path + 'dialogs/xselect.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xselect:{
					label : "下拉列表控件属性",
					command : 'xselect',
					group : 'xselect',
					order : 5
				}
			});
		}
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;
				
				var tagName = element.hasAscendant( 'select', 1 );
				if ( tagName && element.getAttribute('xtype')=="xselect"){
					return {
						xselect : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});