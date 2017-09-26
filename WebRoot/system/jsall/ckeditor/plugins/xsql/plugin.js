CKEDITOR.plugins.add('xsql', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xsql', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xsql/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("SQL查询");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(200);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xsql.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xsql.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xsql', {
            label: "SQL查询",
            command: 'xsql',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xsql', this.path + 'dialogs/xsql.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xsql:{
					label : "SQL查询属性",
					command : 'xsql',
					group : 'xsql',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() ) return null;

				var tagName = element.hasAscendant( 'img', 1 );
				if ( tagName && element.getAttribute('xtype')=="xsql"){
					return {
						xsql : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});