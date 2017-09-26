CKEDITOR.plugins.add('xcalculate', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xcalculate', {
             exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xcalculate/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("数据计算控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(160);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xcalculate.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xcalculate.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xcalculate', {
            label: "数据计算控件",
            command: 'xcalculate',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xcalculate', this.path + 'dialogs/xcalculate.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xcalculate:{
					label : "数据计算控件",
					command : 'xcalculate',
					group : 'xcalculate',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;

				var tagName = element.hasAscendant( 'input', 1 );
				
				if ( tagName && element.getAttribute('xtype')=="xcalculate"){
					return {
						xcalculate : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});