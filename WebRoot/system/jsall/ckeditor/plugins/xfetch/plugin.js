CKEDITOR.plugins.add('xfetch', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('xfetch', {
             exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xfetch/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("选择器控件");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(260);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xfetch.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xfetch.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }   
	    });
        editor.ui.addButton('xfetch', {
            label: "选择器控件",
            command: 'xfetch',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xfetch', this.path + 'dialogs/xfetch.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xfetch:{
					label : "选择器控件属性",
					command : 'xfetch',
					group : 'xfetch',
					order : 5
				}
			});
		}
		
		if ( editor.contextMenu ){
			editor.contextMenu.addListener( function( element, selection ){
				if ( !element || element.isReadOnly() )return null;

				var isInput = element.hasAscendant( 'input', 1 );
				
				if ( isInput && element.getAttribute('xtype')=="xfetch"){
					return {
						xfetch : CKEDITOR.TRISTATE_OFF
					};
				}

				return null;
			});
		}
    }
});