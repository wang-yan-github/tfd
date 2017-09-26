CKEDITOR.plugins.add('xdocnum', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xdocnum', {
	    	exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xdocnum/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("流程计数器");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(140);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xdocnum.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xdocnum.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xdocnum', {
            label: "文号控件",
            command: 'xdocnum',
            icon: this.path + 'images/code.jpg'
        });
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xdocnum:{
					label : "文号控件",
					command : 'xdocnum',
					group : 'xdocnum',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )return null;

				var isInput = element.hasAscendant( 'input', 1 );
				
				if ( isInput && element.getAttribute('xtype')=="xdocnum"){
					return {
						xdocnum : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});