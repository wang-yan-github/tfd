CKEDITOR.plugins.add('xtextuedit', {
    requires: ['dialog'],
    init: function(editor){
            editor.addCommand('xtextuedit', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/xtextuedit/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("富文本框");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(220);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                	$("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_xtextuedit.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_xtextuedit.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
	    });
        editor.ui.addButton('xtextuedit', {
            label: "富文本框",
            command: 'xtextuedit',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('xtextuedit', this.path + 'dialogs/xtextuedit.js');
		
		if ( editor.addMenuItems ){
			editor.addMenuItems(
			{
				xtextuedit:{
					label : "富文本框属性",
					command : 'xtextuedit',
					group : 'xtextuedit',
					order : 1
				}
			});
		}
		
		if ( editor.contextMenu )
		{
			editor.contextMenu.addListener( function( element, selection )
				{
					if ( !element || element.isReadOnly() )
						return null;

					var tagName = element.hasAscendant( 'img', 1 );
					
					if ( tagName && element.getAttribute('xtype')=="xtextuedit")
					{
						return {
							xtextuedit : CKEDITOR.TRISTATE_OFF
						};
					}

					return null;
				} );
		}
    }
});