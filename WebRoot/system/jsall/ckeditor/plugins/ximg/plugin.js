CKEDITOR.plugins.add('ximg', {
    requires: ['dialog'],
    init: function(editor){
        editor.addCommand('ximg', {
            exec:function(){
                var url = contextPath+"/system/jsall/ckeditor/plugins/ximg/plugin.jsp";
                $("#modaliframe").attr("src",url);
                $("#myModalLabel").html("图片上传");
                $("#div-modal-dialog").width(360);
                $("#modaliframe").width(355);
                $("#modaliframe").height(180);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                $("#savedata").unbind('click').click(function (){
                        if(window["modaliframe"].flowform_plugin_ximg.validate()){
                            editor.insertHtml(window["modaliframe"].flowform_plugin_ximg.toDomStr());
                            $('#modal').modal("hide");
                        }
                    });
                });
            }
        });
        editor.ui.addButton('ximg', {
            label: "图片上传",
            command: 'ximg',
            icon: this.path + 'images/code.jpg'
        });
        CKEDITOR.dialog.add('ximg', this.path + 'dialogs/ximg.js');     
        if ( editor.addMenuItems ){
            editor.addMenuItems({
                ximg:{
                    label : "图片上传",
                    command : 'ximg',
                    group : 'ximg',
                    order : 1
                }
            });
        }
        
        if ( editor.contextMenu ){
            editor.contextMenu.addListener( function( element, selection ){
                if ( !element || element.isReadOnly() )	return null;

                var isInput = element.hasAscendant( 'img', 1 );
                
                if ( isInput && element.getAttribute('xtype')=="ximg"){
                    return {
                        ximg : CKEDITOR.TRISTATE_OFF
                    };
                }

                return null;
            });
        }
    }
});